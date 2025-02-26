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
            const res = await axios.post(
                "http://localhost:8080/login",
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

            if (res.status === 200) {
                return (window.location.href = "/");
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
