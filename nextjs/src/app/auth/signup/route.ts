import { NextRequest } from "next/server";
import axios from "axios";

/**
 *
 * 회원가입
 *
 * @param req
 * @param res
 */
export async function POST(req: NextRequest) {
    const body = await req.json();

    try {
        const signup = await axios.post(
            `${process.env.NEXT_PUBLIC_SPRINGBOOT_SERVER_URL}/signup`,
            {
                username: body.username,
                password: body.password,
                email: body.email,
                nickname: body.nickname,
            }
        );

        return new Response(signup.data, { status: 200 });
    } catch {
        return new Response("false", { status: 401 });
    }
}
