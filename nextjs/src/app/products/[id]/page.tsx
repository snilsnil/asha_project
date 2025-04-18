// app/products/[id]/page.tsx
import { notFound } from 'next/navigation';

interface Params {
  id: string;
}

export default function ProductDetailPage({ params }: { params: Params }) {
  const { id } = params;

  // 임시 더미 데이터 (나중에 fetch로 바꾸면 됨)
  const dummy = {
    1: {
      productName: '플스5',
      description: '최신형 콘솔 게임기',
      imageUrl: 'https://via.placeholder.com/300',
    },
    2: {
      productName: '닌텐도 스위치',
      description: '재밌는 휴대용 콘솔',
      imageUrl: 'https://via.placeholder.com/300',
    },
  };

  const product = dummy[id];

  if (!product) return notFound(); // 없는 ID

  return (
    <div style={{ padding: '2rem' }}>
      <h1>{product.productName}</h1>
      <img src={product.imageUrl} alt={product.productName} width={300} />
      <p>{product.description}</p>
    </div>
  );
}
