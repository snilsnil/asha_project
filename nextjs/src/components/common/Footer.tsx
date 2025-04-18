export default function Footer() {
  const linkClass =
    'block p-2 text-lg underline-offset-4 hover:text-black hover:underline md:inline-block md:text-sm dark:hover:text-neutral-300';

  return (
    <footer className="text-sm text-neutral-500 dark:text-neutral-400">
      <div className="mx-auto flex w-full max-w-7xl flex-col gap-6 border-t border-neutral-200 px-6 py-12 text-sm md:flex-row md:gap-12 md:px-4 min-[1320px]:px-0 dark:border-neutral-700">
        <div>
          <span className="uppercase">Acme Store</span>
        </div>
        <nav>
          <ul className="flex flex-col md:flex-row md:flex-wrap gap-2 md:gap-4">
            <li>
              <a className={linkClass} href="/">
                Home
              </a>
            </li>
            <li>
              <a className={linkClass} href="/about">
                About
              </a>
            </li>
            <li>
              <a className={linkClass} href="/terms-conditions">
                Terms &amp; Conditions
              </a>
            </li>
            <li>
              <a className={linkClass} href="/shipping-return-policy">
                Shipping &amp; Return Policy
              </a>
            </li>
            <li>
              <a className={linkClass} href="/privacy-policy">
                Privacy Policy
              </a>
            </li>
            <li>
              <a className={linkClass} href="/frequently-asked-questions">
                FAQ
              </a>
            </li>
          </ul>
        </nav>
      </div>
    </footer>
  );
}
