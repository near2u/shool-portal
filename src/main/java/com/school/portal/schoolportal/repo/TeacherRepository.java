package com.school.portal.schoolportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.portal.schoolportal.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

	public Teacher findByUserName(String userName);

}
