package com.dragon.course;

import com.dragon.entity.course.CourseInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseInfoRepository extends JpaRepository<CourseInfo,String> {

    Optional<CourseInfo> findByDeleteIsFalseAndId(String courseId);

    Page<CourseInfo> findAll(Specification<CourseInfo> specification, Pageable pageable);
}
