package com.example.quiz.services;

import com.example.quiz.entities.Question;
import com.example.quiz.entities.Quiz;
import com.example.quiz.repositories.QuestionRepository;
import com.example.quiz.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;


    //public Question addQuestionToQuiz(Long quizId, Question question) {
      //  Quiz quiz = quizRepository.findById(quizId).orElse(null);
     //   if (quiz != null) {
      //      question.setQuiz(quiz);
     //       return questionRepository.save(question);
    //    } else {
    //        return null;
   //     }
 //   }
    @Transactional
    public Question addQuestionToQuiz(Long quizId, Question question) {
        // Fetch the quiz by ID
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        // Set the quiz to the question
        question.setQuiz(quiz);

        // Log the question data to ensure it's not null
        System.out.println("Adding question: " + question.toString());

        // Save the question and return the saved object
        return questionRepository.save(question);
    }


    public List<Question> getQuestionsByQuizId(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }
}