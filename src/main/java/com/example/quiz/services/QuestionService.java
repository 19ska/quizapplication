package com.example.quiz.services;

import com.example.quiz.entities.Question;
import com.example.quiz.entities.Quiz;
import com.example.quiz.repositories.QuestionRepository;
import com.example.quiz.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    // Add a question to a quiz
    public Question addQuestionToQuiz(Long quizId, Question question) {
        Quiz quiz = quizRepository.findById(quizId).orElse(null);
        if (quiz != null) {
            question.setQuiz(quiz); // Set the quiz for the question
            return questionRepository.save(question);
        } else {
            return null; // Quiz not found
        }
    }

    // Get questions by quiz ID
    public List<Question> getQuestionsByQuizId(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }
}