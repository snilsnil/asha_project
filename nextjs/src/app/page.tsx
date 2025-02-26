import Image from "next/image";
import styles from "./page.module.css";
import Link from "next/link";

export default function Home() {
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
