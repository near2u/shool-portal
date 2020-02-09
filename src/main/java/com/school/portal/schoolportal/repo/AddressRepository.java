package com.school.portal.schoolportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.portal.schoolportal.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
