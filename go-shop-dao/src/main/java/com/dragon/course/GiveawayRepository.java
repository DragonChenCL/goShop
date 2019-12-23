package com.dragon.course;

import com.dragon.entity.course.Giveaway;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiveawayRepository extends JpaRepository<Giveaway,String> {
}
