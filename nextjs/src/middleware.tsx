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
    const token = req.cookies.get("token"); // 쿠키의 이름이 'token'일 경우
    console.log("Token:", token);

    // 요청 경로(pathname) 가져오기
    const pathname = req.nextUrl.pathname;
    console.log(pathname);

    if (token) {
        try {
            // 토큰을 검증하기 위해 API 호출
            const response = await axios.get(
                "http://localhost:8080/checkToken",
                {
                    headers: {
                        Authorization: `Bearer ${token.value}`, // token에 .value가 필요 없을 수 있습니다.
                    },
                }
            );

            if (response.status === 200) {
                if (pathname !== "/login" && pathname !== "/signup") {
                    // 토큰이 유효하면 현재 경로로 리디렉션
                    return NextResponse.next();
                } else {
                    // 로그인, 회원가입 페이지에서는 홈으로 리디렉션
                    return NextResponse.redirect(new URL("/", req.url));
                }
            } else if (response.status === 403) {
                // 토큰이 유효하지 않으면 쿠키 삭제 후 원래 경로로 리디렉션
                res.cookies.delete("token"); // 쿠키 삭제
                return NextResponse.redirect(new URL(pathname, req.url));
            }
        } catch (error) {
            console.error("Token validation failed:", error);
            // 토큰 검증 실패 시에도 쿠키 삭제
            res.cookies.delete("token"); // 쿠키 삭제
            return NextResponse.redirect(new URL(pathname, req.url));
        }
    }

    // 토큰이 없거나 유효하지 않으면 요청을 계속 진행
    return NextResponse.next();
}

// 미들웨어가 적용될 경로를 지정 (모든 경로에 적용)
export const config = {
    matcher: ["/", "/login", "/signup", "/game", "/profile", "/dashboard"], // 원하는 경로 추가
};
