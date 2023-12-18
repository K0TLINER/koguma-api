package com.fiveguys.koguma.service.common;

import com.fiveguys.koguma.data.dto.LikeFilterAssociationDTO;
import com.fiveguys.koguma.data.dto.MemberDTO;
import com.fiveguys.koguma.data.dto.ProductDTO;
import com.fiveguys.koguma.data.entity.LikeFilterAssociation;
import com.fiveguys.koguma.data.entity.Product;
import com.fiveguys.koguma.repository.common.LikeFilterAssociationRepository;
import com.fiveguys.koguma.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeFilterAssociationServiceImpl implements LikeFilterAssociationService{


    private final LikeFilterAssociationRepository likeFilterAssociationRepository;

    public List<LikeFilterAssociationDTO> likeProductList(Long memberId) {
        List<LikeFilterAssociation> listProduct = likeFilterAssociationRepository.findAllWithProductAndImagesByMemberId(memberId);

        return listProduct.stream().map(LikeFilterAssociationDTO::fromEntity).collect(Collectors.toList());
    }

    public LikeFilterAssociationDTO getLikeProduct(Long productId, Long memberId) {
        LikeFilterAssociation likeFilterAssociation = likeFilterAssociationRepository
                .findByMemberIdAndProductId(memberId, productId)
                .orElse(null);

        if (likeFilterAssociation == null) {
            // 좋아요 정보가 없을 경우
            return null;
        }

        return LikeFilterAssociationDTO.fromEntity(likeFilterAssociation);
    }
    public void addLikeProduct(LikeFilterAssociationDTO likeFilterAssociationDTO) {
        likeFilterAssociationRepository.save(likeFilterAssociationDTO.toEntity());
    }

    public void deleteLikeProduct(Long id) {
        likeFilterAssociationRepository.deleteById(id);
    }
}
