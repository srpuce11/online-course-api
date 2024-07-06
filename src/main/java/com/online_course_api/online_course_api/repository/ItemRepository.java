package com.online_course_api.online_course_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online_course_api.online_course_api.repository.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
