package com.dragon.course;

import com.dragon.entity.course.CourseComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseCommentRepository extends JpaRepository<CourseComment,String> {
}
