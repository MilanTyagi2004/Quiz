package com.milan.quizApp.Service;

import com.milan.quizApp.Entity.Difficulty;
import com.milan.quizApp.Entity.Questions;
import com.milan.quizApp.Repository.QuestionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionsRepo questionsRepo;
    public List<Questions> getAllQuestion(){
        return questionsRepo.findAll();
    }

    public List<Questions> getCategoryQuestion(String category){
        return questionsRepo.findByCategory(category);
    }
    public List<Questions> getDifficultyAndCategoryQuestion(String category, Difficulty difficulty){
        return questionsRepo.findByCategoryAndDifficulty(category,difficulty);
    }
    public void addQuestions(Questions questions){
        questionsRepo.save((questions));
    }
}
