package com.example.quiz.services;

import com.example.quiz.entities.Quiz;
import com.example.quiz.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;


    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }


    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }


    public Quiz getQuizById(Long quizId) {
        return quizRepository.findById(quizId).orElse(null);
    }

    public Quiz findById(Long quizId) {
        return quizRepository.findById(quizId).orElse(null);
    }
}