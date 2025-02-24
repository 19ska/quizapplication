package com.example.quiz.controllers;


import com.example.quiz.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/question")
public class QuestionController {


    @Autowired
    private QuestionService questionService;
}
