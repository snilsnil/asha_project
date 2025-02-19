"use client"; // 이 지시어를 파일 상단에 추가합니다

import axios from "axios";
import Link from "next/link";

import { useState } from "react";
/**
 *
 * 로그인 페이지
 */
export default function LoginForm() {
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
            const res = await axios.post("http://localhost:8080/login", {
                username,
                password,
            });

            console.log(res.headers);

            if (res.status === 200) {
                const token = res.headers["authorization"];
                console.log(token);
                if (token) {
                    const tokenValue = token.replace("Bearer ", "");
                    document.cookie = `token=${tokenValue}; path=/;`;
                    return (window.location.href = "/");
                }
            } else {
                return (window.location.href = "/login");
            }
        } catch (error) {
            console.error("Error:", error);
        }
    };

    return (
        <div>
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
        </div>
    );
}
