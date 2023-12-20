package com.fiveguys.koguma.repository.product;

import com.fiveguys.koguma.data.dto.ReviewDTO;
import com.fiveguys.koguma.data.entity.Review;
import com.fiveguys.koguma.data.entity.ReviewId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {





    Review findByProductBuyerIdAndProductId(Long id, Long id1);

    Review findByProductSellerIdAndProductId(Long id, Long id1);

    boolean existsByProductBuyerIdAndProductId(Long id, Long id1);

    boolean existsByProductSellerIdAndProductId(Long id, Long id1);


    Boolean existsByProductBuyerIdAndProductIdAndSellerFlag(Long id, Long id1, boolean b);

    Boolean existsByProductSellerIdAndProductIdAndSellerFlag(Long id, Long id1, boolean b);

    Review findByProductBuyerIdAndProductIdAndSellerFlag(Long id, Long id1, boolean b);

    Review findByProductSellerIdAndProductIdAndSellerFlag(Long id, Long id1, boolean b);
}
