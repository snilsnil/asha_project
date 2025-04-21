"use client";
import { useEffect, useState } from "react";
import axios from "axios";

export default function LoginPage() {
    const [loading, setLoading] = useState(true);
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const res = await axios.post(
                `${process.env.NEXT_PUBLIC_SPRINGBOOT_URL}/login`,
                { username, password },
                {
                    headers: {
                        "Content-Type": "multipart/form-data",
                        "Access-Control-Allow-Origin": "*",
                    },
                    withCredentials: true, // 쿠키 포함
                }
            );
            if (res.status === 200) window.location.href = "/";
        } catch (error) {
            console.error("로그인 실패", error);
        }
    };

    //토큰 유효성 검사사
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

    useEffect(() => {
        checkToken();
    }, []);

    return loading ? (
        <div className="min-h-screen bg-black text-white flex items-center justify-center px-4"></div>
    ) : (
        <form
            onSubmit={handleSubmit}
            className="bg-neutral-900 p-8 rounded-lg shadow-md w-full max-w-sm space-y-4"
        >
            <h2 className="text-xl font-bold text-center">로그인</h2>
            <input
                type="text"
                placeholder="ID"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                className="w-full px-4 py-2 rounded-md border border-neutral-700 bg-black text-white"
            />
            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className="w-full px-4 py-2 rounded-md border border-neutral-700 bg-black text-white"
            />
            <button
                type="submit"
                className="w-full bg-blue-600 hover:bg-blue-700 text-white py-2 rounded-md"
            >
                로그인
            </button>
        </form>
    );
}
