package com.milan.quizApp.Repository;

import com.milan.quizApp.Entity.Difficulty;
import com.milan.quizApp.Entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface QuestionsRepo extends JpaRepository<Questions,Integer> {
    List<Questions> findByCategory(String category);
    List<Questions> findByCategoryAndDifficulty(String category, Difficulty difficulty);

    @Query(value = "SELECT * FROM questions q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Questions> findRandomQuestionByCategory(@Param("category") String category, @Param("numQ") int numQ);

}
