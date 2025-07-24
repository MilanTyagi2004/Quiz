package com.milan.quizApp.controller;

import com.milan.quizApp.dto.QuestionDTO;
import com.milan.quizApp.Service.QuizService;
import com.milan.quizApp.dto.SubmitRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    private  QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,
                                             @RequestParam int numQ,
                                             @RequestParam String title){
        try{
            quizService.createQuiz(category,numQ,title);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionDTO>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }
    // QuizController.java
    @PostMapping("submit/{id}")
    public ResponseEntity<?> submitQuiz(@PathVariable Integer id,@RequestBody List<SubmitRequest> response) {

        return new ResponseEntity<>(quizService.calculateScore(id,response),HttpStatus.OK);
    }


}
