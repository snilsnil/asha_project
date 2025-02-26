import { NextRequest, NextResponse } from "next/server";
import axios from "axios";

export async function GET(req: NextRequest): Promise<NextResponse> {
    try {
        const refreshToken = req.cookies.get("refreshToken")?.value;

        if (!refreshToken) {
            return NextResponse.json(
                { message: "No refresh token" },
                { status: 401 }
            );
        }

        const response = await axios.get<{
            accessToken: string;
            refreshToken: string;
        }>("http://localhost:8080/refreshToken", {
            headers: { Authorization: `Bearer ${refreshToken}` },
        });

        if (response.status === 200) {
            const { accessToken, refreshToken: newRefreshToken } =
                response.data;

            const res = NextResponse.redirect(new URL("/", req.url));

            // ✅ 새 AccessToken 설정
            res.cookies.set("accessToken", accessToken, {
                httpOnly: true,
                path: "/",
                maxAge: 60 * 25, // 25분
            });

            // ✅ 새 RefreshToken 설정
            res.cookies.set("refreshToken", newRefreshToken, {
                httpOnly: true,
                path: "/",
                maxAge: 60 * 60 * 10, // 10시간
            });

            return res;
        }

        return NextResponse.json(
            { message: "Failed to refresh token" },
            { status: 400 }
        );
    } catch (error) {
        console.error("Token refresh failed:", error);
        return NextResponse.json(
            { message: "Internal Server Error" },
            { status: 500 }
        );
    }
}
