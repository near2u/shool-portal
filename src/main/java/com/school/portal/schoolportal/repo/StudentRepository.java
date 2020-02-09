package com.school.portal.schoolportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.portal.schoolportal.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	Student findByUserName(String userName);

}
