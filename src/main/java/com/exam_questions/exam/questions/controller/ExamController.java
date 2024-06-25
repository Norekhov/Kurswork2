package com.exam_questions.exam.questions.controller;


import com.exam_questions.exam.questions.interf.ExaminerService;
import com.exam_questions.exam.questions.model.Question;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ExamController {

    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/exam/get/{amount}")
    public Collection<Question> getQuestions(@PathVariable int amount) {
        return examinerService.getQuestions(amount);
    }

}
