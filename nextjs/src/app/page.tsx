"use client";

import styles from "./page.module.css";
import Link from "next/link";
import axios from "axios";
import { useEffect } from "react";

export default function Home() {
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
            console.log(res);
        } catch {
            // console.error("토큰 체크 오류:", error);
        }
    };

    useEffect(() => {
        checkToken();
    }, []);

    return (
        <div className={styles.page}>
            <main className={styles.main}>
                <Link href="/login" className="text-white hover:underline">
                    Log In
                </Link>
                <Link href="/signup" className="text-white hover:underline">
                    Sign Up
                </Link>
            </main>
            <footer className={styles.footer}></footer>
        </div>
    );
}
