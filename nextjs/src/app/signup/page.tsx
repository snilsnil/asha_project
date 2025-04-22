"use client";

import { useEffect, useState } from "react";
import axios from "axios";

export default function SignupForm() {
    const [formData, setFormData] = useState({
        username: "",
        password: "",
        email: "",
        nickname: "",
    });
    const [loading, setLoading] = useState(true);
    const [password1, setPassword1] = useState("");
    const [password2, setPassword2] = useState("");
    const [message, setMessage] = useState("");

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
    };

    const handlePassword1Change = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword1(e.target.value);
    };

    const handlePassword2Change = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword2(e.target.value);
    };

    const checkPassword = () => password1.length >= 8;

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        if (!checkPassword() || password1 !== password2) {
            setMessage("비밀번호 조건이 맞지 않습니다.");
            return;
        }

        if (!formData.username || !formData.nickname || !formData.email) {
            setMessage("빈칸이 존재합니다.");
            return;
        }

        try {
            const res = await axios.post(
                `${process.env.NEXT_PUBLIC_SPRINGBOOT_URL}/signup`,
                {
                    ...formData,
                    password: password1,
                }
            );

            if (!res) throw new Error("Signup failed");
            window.location.href = "/login";
        } catch (error) {
            console.error("Error:", error);
            setMessage("회원가입 중 오류가 발생했습니다.");
        }
    };

    const checkToken = async () => {
        try {
            await axios.get(
                `${process.env.NEXT_PUBLIC_BASED_URL}/auth/checkToken`,
                {
                    headers: {
                        withCredentials: true,
                    },
                }
            );
            return (window.location.href = "/");
        } catch {
            setLoading(false);
        }
    };

    useEffect(() => {
        checkToken();
    }, []);

    return loading ? (
        <div className="text-center py-12 text-xl text-white bg-black min-h-screen"></div>
    ) : (
        <form
            onSubmit={handleSubmit}
            className="bg-neutral-900 mt-20 mb-40 text-white p-8 rounded-xl shadow max-w-md mx-auto space-y-4 border border-neutral-800"
        >
            <h2 className="text-2xl font-bold mb-4 text-center">회원가입</h2>
            {message && (
                <p className="text-red-500 text-sm text-center">{message}</p>
            )}

            <div>
                <label className="block mb-1 font-medium">아이디</label>
                <input
                    type="text"
                    name="username"
                    onChange={handleChange}
                    required
                    className="w-full border border-neutral-700 bg-black text-white px-3 py-2 rounded-md"
                />
            </div>

            <div>
                <label className="block mb-1 font-medium">비밀번호</label>
                <input
                    type="password"
                    value={password1}
                    onChange={handlePassword1Change}
                    required
                    className="w-full border border-neutral-700 bg-black text-white px-3 py-2 rounded-md"
                />
            </div>

            <div>
                <label className="block mb-1 font-medium">비밀번호 확인</label>
                <input
                    type="password"
                    value={password2}
                    onChange={handlePassword2Change}
                    required
                    className="w-full border border-neutral-700 bg-black text-white px-3 py-2 rounded-md"
                />
            </div>

            <div className="text-sm">
                <p
                    className={
                        checkPassword() ? "text-green-400" : "text-red-500"
                    }
                >
                    {password1 === ""
                        ? "소문자, 숫자, 특수문자 포함 8자 이상"
                        : checkPassword()
                        ? "비밀번호 조건 충족"
                        : "비밀번호가 너무 짧습니다."}
                </p>
                <p
                    className={
                        password1 === password2
                            ? "text-green-400"
                            : "text-red-500"
                    }
                >
                    {password2 === ""
                        ? "비밀번호 확인을 입력하세요."
                        : password1 === password2
                        ? "비밀번호가 일치합니다."
                        : "비밀번호가 일치하지 않습니다."}
                </p>
            </div>

            <div>
                <label className="block mb-1 font-medium">닉네임</label>
                <input
                    type="text"
                    name="nickname"
                    onChange={handleChange}
                    required
                    className="w-full border border-neutral-700 bg-black text-white px-3 py-2 rounded-md"
                />
            </div>

            <div>
                <label className="block mb-1 font-medium">이메일</label>
                <input
                    type="email"
                    name="email"
                    onChange={handleChange}
                    required
                    className="w-full border border-neutral-700 bg-black text-white px-3 py-2 rounded-md"
                />
            </div>

            <button
                type="submit"
                className="w-full bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2 px-4 rounded-md"
            >
                회원가입
            </button>
        </form>
    );
}
