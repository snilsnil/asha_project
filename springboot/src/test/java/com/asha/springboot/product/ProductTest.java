package com.asha.springboot.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.asha.springboot.domain.product.dto.ProductDTO;
import com.asha.springboot.domain.product.entity.AuctionEntity;
import com.asha.springboot.domain.product.entity.BidEntity;
import com.asha.springboot.domain.product.entity.CategoryEntity;
import com.asha.springboot.domain.product.entity.ProductEntity;
import com.asha.springboot.domain.product.repository.AuctionRepository;
import com.asha.springboot.domain.product.repository.BidRepository;
import com.asha.springboot.domain.product.repository.CategoryRepository;
import com.asha.springboot.domain.product.repository.ProductRepository;
import com.asha.springboot.domain.user.entity.UserEntity;
import com.asha.springboot.domain.user.repository.UserRepository;

import static com.asha.springboot.domain.product.entity.AuctionStatus.IN_PROGRESS;


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
    private UserRepository userRepository;

    /**
     * 상품 저장 테스트
     */
    @Test
    public void testSaveProduct() {

        LocalDateTime productNow = LocalDateTime.now();
        LocalDateTime starTime = productNow.minusHours(5);
        LocalDateTime endTime = productNow.plusHours(5);

        ProductDTO productDTO = ProductDTO.builder()
                .productName("Nintendo Switch 2")
                .description("Nintendo Switch 2 Console")
                .imageUrl("https://www.nintendo.co.kr/switch/index.php")
                .categoryName("Game, Electronics")
                .buyNowPrice(new BigDecimal("500000"))
                .startPrice(new BigDecimal("100000"))
                .nowPrice(new BigDecimal("200000"))
                .endPrice(new BigDecimal("500000"))
                .startAuctionTime(starTime)
                .endAuctionTime(endTime)
                .status(IN_PROGRESS)
                .bidPrice(new BigDecimal("150000"))
                .bidTime(productNow)
                .build();

        UserEntity userEntity1 = userRepository.findByUsername("admin");
        UserEntity userEntity2 = userRepository.findByUsername("admin2");

        List<CategoryEntity> categoryEntities = productDTO.toCategoryEntities();
        categoryEntities = categoryRepository.saveAll(categoryEntities);

        ProductEntity productEntity = productRepository.save(productDTO.toProductEntity(categoryEntities));

        AuctionEntity auctionEntity = auctionRepository.save(productDTO.toAuctionEntity(productEntity, userEntity1));

        bidRepository.save(productDTO.toBidEntity(auctionEntity, userEntity2));
    }

    @Test
    public void testFindProduct() {
        String productName = "Nintendo Switch 2";
        ProductEntity productEntity = productRepository.findByProductName(productName);
        System.out.println(productEntity);
    }

    @Test
    public void testFindAuction() {
        List<AuctionEntity> auctionEntity = auctionRepository.findAll();
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
