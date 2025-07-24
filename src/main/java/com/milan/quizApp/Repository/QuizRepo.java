package com.milan.quizApp.Repository;

import com.milan.quizApp.Entity.Quiz;

import org.springframework.data.jpa.repository.JpaRepository;


public interface QuizRepo extends JpaRepository<Quiz,Integer> {

}
