import { NextRequest, NextResponse } from "next/server";
import axios from "axios";
import { RequestCookie } from "next/dist/compiled/@edge-runtime/cookies";

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
        const resAccessToken = await checkAccessToken(
            accessToken.value,
            currentPath
        );

        return new Response(JSON.stringify(resAccessToken), { status: 200 });
    } else if (refreshToken) {
        return checkRefreshToken(refreshToken, currentPath);
    }

    // 토큰이 없거나 유효하지 않으면 요청을 계속 진행
    return new Response("false", { status: 401 });
}

async function checkAccessToken(
    accessToken: string | RequestCookie,
    currentPath: string
) {
    try {
        if (currentPath === "/login" || currentPath === "/signup") {
            const result = "토큰이 이미 존재합니다.";
            return new Response(JSON.stringify(result), {
                status: 200,
            });
        }

        // 토큰을 검증하기 위해 API 호출
        const response = await axios.get(
            `${process.env.NEXT_PUBLIC_SPRINGBOOT_SERVER_URL}/accessToken`,
            {
                headers: {
                    Authorization: `Bearer ${accessToken}`,
                },
            }
        );
        return response.data;
    } catch (error) {
        console.log(error);
        const res = NextResponse.json(
            "서버에 문제가 발생했습니다. 나중에 다시 시도해주세요."
        );
        res.cookies.delete("accessToken");
        return res;
    }
}

async function checkRefreshToken(
    refreshToken: RequestCookie,
    currentPath: string
) {
    try {
        if (currentPath === "/login" || currentPath === "/signup") {
            const result = "토큰이 이미 존재합니다.";
            return new Response(JSON.stringify(result), {
                status: 200,
            });
        }

        // 토큰을 검증하기 위해 API 호출
        const response = await axios.get(
            `${process.env.NEXT_PUBLIC_SPRINGBOOT_SERVER_URL}/refreshToken`,
            {
                headers: {
                    Authorization: `Bearer ${refreshToken.value}`,
                },
            }
        );

        // ✅ 새 AccessToken 설정
        const accessToken = response.data.accessToken;
        const res = NextResponse.json({ data: response.data });
        res.cookies.set("accessToken", accessToken, {
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

        // ✅ 새로 발급된 AccessToken으로 checkAccessToken 호출
        const accessTokenData = await checkAccessToken(
            accessToken,
            currentPath
        );

        // ✅ checkAccessToken 결과와 함께 응답 전송

        const finalResponse = NextResponse.json(accessTokenData);
        finalResponse.cookies.set("accessToken", accessToken, {
            httpOnly: true,
            path: "/",
            maxAge: 60 * 25, // 25분
        });

        return finalResponse;
    } catch (error) {
        console.log(error);
        const res = NextResponse.json(
            "서버에 문제가 발생했습니다. 나중에 다시 시도해주세요."
        );
        res.cookies.delete("refreshToken");
        return res;
    }
}
