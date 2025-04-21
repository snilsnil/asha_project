'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import axios from 'axios';

export default function AddProductPage() {
  const router = useRouter();

  const [newProduct, setNewProduct] = useState({
    productName: '',
    description: '',
    categories: '',
    imageUrl: '',
    auctionDuration: '',
  });

  // useEffect(() => {
  //   const fetchCategories = async () => {
  //     try {
  //       const res = await axios.get(`${process.env.NEXT_PUBLIC_SPRINGBOOT_URL}/categories`);
  //       const categories = res.data.map((cat: any) => cat.name); // `name` 필드 맞게 수정
  //       setCategoryOptions(categories);
  //     } catch (err) {
  //       console.error('카테고리 불러오기 실패:', err);
  //     }
  //   };
  const categoryOptions = [
    'game',
    'electronics',
    'computer',
    'nintendo',
    'playstation',
    'xbox',
  ];

  const durationOptions = [
    { label: '6시간', value: 21600 },   // 6 * 60 * 60
    { label: '12시간', value: 43200 },  // 12 * 60 * 60
    { label: '24시간', value: 86400 },  // 24 * 60 * 60
    { label: '48시간', value: 172800 }, // 48 * 60 * 60
  ];

  const [selectedCategories, setSelectedCategories] = useState<string[]>([]);

  const handleCategoryChange = (category: string) => {
    let updated = [...selectedCategories];
    if (updated.includes(category)) {
      updated = updated.filter((c) => c !== category);
    } else {
      updated.push(category);
    }
    setSelectedCategories(updated);
    setNewProduct({ ...newProduct, categories: updated.join(',') });
  };

  const handleCreateProduct = async () => {
    try {
      const response = await axios.post(
          `${process.env.NEXT_PUBLIC_SPRINGBOOT_URL}/products`,
          newProduct
      );
      console.log('상품 추가 완료:', response.data);
      router.push('/products');
    } catch (error) {
      console.error('상품 추가 중 오류:', error);
    }
  };

  return (
      <div className="mx-auto max-w-screen-2xl px-4 py-12">
        <div className="rounded-lg border border-neutral-200 bg-white p-8 md:p-12 dark:border-neutral-800 dark:bg-black">
          <h1 className="text-3xl font-bold text-white mb-8">Add Product</h1>

          <div className="grid gap-6">
            {/* 상품 이미지 */}
            <div>
              <label className="block text-sm font-medium text-white mb-1">이미지 URL</label>
              <input
                  type="text"
                  placeholder="https://example.com/image.png"
                  value={newProduct.imageUrl}
                  onChange={(e) => setNewProduct({ ...newProduct, imageUrl: e.target.value })}
                  className="w-full rounded-md border border-neutral-300 px-4 py-2 dark:bg-neutral-900 dark:text-white"
              />
              {newProduct.imageUrl && (
                  <img src={newProduct.imageUrl} alt="미리보기" className="mt-4 rounded-lg max-h-[300px] mx-auto" />
              )}
            </div>

            {/* 상품 이름 */}
            <div>
              <label className="block text-sm font-medium text-white mb-1">상품 이름</label>
              <input
                  type="text"
                  placeholder="예: 플레이스테이션 5"
                  value={newProduct.productName}
                  onChange={(e) => setNewProduct({ ...newProduct, productName: e.target.value })}
                  className="w-full rounded-md border border-neutral-300 px-4 py-2 dark:bg-neutral-900 dark:text-white"
              />
            </div>

            {/* 상품 설명 */}
            <div>
              <label className="block text-sm font-medium text-white mb-1">상품 설명</label>
              <textarea
                  placeholder="상품에 대한 설명을 적어주세요."
                  value={newProduct.description}
                  onChange={(e) => setNewProduct({ ...newProduct, description: e.target.value })}
                  className="w-full rounded-md border border-neutral-300 px-4 py-2 dark:bg-neutral-900 dark:text-white"
                  rows={4}
              />
            </div>

            {/* 카테고리 */}
            <div>
              <label className="block text-sm font-medium text-white mb-1">카테고리 선택</label>
              <div className="flex flex-wrap gap-3">
                {categoryOptions.map((category) => (
                    <label key={category} className="text-white">
                      <input
                          type="checkbox"
                          checked={selectedCategories.includes(category)}
                          onChange={() => handleCategoryChange(category)}
                          className="mr-2 accent-blue-600"
                      />
                      {category}
                    </label>
                ))}
              </div>
            </div>

            {/* 경매 시간 */}
            <div>
              <label className="block text-sm font-medium text-white mb-1">경매 시간 설정</label>
              <select
                  className="w-full rounded-md border border-neutral-300 px-4 py-2 dark:bg-neutral-900 dark:text-white"
                  value={newProduct.auctionDuration}
                  onChange={(e) => setNewProduct({ ...newProduct, auctionDuration: e.target.value })}
              >
                <option value="">시간을 선택하세요</option>
                {durationOptions.map((opt) => (
                    <option key={opt.value} value={opt.value}>
                      {opt.label}
                    </option>
                ))}
              </select>
            </div>

            {/* 등록 버튼 */}
            <button
                onClick={handleCreateProduct}
                className="w-full rounded-full bg-blue-600 py-3 text-white font-semibold hover:bg-blue-700 transition-colors"
            >
              등록하기
            </button>
          </div>
        </div>
      </div>
  );
}
