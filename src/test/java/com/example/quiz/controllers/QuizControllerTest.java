package com.example.quiz.controllers;

import com.example.quiz.entities.Quiz;
import com.example.quiz.entities.Question;
import com.example.quiz.repositories.QuestionRepository;
import com.example.quiz.services.QuizService;
import com.example.quiz.services.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class QuizControllerTest {

    @Mock
    private QuizService quizService;

    @Mock
    private QuestionService questionService;

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuizController quizController;


    @Test
    void testCreateQuiz() {
        Quiz quiz = new Quiz();
        quiz.setTitle("Sample Quiz");
        quiz.setDescription("Sample Quiz Description");

        when(quizService.createQuiz(any(Quiz.class))).thenReturn(quiz);

        ResponseEntity<Quiz> response = quizController.createQuiz(quiz);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Sample Quiz", response.getBody().getTitle());
    }


    @Test
    void testAddQuestionToQuiz() {
        Long quizId = 1L;
        Question question = new Question();
        question.setQuizQuestion("What is Spring?");
        question.setChoices(List.of("A", "B", "C"));
        question.setCorrectOption("A");

        when(questionService.addQuestionToQuiz(eq(quizId), any(Question.class))).thenReturn(question);

        ResponseEntity<Question> response = quizController.addQuestionToQuiz(quizId, question);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("What is Spring?", response.getBody().getQuizQuestion());
    }


    @Test
    void testGetAllQuizzes() {
        Quiz quiz1 = new Quiz();
        quiz1.setTitle("Sample Quiz 1");
        Quiz quiz2 = new Quiz();
        quiz2.setTitle("Sample Quiz 2");

        when(quizService.getAllQuizzes()).thenReturn(List.of(quiz1, quiz2));

        ResponseEntity<List<Quiz>> response = quizController.getAllQuizzes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("Sample Quiz 1", response.getBody().get(0).getTitle());
    }


    @Test
    void testGetQuestionsByQuiz() {
        Long quizId = 1L;
        Question question = new Question();
        question.setQuizQuestion("What is Java?");
        question.setChoices(List.of("Option 1", "Option 2"));
        question.setCorrectOption("Option 1");

        when(questionService.getQuestionsByQuizId(eq(quizId))).thenReturn(List.of(question));

        ResponseEntity<List<Question>> response = quizController.getQuestionsByQuiz(quizId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("What is Java?", response.getBody().get(0).getQuizQuestion());
    }


    @Test
    void testDeleteQuestion_Success() {
        Long questionId = 1L;

        when(questionRepository.existsById(questionId)).thenReturn(true);

        ResponseEntity<String> response = quizController.deleteQuestion(questionId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Question deleted successfully.", response.getBody());
    }


    @Test
    void testDeleteQuestion_NotFound() {
        Long questionId = 1L;

        when(questionRepository.existsById(questionId)).thenReturn(false);

        ResponseEntity<String> response = quizController.deleteQuestion(questionId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Question not found.", response.getBody());
    }


    @Test
    void testUpdateQuestion_Success() {
        Long questionId = 1L;
        Question existingQuestion = new Question();
        existingQuestion.setQuizQuestion("What is Spring?");
        existingQuestion.setChoices(List.of("A", "B", "C"));
        existingQuestion.setCorrectOption("A");

        Question updatedQuestion = new Question();
        updatedQuestion.setQuizQuestion("What is Java?");
        updatedQuestion.setChoices(List.of("A", "B", "C"));
        updatedQuestion.setCorrectOption("B");

        when(questionRepository.findById(questionId)).thenReturn(Optional.of(existingQuestion));
        when(questionRepository.save(any(Question.class))).thenReturn(updatedQuestion);

        ResponseEntity<Question> response = quizController.updateQuestion(questionId, updatedQuestion);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("What is Java?", response.getBody().getQuizQuestion());
        assertEquals("B", response.getBody().getCorrectOption());
    }


    @Test
    void testUpdateQuestion_NotFound() {
        Long questionId = 1L;
        Question updatedQuestion = new Question();
        updatedQuestion.setQuizQuestion("What is Python?");
        updatedQuestion.setChoices(List.of("A", "B", "C"));
        updatedQuestion.setCorrectOption("A");

        when(questionRepository.findById(questionId)).thenReturn(Optional.empty());

        ResponseEntity<Question> response = quizController.updateQuestion(questionId, updatedQuestion);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


}