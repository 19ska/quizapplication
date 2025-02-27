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


    public Question addQuestionToQuiz(Long quizId, Question question) {
        Quiz quiz = quizRepository.findById(quizId).orElse(null);
        if (quiz != null) {
            question.setQuiz(quiz);
            return questionRepository.save(question);
        } else {
            return null;
        }
    }


    public List<Question> getQuestionsByQuizId(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }
}