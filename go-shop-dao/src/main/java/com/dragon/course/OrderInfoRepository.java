package com.dragon.course;

import com.dragon.entity.course.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInfoRepository extends JpaRepository<OrderInfo,String> {
}
