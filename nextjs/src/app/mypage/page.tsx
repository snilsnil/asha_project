"use client";

import axios from "axios";
import { useEffect, useState } from "react";

export default function MyPage() {
    const [loading, setLoading] = useState(true);
    const [userData, setUserData] = useState({
        role: "",
        username: "",
        userId: "",
    });

    const logout = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            await axios.get(
                `${process.env.NEXT_PUBLIC_BASED_URL}/auth/logout`,
                {}
            );
            window.location.href = "/";
        } catch {}
    };

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

            setUserData({
                role: res.data.role,
                username: res.data.username,
                userId: res.data.userId,
            });
            setLoading(false);
        } catch {
            return (window.location.href = "/");
        }
    };

    useEffect(() => {
        checkToken();
    }, []);
    return loading ? (
        <div className="text-center py-12 text-xl text-white bg-black min-h-screen"></div>
    ) : (
        <div>
            <h2 className="text-xl font-bold  mb-4  text-center text-white">
                {userData.username}의 마이페이지입니다.
            </h2>
            <div className="flex justify-center">
                <button
                    className="text-xl font-bold mb-4 text-center text-white hover:underline"
                    onClick={logout}
                >
                    로그아웃
                </button>
            </div>
        </div>
    );
}
