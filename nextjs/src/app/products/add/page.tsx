'use client';

import { useState } from 'react';
import axios from 'axios';
import { useRouter } from 'next/navigation';

export default function AddProductPage() {
  const router = useRouter();

  const [newProduct, setNewProduct] = useState({
    productName: '',
    description: '',
    categories: '',
    imageUrl: '',
  });

  const categoryOptions = [
    'game',
    'electronics',
    'computer',
    'nintendo',
    'playstation',
    'xbox',
  ];
  const [selectedCategories, setSelectedCategories] = useState<string[]>([]);

  const handleCategoryChange = (category: string) => {
    let updatedCategories = [...selectedCategories];
    if (updatedCategories.includes(category)) {
      updatedCategories = updatedCategories.filter((c) => c !== category);
    } else {
      updatedCategories.push(category);
    }
    setSelectedCategories(updatedCategories);
    setNewProduct({
      ...newProduct,
      categories: updatedCategories.join(','),
    });
  };

  const handleCreateProduct = async () => {
    try {
      const response = await axios.post(
        `${process.env.NEXT_PUBLIC_SPRINGBOOT_URL}/products`,
        newProduct
      );
      console.log('Added product:', response.data);
      router.push('/products');
    } catch (error) {
      console.error('상품 추가 중 오류가 발생했습니다:', error);
    }
  };

  return (
    <div className="max-w-xl mx-auto mt-12 px-4">
      <h1 className="text-2xl font-bold mb-6">➕ 상품 등록</h1>
      <div className="space-y-4">
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
          className="w-full border border-gray-300 px-4 py-2 rounded-md"
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
          className="w-full border border-gray-300 px-4 py-2 rounded-md"
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
          className="w-full border border-gray-300 px-4 py-2 rounded-md"
        />
        <div>
          <h3 className="font-medium text-gray-700 mb-2">카테고리 선택</h3>
          <div className="flex flex-wrap gap-4">
            {categoryOptions.map((category) => (
              <label key={category} className="flex items-center space-x-2">
                <input
                  type="checkbox"
                  value={category}
                  checked={selectedCategories.includes(category)}
                  onChange={() => handleCategoryChange(category)}
                  className="accent-blue-600"
                />
                <span className="text-gray-700">{category}</span>
              </label>
            ))}
          </div>
        </div>
        <button
          onClick={handleCreateProduct}
          className="w-full bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2 px-4 rounded-md"
        >
          상품 추가
        </button>
      </div>
    </div>
  );
}
