import { NextResponse } from "next/server";
/**
 *
 * 로그아웃
 *
 */
export async function GET() {
    // 쿠키 삭제
    const response = NextResponse.json({ message: "Logged out successfully" });
    response.cookies.set("refreshToken", "", { maxAge: 0 });
    response.cookies.set("accessToken", "", { maxAge: 0 });
    return response;
}
