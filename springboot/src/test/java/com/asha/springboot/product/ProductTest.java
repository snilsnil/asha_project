package com.asha.springboot.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.asha.springboot.domain.product.entity.AuctionEntity;
import com.asha.springboot.domain.product.entity.AuctionStatus;
import com.asha.springboot.domain.product.entity.BidEntity;
import com.asha.springboot.domain.product.entity.CategoryEntity;
import com.asha.springboot.domain.product.entity.ProductEntity;
import com.asha.springboot.domain.product.repository.AuctionRepository;
import com.asha.springboot.domain.product.repository.BidRepository;
import com.asha.springboot.domain.product.repository.CategoryRepository;
import com.asha.springboot.domain.product.repository.ProductRepository;
import com.asha.springboot.domain.user.repository.UserNickNameRepository;

/**
 * 상품 테스트
 */
@SpringBootTest
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserNickNameRepository userNickNameRepository;

    private String nickname1;
    private String nickname2;

    private CategoryEntity categoryEntity1;
    private CategoryEntity categoryEntity2;

    private ProductEntity product;

    private AuctionEntity auction;

    private BidEntity bid;

    private LocalDateTime now;

    private AuctionStatus inProgress;

    @BeforeEach
    public void setup() {
        // 테스트 데이터 : 사용자 추가
        nickname1 = userNickNameRepository.findByNickname("admin").get().getNickname();
        nickname2 = userNickNameRepository.findByNickname("admin2").get().getNickname();

        inProgress = AuctionStatus.IN_PROGRESS;

        categoryEntity1 = CategoryEntity.builder()
                .categoryName("game")
                .build();

        categoryEntity2 = CategoryEntity.builder()
                .categoryName("Electronics")
                .build();

        now = LocalDateTime.now();

        product = ProductEntity.builder()
                .productName("Nintendo Switch 2")
                .description("Nintendo Switch2 Console")
                .imageUrl("https://www.nintento.co.kr/switch.index.php")
                .categories(List.of(categoryEntity1, categoryEntity2))
                .build();

        auction = AuctionEntity.builder()
                .product(product)
                .seller(nickname1)
                .startPrice(new BigDecimal("100000"))
                .nowPrice(new BigDecimal("200000"))
                .endPrice(new BigDecimal("500000"))
                .buyNowPrice(new BigDecimal("500000"))
                .status(inProgress)
                .startAuctionTime(now.minusHours(5))
                .endAuctionTime(now.plusHours(5))
                .build();

        bid = BidEntity.builder()
                .auction(auction)
                .customer(nickname2)
                .bidPrice(new BigDecimal("150000"))
                .bidTime(now)
                .build();
    }

    /**
     * 상품 저장 테스트
     */
    @Test
    public void testSaveProduct() {

        // 카테고리 저장
        categoryRepository.save(categoryEntity1);
        categoryRepository.save(categoryEntity2);

        // 상품 저장
        productRepository.save(product);

        // 경매 저장
        auctionRepository.save(auction);

        // 입찰 저장
        bidRepository.save(bid);
    }

    @Test
    public void testFindProduct() {
        String productName = "Nintendo Switch 2";
        List<ProductEntity> productEntity = productRepository.findByProductName(productName);
        System.out.println(productEntity);
    }

    @Test
    public void testFindAuction() {
        List<AuctionEntity> auctionEntity = auctionRepository.findByStartPrice(new BigDecimal("100000"));
        System.out.println(auctionEntity);
    }

    @Test
    public void testFindBid() {
        List<BidEntity> bidEntity = bidRepository.findAll();
        System.out.println(bidEntity);
    }

    @Test
    public void testFindCategory() {
        List<CategoryEntity> categoryEntity = categoryRepository.findAll();
        System.out.println(categoryEntity);
    }
}
