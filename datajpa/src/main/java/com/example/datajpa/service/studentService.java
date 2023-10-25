package com.example.datajpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.datajpa.entity.student;
import com.example.datajpa.repository.studentRepository;

@Service
public class studentService{
    @Autowired
    private studentRepository studentRepository;

    public List<student> getAll(){
        return studentRepository.findAll();
    }
}
