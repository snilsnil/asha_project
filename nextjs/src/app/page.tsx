"use client";

import styles from "./page.module.css";
import Link from "next/link";

export default function Home() {
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
