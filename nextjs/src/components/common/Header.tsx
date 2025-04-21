import Link from 'next/link';

export default function Header() {
  return (
    <header className="relative sticky top-0 z-50 border-b border-neutral-800">
      <div className="max-w-7xl mx-auto px-6 py-4 flex justify-between items-center">
        {/* 왼쪽: 로고 + 네비게이션 */}
        <div className="flex items-center gap-6">
          <Link href="/" className="flex items-center gap-2">
            <div className="flex items-center justify-center border border-neutral-200 bg-white dark:border-neutral-700 dark:bg-black h-[40px] w-[40px] rounded-xl">
              {/* 로고 아이콘 */}
              <svg
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 32 28"
                className="h-4 w-4 fill-black dark:fill-white"
              >
                <path d="M21.5758 9.75769L16 0L0 28H11.6255L21.5758 9.75769Z"></path>
                <path d="M26.2381 17.9167L20.7382 28H32L26.2381 17.9167Z"></path>
              </svg>
            </div>
            <div className="ml-2 text-white font-medium uppercase">ASHA</div>
          </Link>

          <nav className="hidden md:flex gap-6 text-m text-gray-300">
            <Link
              href="/products/hot"
              className="hover:text-white hover:underline"
            >
              HOT
            </Link>
            <Link href="/products" className="hover:text-white hover:underline">
              Products
            </Link>
            <Link
              href="/users/:id"
              className="hover:text-white hover:underline"
            >
              MYPAGE
            </Link>
          </nav>
        </div>

        {/* 중앙: 검색창 */}
        <div className="hidden md:flex w-1/3 justify-center">
          <form className="relative w-full lg:w-80 xl:w-full" action="/search">
            <input
              type="text"
              name="q"
              placeholder="Search for products..."
              className="w-full rounded-lg border bg-neutral-800 text-white px-4 py-2 placeholder:text-neutral-500 dark:border-neutral-700 dark:placeholder:text-neutral-400"
            />
            <div className="absolute right-0 top-0 mr-3 flex h-full items-center"></div>
          </form>
        </div>

        {/* 오른쪽: 카트 버튼 */}
        <div className="flex justify-end md:w-1/3">
          <button aria-label="Open cart">
            <div className="relative flex h-11 w-11 items-center justify-center rounded-md border border-neutral-200 text-white transition-colors dark:border-neutral-700 dark:text-white">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke-width="1.5"
                stroke="currentColor"
                className="h-4 transition-all ease-in-out hover:scale-110"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  d="M2.25 3h1.386c.51 0 .955.343 1.087.835l.383 1.437M7.5 14.25a3 3 0 0 0-3 3h15.75m-12.75-3h11.218c1.121-2.3 2.1-4.684 2.924-7.138a60.114 60.114 0 0 0-16.536-1.84M7.5 14.25 5.106 5.272M6 20.25a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Zm12.75 0a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Z"
                ></path>
              </svg>
            </div>
          </button>
        </div>
      </div>
    </header>
  );
}
