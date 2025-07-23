package com.milan.quizApp.Repository;

import com.milan.quizApp.Entity.Difficulty;
import com.milan.quizApp.Entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface QuestionsRepo extends JpaRepository<Questions,Integer> {
    List<Questions> findByCategory(String category);
    List<Questions> findByCategoryAndDifficulty(String category, Difficulty difficulty);
}
