import { NextRequest } from "next/server";
import axios from "axios";

/**
 *
 * 로그인
 *
 * @param req
 * @param res
 */
export async function POST(req: NextRequest) {
    const body = await req.json();

    try {
        const login = await axios.post(
            `${process.env.NEXT_PUBLIC_SPRINGBOOT_SERVER_URL}/login`,
            {
                username: body.username,
                password: body.password,
            },
            {
                headers: {
                    "Content-Type": "multipart/form-data",
                    "Access-Control-Allow-Origin": "*",
                },
                withCredentials: true, // 쿠키 포함
            }
        );

        const headers = new Headers();
        login.headers["set-cookie"]?.forEach((cookie: string) => {
            headers.append("set-cookie", cookie);
        });

        return new Response(login.data, {
            status: 200,
            headers,
        });
    } catch {
        return new Response("false", { status: 401 });
    }
}
