// app/layout.tsx
import Header from "@/components/common/Header";
import Footer from "@/components/common/Footer";
import "@/app/globals.css";

export default function RootLayout({
    children,
}: {
    children: React.ReactNode;
}) {
    return (
        <html lang="ko">
            <body suppressHydrationWarning={true}>
                <Header />
                <main className="page-container">{children}</main>
                <Footer />
            </body>
        </html>
    );
}
