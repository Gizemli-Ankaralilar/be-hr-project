package com.team1.repository.entity;

import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {


}
