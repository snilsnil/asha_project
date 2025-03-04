"use client";

import Image from "next/image";
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
        } catch (error) {
            console.error("토큰 체크 오류:", error);
        }
    };

    useEffect(() => {
        checkToken();
    }, []);

    return (
        <div className={styles.page}>
            <main className={styles.main}>
                <div className={styles.ctas}>
                    <Link href="/login">
                        <Image
                            className={styles.logo}
                            src="/vercel.svg"
                            alt="Vercel logomark"
                            width={20}
                            height={20}
                        />
                        Log In
                    </Link>
                    <Link href="/signup">
                        <Image
                            className={styles.logo}
                            src="/vercel.svg"
                            alt="Vercel logomark"
                            width={20}
                            height={20}
                        />
                        Sign Up
                    </Link>
                </div>
            </main>
            <footer className={styles.footer}></footer>
        </div>
    );
}
