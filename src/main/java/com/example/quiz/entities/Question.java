package com.example.quiz.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String quizQuestion;

    @ElementCollection
    @CollectionTable(name = "question_options", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "choice")
    private List<String> choices;

    private String correctOption;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @JsonBackReference
    private Quiz quiz; // A Question belongs to a Quiz


    @Override
    public String toString() {
        return "Question{id=" + id +
                ", quizQuestion='" + quizQuestion +
                "', choices=" + choices +
                ", correctOption='" + correctOption + "'}";
    }
}