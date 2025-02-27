package com.example.quiz.controllers;

import com.example.quiz.entities.Quiz;
import com.example.quiz.entities.Question;
import com.example.quiz.repositories.QuestionRepository;
import com.example.quiz.services.QuizService;
import com.example.quiz.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;


    @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.createQuiz(quiz));
    }


    @PostMapping("/{quizId}/add-question")
    public ResponseEntity<Question> addQuestionToQuiz(@PathVariable Long quizId, @RequestBody Question question) {
        return ResponseEntity.ok(questionService.addQuestionToQuiz(quizId, question));
    }


    @GetMapping("/all")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<List<Question>> getQuestionsByQuiz(@PathVariable Long quizId) {
        List<Question> questions = questionService.getQuestionsByQuizId(quizId);
        System.out.println("Returning Questions: " + questions);
        return ResponseEntity.ok(questions);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long questionId) {
        if (questionRepository.existsById(questionId)) {
            questionRepository.deleteById(questionId);
            return ResponseEntity.ok("Question deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found.");
        }
    }

    @PutMapping("/question/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question updatedQuestion) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);

        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            question.setQuizQuestion(updatedQuestion.getQuizQuestion());
            question.setChoices(updatedQuestion.getChoices());
            question.setCorrectOption(updatedQuestion.getCorrectOption());

            questionRepository.save(question);
            return ResponseEntity.ok(question);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}