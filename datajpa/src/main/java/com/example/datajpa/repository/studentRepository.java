package com.example.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.datajpa.entity.student;

@Repository
public interface studentRepository extends JpaRepository<student, Integer>{
    

}
