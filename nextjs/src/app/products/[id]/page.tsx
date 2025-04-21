"use client";

import { useParams, notFound } from "next/navigation";
import Image from "next/image";

export default function ProductDetailPage() {
    const { id } = useParams();

    const dummy: Record<
        string,
        { productName: string; description: string; imageUrl: string }
    > = {
        "1": {
            productName: "플스5",
            description: "최신형 콘솔 게임기",
            imageUrl:
                "https://i.namu.wiki/i/slG6yfFwae-r-Jzq8WC59XPkHlyydYuTMwnf3UKwuwZq-4LeOXVGJWBdHTmc_TJIUgp73xoD9ivrfx93E8xj4XzqOPFtCxwJ6CT2ifqq69WXWSHbkAF4KTR2xcaCsKXHoBkf6dqUIcc0MW6jRYRVdw.webp",
        },
        "2": {
            productName: "닌텐도 스위치",
            description: "재밌는 휴대용 콘솔",
            imageUrl:
                "https://i.namu.wiki/i/QcmvxVziNNazivnL1iwOAinNCAnGLfjwu84B6_JiFNuJPVt8ulnKraZdB3gsx5LC4tFuu_q5VPp9xBsAqbGodu0rR2JfhLCbOTlw4GOyVDURs2JlI0LWAQHvkQPYY88xVKXIVs_9hx8tVufOumDKlQ.webp",
        },
    };

    const product = dummy[id as string];

    if (!product) return notFound();

    return (
        <div style={{ padding: "2rem" }}>
            <h1>{product.productName}</h1>
            <Image
                src={product.imageUrl}
                alt={product.productName}
                width={300} // 최적화된 너비
                height={300} // 최적화된 높이
                quality={75} // 이미지 품질 조정 (선택사항)
            />
            <p>{product.description}</p>
        </div>
    );
}
