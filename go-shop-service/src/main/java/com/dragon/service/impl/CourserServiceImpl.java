package com.dragon.service.impl;

import com.dragon.course.CouponInfoRepository;
import com.dragon.course.CourseInfoRepository;
import com.dragon.dto.request.AddCouponInfoRequest;
import com.dragon.dto.request.AddCourseRequest;
import com.dragon.dto.request.CouponInfoRequest;
import com.dragon.dto.request.SearchCourseRequest;
import com.dragon.entity.course.CouponInfo;
import com.dragon.entity.course.CourseInfo;
import com.dragon.exception.NormalException;
import com.dragon.service.ICourserService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@Service
public class CourserServiceImpl implements ICourserService {
    @Resource
    private CourseInfoRepository courseInfoRepository;
    @Resource
    private CouponInfoRepository couponInfoRepository;

    @Override
    public String addCourse(AddCourseRequest request) {
        CourseInfo courseInfo = new CourseInfo();
        BeanUtils.copyProperties(request,courseInfo);
        courseInfo.setAvailable(Boolean.FALSE);
        courseInfoRepository.save(courseInfo);
        return "新增课程成功，待审核";
    }

    @Override
    public String addCoupon(AddCouponInfoRequest request) {
        CourseInfo courseInfoById = this.getCourseInfoById(request.getCourseId());
        CouponInfo couponInfo = new CouponInfo();
        BeanUtils.copyProperties(request,couponInfo);
        couponInfo.setCourseInfo(courseInfoById);
        couponInfoRepository.save(couponInfo);
        return "新增优惠券成功";
    }

    @Override
    public String deleteCourse(String courseId) {
        CourseInfo courseInfoById = this.getCourseInfoById(courseId);
        courseInfoById.setDelete(false);
        courseInfoRepository.save(courseInfoById);
        return "删除成功";
    }

    @Override
    public List<CouponInfo> getCouponList(CouponInfoRequest request) {
        Specification<CouponInfo> specification = (Specification<CouponInfo>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            if (StringUtils.isNotBlank(request.getGrade())){
                predicates.add(criteriaBuilder.equal(root.get("grade"),request.getGrade()));
            }
            if (StringUtils.isNotBlank(request.getCourseId())){
                predicates.add(criteriaBuilder.equal(root.join("courseInfo",JoinType.LEFT).get("id"),request.getCourseId()));
            }else {
                predicates.add(criteriaBuilder.equal(root.join("courseInfo",JoinType.LEFT).get("available"),Boolean.TRUE));
                predicates.add(criteriaBuilder.greaterThan(root.get("amount"),0));
            }
            predicates.add(criteriaBuilder.equal(root.get("delete"),Boolean.FALSE));
            predicates.add(criteriaBuilder.equal(root.get("available"),Boolean.TRUE));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return couponInfoRepository.findAll(specification);
    }

    @Override
    public CourseInfo getCourseDetail(String courseId) {
        return this.getCourseInfoById(courseId);
    }

    @Override
    public Page<CourseInfo> getCoursePage(SearchCourseRequest request) {
        Pageable pageable = PageRequest.of(request.getPageNum(),request.getPageSize(), Sort.Direction.DESC,"createDate");
        Specification<CourseInfo> specification = (Specification<CourseInfo>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            if (request.getAvailable() != null){
                predicates.add(criteriaBuilder.equal(root.get("available"),request.getAvailable()));
            }
            if (StringUtils.isNotBlank(request.getGrade())){
                predicates.add(criteriaBuilder.equal(root.get("grade"),request.getGrade()));
            }
            if (StringUtils.isNotBlank(request.getUserId())){
                predicates.add(criteriaBuilder.equal(root.joinList("favoriteUsers", JoinType.INNER).get("id"),request.getUserId()));
            }
            criteriaQuery.distinct(true);
            predicates.add(criteriaBuilder.equal(root.get("delete"),Boolean.FALSE));
            return criteriaQuery.where(predicates.toArray(new Predicate[0])).getRestriction();
        };

        return courseInfoRepository.findAll(specification,pageable);
    }

    @Override
    public String changeCourseStatus(String courseId) {
        CourseInfo courseInfoById = this.getCourseInfoById(courseId);
        courseInfoById.setAvailable(Boolean.TRUE);
        courseInfoRepository.save(courseInfoById);
        return "上架成功";
    }

    private CourseInfo getCourseInfoById(String courseId){
        Optional<CourseInfo> courseInfoOptional = courseInfoRepository.findByDeleteIsFalseAndId(courseId);
        if (!courseInfoOptional.isPresent()){
            throw new NormalException("课程不存在");
        }
        return courseInfoOptional.get();
    }
}
