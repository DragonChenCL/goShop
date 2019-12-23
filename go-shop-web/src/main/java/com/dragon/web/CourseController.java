package com.dragon.web;

import com.dragon.dto.request.AddCouponInfoRequest;
import com.dragon.dto.request.AddCourseRequest;
import com.dragon.dto.request.CouponInfoRequest;
import com.dragon.dto.request.SearchCourseRequest;
import com.dragon.entity.course.CouponInfo;
import com.dragon.entity.course.CourseInfo;
import com.dragon.service.ICourserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/course")
@Api(tags = {"课程板块"})
public class CourseController {
    @Resource
    private ICourserService courserService;

    @ApiOperation("新增课程")
    @PostMapping("/add")
    public String addCourse(@RequestBody AddCourseRequest request){
        return courserService.addCourse(request);
    }

    @ApiOperation("更改课程状态")
    @PostMapping("/status/{courseId}")
    public String changeCourseStatus(@PathVariable("courseId") String courseId){
        return courserService.changeCourseStatus(courseId);
    }

    @ApiOperation("删除课程")
    @PostMapping("/delete/{courseId}")
    public String deleteCourse(@PathVariable("courseId") String courseId){
        return courserService.deleteCourse(courseId);
    }

    @ApiOperation("分页获取课程")
    @PostMapping("/page")
    public Page<CourseInfo> getCoursePage(@RequestBody SearchCourseRequest request){
        return courserService.getCoursePage(request);
    }

    @ApiOperation("获取课程详情")
    @GetMapping("/detail/{courseId}")
    public CourseInfo getCourseDetail(@PathVariable("courseId") String courseId){
        return courserService.getCourseDetail(courseId);
    }

    @ApiOperation("获取优惠券")
    @PostMapping("/coupon/list")
    public List<CouponInfo> getCouponList(@RequestBody CouponInfoRequest request){
        return courserService.getCouponList(request);
    }

    @ApiOperation("新增优惠券")
    @PostMapping("/coupon/add")
    public String addCoupon(@RequestBody AddCouponInfoRequest request){
        return courserService.addCoupon(request);
    }
}
