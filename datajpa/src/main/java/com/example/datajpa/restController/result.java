package com.example.datajpa.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.datajpa.entity.student;
import com.example.datajpa.service.studentService;

import java.util.List;

@RestController
public class result {

    @Autowired
    private studentService service;


    @GetMapping("/")
    public List<student> show(){
        return service.getAll();
    }
}
