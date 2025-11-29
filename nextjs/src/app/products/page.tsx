"use client";

import { useEffect, useState } from "react";

interface Category {
    categoryId: number;
    categoryName: string;
}

interface Product {
    productId: number;
    productName: string;
    description: string;
    categories: Category[];
}

export default function ProductsPage() {
    const [loading, setLoading] = useState(true);
    const [products, setProducts] = useState<Product[] | null>(null);

    const getProducts = async () => {
        try {
            const res = await fetch(
                `${process.env.NEXT_PUBLIC_SPRINGBOOT_URL}/products`,
                {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                    },
                }
            );
            const data = await res.json();
            console.log(data);
            setProducts(data as Product[]);
            setLoading(false);
            } catch (error) {
                console.error(error);
                setLoading(false);
            }
            };

            useEffect(() => {
            getProducts();
            }, []);

            return loading ? (
            <div className="min-h-screen text-white flex px-4"></div>
            ) : (
            <div className="min-h-screen text-white flex px-4">
                {products ? (
                <ul>
                    {products.map((product) => (
                    <li key={product.productId}>
                        <a href={`/products/${product.productId}`}>
                        {product.productId} - {product.productName} -{" "}
                        {product.description} - {"["}
                        {product.categories
                            .map((c) => `${c.categoryName}`)
                            .join(", ")}
                        {"]"}
                        </a>
                    </li>
                    ))}
                </ul>
                ) : (
                <>로딩중</>
                )}
            </div>
            );
        }
