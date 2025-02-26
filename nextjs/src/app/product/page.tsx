import { useEffect, useState } from 'react';
import axios from 'axios';

export default function ProductPage() {
    const [products, setProducts] = useState<any[]>([]);
    const [newProduct, setNewProduct] = useState({ productName: '', description: '', imageUrl: '' });

    // 상품 목록을 가져오는 함수
    const fetchProducts = async () => {
        try {
            const response = await axios.get('http://localhost:8080/products'); // Spring Boot API URL
            setProducts(response.data);
        } catch (error) {
            console.error('상품을 가져오는 데 오류가 발생했습니다:', error);
        }
    };

    // 컴포넌트가 마운트될 때 상품 목록을 가져옵니다.
    useEffect(() => {
        fetchProducts();
    }, []);

    // 새로운 상품 추가 함수
    const handleCreateProduct = async () => {
        try {
            const response = await axios.post('http://localhost:8080/products', newProduct);
            setProducts([...products, response.data]); // 새로운 상품 추가
            setNewProduct({ productName: '', description: '', imageUrl: '' }); // 입력 필드 초기화
        } catch (error) {
            console.error('상품 추가 중 오류가 발생했습니다:', error);
        }
    };

    return (
        <div>
            <h1>상품 목록</h1>
            <ul>
                {products.map(product => (
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
                onChange={e => setNewProduct({ ...newProduct, productName: e.target.value })}
            />
            <input
                type="text"
                placeholder="상품 설명"
                value={newProduct.description}
                onChange={e => setNewProduct({ ...newProduct, description: e.target.value })}
            />
            <input
                type="text"
                placeholder="상품 이미지 URL"
                value={newProduct.imageUrl}
                onChange={e => setNewProduct({ ...newProduct, imageUrl: e.target.value })}
            />
            <button onClick={handleCreateProduct}>상품 추가</button>
        </div>
    );
}
