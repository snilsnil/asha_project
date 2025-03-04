"use client"; // 이 지시어를 파일 상단에 추가합니다

import axios from "axios";
import Link from "next/link";

import { useEffect, useState } from "react";
/**
 *
 * 로그인 페이지
 */
export default function LoginForm() {
    const [loading, setLoading] = useState(true);
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const handleUsername = (e: React.ChangeEvent<HTMLInputElement>) => {
        setUsername(e.target.value);
    };

    const handlePassword = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value);
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault(); // 폼 기본 제출 방지

        try {
            const res = await axios.post(
                `${process.env.NEXT_PUBLIC_SPRINGBOOT_URL}/login`,
                {
                    username,
                    password,
                },
                {
                    headers: {
                        "Content-Type": "multipart/form-data",
                        "Access-Control-Allow-Origin": "*",
                    },
                    withCredentials: true, // 쿠키 포함
                }
            );

            console.log(res.status);

            if (res.status == 200) {
                return (window.location.href = "/");
            }
        } catch (error) {
            console.error("Error:", error);
        }
    };

    useEffect(() => {
        const checkToken = async () => {
            try {
                const res = await axios.get(
                    `${process.env.NEXT_PUBLIC_BASED_URL}/auth/checkToken`,
                    {
                        headers: {
                            withCredentials: true,
                        },
                    }
                );
                console.log(res.data);
                if (res.data == "토큰이 이미 존재합니다.") {
                    return (window.location.href = "/"); // 서버에서 받은 Location으로 리다이렉트
                }
                setLoading(false);
            } catch (error) {
                console.error("토큰 체크 오류:", error);
            }
        };

        checkToken();
    }, []);

    return (
        <div>
            {loading ? (
                <h1>now loading....</h1>
            ) : (
                <fieldset style={{ width: "450px" }}>
                    <form onSubmit={handleSubmit}>
                        <legend>회원가입</legend>

                        <label htmlFor="username">ID:</label>
                        <input
                            type="text"
                            id="username"
                            name="username"
                            required
                            value={username}
                            onChange={handleUsername}
                        />
                        <br />

                        <label htmlFor="password1">비밀번호:</label>
                        <input
                            type="password"
                            id="password"
                            required
                            value={password}
                            onChange={handlePassword}
                        />
                        <br />
                        <button type="submit">Login</button>
                    </form>

                    <div>
                        <button>
                            <Link href="/signup">signup</Link>
                        </button>
                    </div>
                </fieldset>
            )}
        </div>
    );
}
