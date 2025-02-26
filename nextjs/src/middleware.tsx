import { NextResponse, NextRequest } from "next/server";
import axios from "axios";

/**
 *
 * 로그인 지속을 위한 jwt 검증
 *
 * @param req
 * @param res
 */
export async function middleware(req: NextRequest, res: NextResponse) {
    // 쿠키에서 토큰을 가져옴
    const refreshToken = req.cookies.get("refreshToken"); // 쿠키의 이름이 'refreshToken'일 경우
    const accessToken = req.cookies.get("accessToken"); // 쿠키의 이름이 'refreshToken'일 경우

    // 요청 경로(pathname) 가져오기
    const pathname = req.nextUrl.pathname;
    console.log(pathname);

    if (accessToken) {
        try {
            // 토큰을 검증하기 위해 API 호출
            const response = await axios.get(
                "http://localhost:8080/accessToken",
                {
                    headers: {
                        Authorization: `Bearer ${accessToken.value}`,
                    },
                }
            );

            if (response.status === 200) {
                console.log(response.data);
                if (pathname !== "/login" && pathname !== "/signup") {
                    // 토큰이 유효하면 현재 경로로 리디렉션
                    return NextResponse.next();
                } else {
                    // 로그인, 회원가입 페이지에서는 홈으로 리디렉션
                    return NextResponse.redirect(new URL("/", req.url));
                }
            }
        } catch (error) {
            console.error("accessToken validation failed:", error);
            // 토큰 검증 실패 시에도 쿠키 삭제
            res.cookies.delete("accessToken"); // 쿠키 삭제
            return NextResponse.redirect(new URL(pathname, req.url));
        }
    } else if (refreshToken) {
        return NextResponse.redirect(new URL("/auth/remake", req.url));
    }

    // 토큰이 없거나 유효하지 않으면 요청을 계속 진행
    return NextResponse.next();
}

// 미들웨어가 적용될 경로를 지정 (모든 경로에 적용)
export const config = {
    matcher: ["/", "/login", "/signup"], // 원하는 경로 추가
};
