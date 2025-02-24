package com.example.quiz.controllers;


import com.example.quiz.services.QuestionService;
import com.example.quiz.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {


    @Autowired
    private QuizService quizService;
}
