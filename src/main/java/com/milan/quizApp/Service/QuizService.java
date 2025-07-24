package com.milan.quizApp.Service;

import com.milan.quizApp.Entity.Questions;
import com.milan.quizApp.Entity.Quiz;
import com.milan.quizApp.dto.QuestionDTO;
import com.milan.quizApp.Repository.QuestionsRepo;
import com.milan.quizApp.Repository.QuizRepo;
import com.milan.quizApp.dto.SubmitRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {
    @Autowired
    private QuizRepo quizRepo;
    @Autowired
    private QuestionsRepo questionsRepo;

    public void createQuiz(String category, int numQ, String title) {
        List<Questions> questions = questionsRepo.findRandomQuestionByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepo.save(quiz);
    }

    public ResponseEntity<List<QuestionDTO>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizRepo.findById(id);
        List<Questions> questionsfromDB = quiz.get().getQuestions();

        List<QuestionDTO> questionsForUsers = new ArrayList<>();

        for (Questions q : questionsfromDB) {
            QuestionDTO qw = new QuestionDTO(q.getId(), q.getQuestion(), q.getOptions());
            questionsForUsers.add(qw);
        }
        return new ResponseEntity<>(questionsForUsers, HttpStatus.OK);

    }

    public int calculateScore(Integer id, List<SubmitRequest> responses) {
        Optional<Quiz> quiz = quizRepo.findById(id);
        List<Questions> questions = quiz.get().getQuestions();
        int right = 0;
        int i = 0;
        for (SubmitRequest response : responses) {
            if (response.getResponses().equals(questions.get(i).getCorrectAnswer()))
                right++;

            i++;
        }
        return right;
    }
}

