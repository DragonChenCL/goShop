package com.dragon.service;

import com.dragon.dto.request.AddCouponInfoRequest;
import com.dragon.dto.request.AddCourseRequest;
import com.dragon.dto.request.CouponInfoRequest;
import com.dragon.dto.request.SearchCourseRequest;
import com.dragon.entity.course.CouponInfo;
import com.dragon.entity.course.CourseInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICourserService {
    //新增课程
    String addCourse(AddCourseRequest request);

    //更改课程状态
    String changeCourseStatus(String courseId);

    //分页获取我的课程
    Page<CourseInfo> getCoursePage(SearchCourseRequest request);

    //获取课程详情
    CourseInfo getCourseDetail(String courseId);

    //获取优惠券
    List<CouponInfo> getCouponList(CouponInfoRequest request);

    //删除课程
    String deleteCourse(String courseId);

    //新增优惠券
    String addCoupon(AddCouponInfoRequest request);
}
