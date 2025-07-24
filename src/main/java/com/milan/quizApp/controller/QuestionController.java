package com.milan.quizApp.controller;


import com.milan.quizApp.Entity.Difficulty;
import com.milan.quizApp.Entity.Questions;
import com.milan.quizApp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Questions>> getAllQuestion(){
        try{
        return new ResponseEntity<>(questionService.getAllQuestion(),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "filter",params = {"category"})
    public ResponseEntity<List<Questions>> getQuestionByCategory(@RequestParam String category){
        try{
            return new ResponseEntity<>(questionService.getCategoryQuestion(category),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "filter",params = {"category","difficulty"})
    public ResponseEntity<List<Questions>> getQuestionsByCategoryAndDifficulty(@RequestParam String category,@RequestParam Difficulty difficulty ){
        try{
            return new ResponseEntity<>(questionService.getDifficultyAndCategoryQuestion(category,difficulty),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("addQuestions")
    public ResponseEntity<?> addQuestions(@RequestBody Questions questions){
        try{
            questionService.addQuestions(questions);
            return new ResponseEntity<>(questions,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }
}
