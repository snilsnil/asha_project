import type { NextConfig } from "next";
import path from "path";

const nextConfig: NextConfig = {
    images: {
        remotePatterns: [
            {
                protocol: "https",
                hostname: "**",
            },
            {
                protocol: "http",
                hostname: "**",
            },
        ],
    },
    /* config options here */
    outputFileTracingRoot: path.join(__dirname, "../../"),
};

export default nextConfig;
