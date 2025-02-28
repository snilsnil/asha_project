import { NextRequest, NextResponse } from "next/server";
import axios from "axios";

/**
 *
 * 로그인 지속을 위한 jwt 검증
 *
 * @param req
 * @param res
 */
export async function GET(req: NextRequest) {
    // 쿠키에서 토큰을 가져옴
    const refreshToken = req.cookies.get("refreshToken"); // 쿠키의 이름이 'refreshToken'일 경우
    const accessToken = req.cookies.get("accessToken"); // 쿠키의 이름이 'refreshToken'일 경우

    const referer = req.headers.get("referer") ?? "";
    const baseUrlLength = process.env.NEXT_PUBLIC_BASED_URL?.length || 0;
    const currentPath = referer.substring(baseUrlLength);

    if (accessToken != undefined) {
        try {
            if (currentPath === "/login" || currentPath === "/signup") {
                const result = "토큰이 이미 존재합니다.";
                return new Response(JSON.stringify(result), {
                    status: 200,
                });
            }

            // 토큰을 검증하기 위해 API 호출
            const response = await axios.get(
                "http://localhost:8080/accessToken",
                {
                    headers: {
                        Authorization: `Bearer ${accessToken.value}`,
                    },
                }
            );
            return new Response(JSON.stringify(response.data), { status: 200 });
        } catch (error) {
            console.log(error);
            return new Response(`accessToken에서 오류가 발생했습니다.`, {
                status: 500,
            });
        }
    } else if (refreshToken) {
        try {
            if (currentPath === "/login" || currentPath === "/signup") {
                const result = "토큰이 이미 존재합니다.";
                return new Response(JSON.stringify(result), {
                    status: 200,
                });
            }

            // 토큰을 검증하기 위해 API 호출
            const response = await axios.get(
                "http://localhost:8080/refreshToken",
                {
                    headers: {
                        Authorization: `Bearer ${refreshToken.value}`,
                    },
                }
            );

            // ✅ 새 AccessToken 설정
            const res = NextResponse.json({ data: response.data });
            res.cookies.set("accessToken", response.data.accessToken, {
                httpOnly: true,
                path: "/",
                maxAge: 60 * 25, // 25분
            });

            // ✅ 새 RefreshToken 설정
            res.cookies.set({
                name: "refreshToken",
                value: response.data.refreshToken,
                httpOnly: true,
                path: "/",
                maxAge: 60 * 60 * 10, // 10시간
            });

            return res;
        } catch (error) {
            console.log(error);
            return new Response(`refreshToken에서 오류가 발생했습니다.`, {
                status: 500,
            });
        }
    }

    // 토큰이 없거나 유효하지 않으면 요청을 계속 진행
    return new Response("토큰이 유효합니다.", { status: 200 });
}
