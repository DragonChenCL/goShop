package com.dragon.course;

import com.dragon.entity.course.CouponInfo;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponInfoRepository extends JpaRepository<CouponInfo,String> {
    List<CouponInfo> findByDeleteIsFalseAndAvailableIsTrueAndAmountGreaterThanOrderByCreateDateDesc(Integer amount);

    List<CouponInfo> findAll(Specification<CouponInfo> specification);
}
