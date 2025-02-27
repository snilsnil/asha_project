"use client";

import { useEffect, useState } from "react";
import axios from "axios";

interface Product {
    productId: number;
    productName: string;
    description: string;
    imageUrl: string;
}

interface NewProduct {
    productName: string;
    description: string;
    categories: string;
    imageUrl: string;
}

export default function ProductPage() {
    const [loading, setLoading] = useState(true);
    const [products, setProducts] = useState<Product[]>([]);
    const [newProduct, setNewProduct] = useState<NewProduct>({
        productName: "",
        description: "",
        categories: "",
        imageUrl: "",
    });

    // ✅ 체크 가능한 카테고리 목록
    const categoryOptions = [
        "game",
        "electronics",
        "computer",
        "nintendo",
        "playstation",
        "xbox",
    ];
    const [selectedCategories, setSelectedCategories] = useState<string[]>([]); // ✅ 선택된 카테고리 저장

    // 상품 목록을 가져오는 함수
    const fetchProducts = async () => {
        try {
            const response = await axios.get("http://localhost:8080/products");
            setProducts(response.data);
            console.log("Fetched products:", response.data);
            setLoading(false);
        } catch (error) {
            console.error("상품을 가져오는 데 오류가 발생했습니다:", error);
        }
    };

    // 컴포넌트가 마운트될 때 한 번만 실행
    useEffect(() => {
        fetchProducts();
    }, []);

    // ✅ 체크박스 선택 이벤트 핸들러
    const handleCategoryChange = (category: string) => {
        let updatedCategories = [...selectedCategories];

        if (updatedCategories.includes(category)) {
            // 이미 선택된 경우 제거
            updatedCategories = updatedCategories.filter((c) => c !== category);
        } else {
            // 새로운 카테고리 추가
            updatedCategories.push(category);
        }

        setSelectedCategories(updatedCategories);
        setNewProduct({
            ...newProduct,
            categories: updatedCategories.join(","), // ✅ 쉼표로 구분된 문자열 저장
        });
    };

    // 새로운 상품 추가 함수
    const handleCreateProduct = async () => {
        try {
            const response = await axios.post(
                "http://localhost:8080/products",
                newProduct
            );
            console.log("Added product:", response.data);
            fetchProducts(); // 새로운 상품 추가 후 목록 다시 불러오기
        } catch (error) {
            console.error("상품 추가 중 오류가 발생했습니다:", error);
        }
    };

    return (
        <div>
            {loading ? (
                <h1>now loading....</h1>
            ) : (
                <div>
                    <h1>상품 목록</h1>
                    <ul>
                        {products.map((product) => (
                            <li key={product.productId}>
                                {product.productName} - {product.description}
                            </li>
                        ))}
                    </ul>

                    <h2>상품 추가</h2>
                    <input
                        type="text"
                        placeholder="상품 이름"
                        value={newProduct.productName}
                        onChange={(e) =>
                            setNewProduct((prev) => ({
                                ...prev,
                                productName: e.target.value,
                            }))
                        }
                    />
                    <input
                        type="text"
                        placeholder="상품 설명"
                        value={newProduct.description}
                        onChange={(e) =>
                            setNewProduct((prev) => ({
                                ...prev,
                                description: e.target.value,
                            }))
                        }
                    />
                    <input
                        type="text"
                        placeholder="상품 이미지 URL"
                        value={newProduct.imageUrl}
                        onChange={(e) =>
                            setNewProduct((prev) => ({
                                ...prev,
                                imageUrl: e.target.value,
                            }))
                        }
                    />
                    <h3>카테고리 선택</h3>
                    {categoryOptions.map((category) => (
                        <label key={category} style={{ marginRight: "10px" }}>
                            <input
                                type="checkbox"
                                value={category}
                                checked={selectedCategories.includes(category)}
                                onChange={() => handleCategoryChange(category)}
                            />
                            {category}
                        </label>
                    ))}
                    <button onClick={handleCreateProduct}>상품 추가</button>
                </div>
            )}
        </div>
    );
}
