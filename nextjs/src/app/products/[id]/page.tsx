"use client";
import { useParams } from "next/navigation";
import { useEffect, useState, useCallback } from "react";
import Image from "next/image";

interface Category {
    categoryId: number;
    categoryName: string;
}

interface Product {
    productId: number;
    productName: string;
    description: string;
    categories: Category[];
    imageUrl: string;
    // 필요한 경우 백엔드 필드 추가 (예: currentBid?: number)
}

export default function ProductDetailPage() {
    const { id } = useParams();
    const [product, setProduct] = useState<Product | null>(null);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);


    const getProducts = useCallback(async () => {
        if (!id) return;
        setLoading(true);
        setError(null);
        try {
            const res = await fetch(
                `${process.env.NEXT_PUBLIC_SPRINGBOOT_URL}/products/${id}`,
                {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                    },
                }
            );
            if (!res.ok) {
                throw new Error(`서버 응답 오류: ${res.status}`);
            }
            const data = await res.json();
            console.log("product:", data);
            setProduct(data as Product);
        } catch (error) {
            console.error(error);
            setError("상품을 불러오는 중 오류가 발생했습니다.");
        } finally {
            setLoading(false);
        }
    }, [id]);
    
    useEffect(() => {
        getProducts();
    }, [getProducts]);

    return (
        <div className='mx-auto max-w-screen-2xl px-4'>
            {loading ? (
                <div className="py-20 text-center text-neutral-400">상품을 불러오는 중...</div>
            ) : error ? (
                <div className="py-20 text-center text-red-500">{error}</div>
            ) : (
                <div className='mx-auto max-w-screen-2xl px-4'>
                    <div
                        className='flex flex-col rounded-lg border border-neutral-200 bg-white p-8 md:p-12 lg:flex-row lg:gap-8 dark:border-neutral-800 dark:bg-black'>
                        <div className='h-full w-full basis-full lg:basis-4/6'>
                            <form>
                                <div className='relative aspect-square h-full max-h-[550px] w-full overflow-hidden'>
                                    {product && product.imageUrl ? (
                                        <Image
                                            src={product.imageUrl}
                                            alt={product.productName}
                                            fill
                                            className="w-full h-auto object-contain"
                                        />
                                    ) : (
                                        <div className="w-full h-full bg-neutral-200 dark:bg-neutral-800 flex items-center justify-center">
                                            <p className="text-neutral-500">상품 이미지를 불러오는 중...</p>
                                        </div>
                                    )}
                                </div>
                                <div className='absolute bottom-[15%] flex w-full justify-center'>
                                    <div
                                        className="mx-auto flex h-11 items-center rounded-full border border-white bg-neutral-50/80 text-neutral-500 backdrop-blur dark:border-black dark:bg-neutral-900/80">
                                    </div>

                                </div>

                            </form>

                            <ul className='my-12 flex items-center flex-wrap justify-center gap-2 overflow-auto py-1 lg:mb-0'>
                                <li className='h-20 w-20 border-1 border-b-blue-700'>
                                    <button aria-label="Select product image" className='h-full w-full text-amber-100'>
                                        사 진 1
                                    </button>
                                </li>
                                <li className='h-20 w-20 border-2 border-b-blue-700'>
                                    <button aria-label="Select product image" className='h-full w-full text-amber-100'>
                                        사 진 2
                                    </button>
                                </li>
                                <li className='h-20 w-20 border-1 border-b-blue-700'>
                                    <button aria-label="Select product image" className='h-full w-full text-amber-100'>
                                        사 진 3
                                    </button>
                                </li>

                            </ul>
                        </div>
                        <div className="basis-full lg:basis-2/6">
                            <div className="mb-6 flex flex-col border-b pb-6 dark:border-neutral-700">
                                <h1 className="mb-2 text-5xl font-medium text-white">{product?.productName ?? "상품명 없음"}</h1>
                                <div className="mr-auto w-auto rounded-full bg-blue-600 p-2 text-sm text-white">
                                    <p>낙찰가 ₩200,000<span className="text-white ml-1 inline" >원</span></p>
                                </div>
                            </div>
                            <form>
                                <dl className="mb-8">
                                    <dt className="mb-4 text-sm uppercase tracking-wide text-white">Color</dt>
                                    <dd className="flex flex-wrap gap-3">
                                        <button aria-disabled="false" title="Color Black"
                                                className="flex min-w-[48px] items-center justify-center rounded-full border bg-neutral-100 px-2 py-1 text-sm dark:border-neutral-800 dark:bg-neutral-900 ring-1 ring-transparent transition duration-300 ease-in-out hover:ring-blue-600"
                                          >Black
                                        </button>
                                        <button aria-disabled="false" title="Color White"
                                                className="flex min-w-[48px] items-center justify-center rounded-full border bg-neutral-100 px-2 py-1 text-sm dark:border-neutral-800 dark:bg-neutral-900 ring-1 ring-transparent transition duration-300 ease-in-out hover:ring-blue-600"
                                          >White
                                        </button>
                                        <button aria-disabled="true"  title="Color Blue (Out of Stock)"
                                                className="flex min-w-[48px] items-center justify-center rounded-full border bg-neutral-100 px-2 py-1 text-sm dark:border-neutral-800 dark:bg-neutral-900 relative z-10 cursor-not-allowed overflow-hidden bg-neutral-100 text-neutral-500 ring-1 ring-neutral-300 before:absolute before:inset-x-0 before:-z-10 before:h-px before:-rotate-45 before:bg-neutral-300 before:transition-transform dark:bg-neutral-900 dark:text-neutral-400 dark:ring-neutral-700 before:dark:bg-neutral-700"
                                             >Blue
                                        </button>
                                    </dd>
                                </dl>
                            </form>
                            <form>
                                <dl className="mb-8">
                                    <dt className="mb-4 text-sm uppercase tracking-wide text-white">경매하기 </dt>
                                    <dd className="flex flex-wrap gap-3">
                                        <button aria-disabled="false" title=""
                                                className="text-white flex min-w-[48px] items-center justify-center rounded-full border bg-neutral-100 px-2 py-1 text-sm dark:border-neutral-800 dark:bg-neutral-900 ring-1 ring-transparent transition duration-300 ease-in-out hover:ring-blue-600"
                                        >+ 100₩
                                        </button>
                                        <button aria-disabled="false" title="Size S"
                                                className="text-white flex min-w-[48px] items-center justify-center rounded-full border bg-neutral-100 px-2 py-1 text-sm dark:border-neutral-800 dark:bg-neutral-900 ring-1 ring-transparent transition duration-300 ease-in-out hover:ring-blue-600"
                                        >+ 1,000₩
                                        </button>
                                        <button aria-disabled="false" title="Size S"
                                                className="text-white flex min-w-[48px] items-center justify-center rounded-full border bg-neutral-100 px-2 py-1 text-sm dark:border-neutral-800 dark:bg-neutral-900 ring-1 ring-transparent transition duration-300 ease-in-out hover:ring-blue-600"
                                        >+ 10,000₩
                                        </button>
                                        <button aria-disabled="false" title="Size S"
                                                className="text-white flex min-w-[48px] items-center justify-center rounded-full border bg-neutral-100 px-2 py-1 text-sm dark:border-neutral-800 dark:bg-neutral-900 ring-1 ring-transparent transition duration-300 ease-in-out hover:ring-blue-600"
                                        >+ 100,000₩
                                        </button>

                                    </dd>
                                </dl>
                            </form>
                            <div
                                className="prose mx-auto max-w-6xl  text-black prose-headings:mt-8 prose-headings:font-semibold prose-headings:tracking-wide prose-headings:text-black prose-h1:text-5xl prose-h2:text-4xl prose-h3:text-3xl prose-h4:text-2xl prose-h5:text-xl prose-h6:text-lg prose-a:text-black prose-a:underline hover:prose-a:text-neutral-300 prose-strong:text-black prose-ol:mt-8 prose-ol:list-decimal prose-ol:pl-6 prose-ul:mt-8 prose-ul:list-disc prose-ul:pl-6  dark:prose-headings:text-white dark:prose-a:text-white dark:prose-strong:text-white mb-6 text-xl leading-tight dark:text-white">
                                남은 시간 : 10시간 : 54 분 : 21 초
                            </div>
                            
                            <div className="mb-4 text-white">
                                <h3 className="text-lg font-semibold">상품 설명</h3>
                                <p className="mt-2 text-neutral-300">{product?.description ?? "설명 없음"}</p>
                                <div className="mt-3 flex flex-wrap gap-2">
                                    {(product?.categories ?? []).map(c => (
                                        <span key={c.categoryId} className="rounded-full bg-neutral-700 px-3 py-1 text-sm text-white">{c.categoryName}</span>
                                    ))}
                                </div>
                            </div>
                            
                            <form

                                action="javascript:throw new Error('A React form was unexpectedly submitted. If you called form.submit() manually, consider using form.requestSubmit() instead. If you\\'re trying to use event.stopPropagation() in a submit event handler, consider also calling event.preventDefault().')">

                                <button aria-label="Please select an option"
                                        className="relative flex w-full items-center justify-center rounded-full bg-blue-600 p-4 tracking-wide text-white cursor-not-allowed opacity-60 hover:opacity-60 mt-10">
                                    <div className="absolute left-0 ml-4">


                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                                             strokeWidth="1.5" stroke="currentColor" aria-hidden="true" data-slot="icon"
                                             className="h-5">
                                            <path strokeLinecap="round" strokeLinejoin="round"
                                                  d="M12 4.5v15m7.5-7.5h-15"></path>
                                        </svg>
                                    </div>
                                    낙찰하기

                                </button>

                                <p aria-live="polite" className="sr-only" role="status"></p></form>
                         </div>
                    </div>
                </div>
            )}
        </div>
    );
 }
