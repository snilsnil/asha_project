import type { NextConfig } from "next";

const nextConfig: NextConfig = {
    images: {
        remotePatterns: [
            {
                protocol: "https",
                hostname: "i.namu.wiki",
            },
        ],
    },
    /* config options here */
};

export default nextConfig;
