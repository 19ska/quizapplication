package com.example.quiz.controllers;

import com.example.quiz.entities.Quiz;
import com.example.quiz.entities.Question;
import com.example.quiz.services.QuizService;
import com.example.quiz.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    // Endpoint to create a quiz
    @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.createQuiz(quiz));
    }

    // Endpoint to add a question to a quiz
    @PostMapping("/{quizId}/add-question")
    public ResponseEntity<Question> addQuestionToQuiz(@PathVariable Long quizId, @RequestBody Question question) {
        return ResponseEntity.ok(questionService.addQuestionToQuiz(quizId, question));
    }

    // Endpoint to fetch all quizzes
    @GetMapping("/all")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<List<Question>> getQuestionsByQuiz(@PathVariable Long quizId) {
        List<Question> questions = questionService.getQuestionsByQuizId(quizId);
        System.out.println("Returning Questions: " + questions); // ✅ Debug
        return ResponseEntity.ok(questions);
    }
}