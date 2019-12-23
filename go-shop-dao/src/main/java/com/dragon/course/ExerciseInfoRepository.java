package com.dragon.course;

import com.dragon.entity.course.ExerciseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseInfoRepository extends JpaRepository<ExerciseInfo,String> {

}
