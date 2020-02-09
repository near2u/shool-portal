package com.school.portal.schoolportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.portal.schoolportal.model.Marks;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Integer> {

}
