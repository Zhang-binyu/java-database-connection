package com.example.jdbctemplate.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
public class page {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public List<Map<String, Object>> check(){
        return jdbcTemplate.queryForList("select * from student");
    }

    @GetMapping("/add")
    public int add(){
        return jdbcTemplate.update("INSERT INTO student value (?,?,?,?)",5,"Lihua","male",16);
    }

    @GetMapping("/delete")
    public int delete(){
        return jdbcTemplate.update("DELETE from student where number = ?",5);
    }

    @GetMapping("/findAllByNumber")
    public List<Map<String,Object>> findByNumber(){
        int number = 2;
        return jdbcTemplate.queryForList("select * from student where number = ?", number);
    }

    @GetMapping("/findAllByName")
    public List<Map<String,Object>> findByName(){
        String name = "mike";
        return jdbcTemplate.queryForList("select * from student where name = ?",name);
    }

    @GetMapping("/findNumberByName")
    public int findIdByName(){
        String name = "lisa";
        String sql = "select number from student where name = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, name);
    }
}
