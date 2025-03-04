"use client";

import { useEffect, useState } from "react";

import axios from "axios";

/**
 * 회원가입 페이지
 */
export default function SignupForm() {
    const [formData, setFormData] = useState({
        username: "",
        password: "", // 이 필드는 사용하지 않음 (fetch에서만 추가)
        email: "",
        nickname: "",
    });
    const [loading, setLoading] = useState(true);
    const [password1, setPassword1] = useState("");
    const [password2, setPassword2] = useState("");
    const [message, setMessage] = useState("");

    // 입력 필드 변경 핸들러
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

    // 비밀번호 길이 검사
    const checkPassword = () => password1.length >= 8;

    // 폼 제출 핸들러
    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault(); // 폼 기본 제출 방지

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
                    password: password1, // password 추가
                }
            );

            console.log(res);

            if (!res) throw new Error("Signup failed");

            console.log("Signup successful!");

            // 회원가입 성공 시 로그인 페이지로 이동
            window.location.href = "/login";
        } catch (error) {
            console.error("Error:", error);
            setMessage("회원가입 중 오류가 발생했습니다.");
        }
    };

    // 토큰 유효성 검사
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
    return (
        <div>
            {loading ? (
                <h1>now loading....</h1>
            ) : (
                <form onSubmit={handleSubmit}>
                    <fieldset style={{ width: "450px" }}>
                        <legend>회원가입</legend>
                        <span style={{ color: message ? "red" : "black" }}>
                            {message}
                        </span>
                        <br />

                        <label htmlFor="username">ID:</label>
                        <input
                            type="text"
                            id="username"
                            name="username"
                            required
                            onChange={handleChange}
                        />
                        <br />

                        <label htmlFor="password1">비밀번호:</label>
                        <input
                            type="password"
                            id="password1"
                            required
                            value={password1}
                            onChange={handlePassword1Change}
                        />
                        <br />

                        <label htmlFor="password2">비밀번호 확인:</label>
                        <input
                            type="password"
                            id="password2"
                            required
                            value={password2}
                            onChange={handlePassword2Change}
                        />
                        <br />
                        <br />

                        <ul>
                            <li>
                                <span
                                    style={{
                                        color:
                                            password1 === ""
                                                ? "white"
                                                : checkPassword()
                                                ? "green"
                                                : "red",
                                    }}
                                >
                                    {password1 === ""
                                        ? "소문자, 숫자, 특수문자 포함해서 8~15자리 입력하세요."
                                        : checkPassword()
                                        ? "모든 조건이 충족되었습니다."
                                        : "조건이 충족되지 못했습니다."}
                                </span>
                            </li>
                            <li>
                                <span
                                    style={{
                                        color:
                                            password1 === "" || password2 === ""
                                                ? "white"
                                                : password1 === password2
                                                ? "green"
                                                : "red",
                                    }}
                                >
                                    {password1 === "" || password2 === ""
                                        ? "비밀번호를 입력하세요."
                                        : password1 === password2
                                        ? "비밀번호가 일치합니다."
                                        : "비밀번호가 다릅니다."}
                                </span>
                            </li>
                        </ul>

                        <label htmlFor="nickname">닉네임:</label>
                        <input
                            type="text"
                            id="nickname"
                            name="nickname"
                            required
                            onChange={handleChange}
                        />
                        <br />

                        <label htmlFor="email">이메일:</label>
                        <input
                            type="email"
                            id="email"
                            name="email"
                            required
                            onChange={handleChange}
                        />

                        <br />
                        <br />
                        <button type="submit">Signup</button>
                    </fieldset>
                </form>
            )}
        </div>
    );
}
