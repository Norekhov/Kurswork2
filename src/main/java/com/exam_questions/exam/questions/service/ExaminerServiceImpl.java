package com.exam_questions.exam.questions.service;

import com.exam_questions.exam.questions.exception.ExceptionAmound;
import com.exam_questions.exam.questions.interf.ExaminerService;
import com.exam_questions.exam.questions.interf.QuestionService;
import com.exam_questions.exam.questions.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (questionService.getAll().size() < amount || amount <= 0) {
            throw new ExceptionAmound();
        }
        Set<Question> questions = new HashSet<>(amount);
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());
        }
        return questions;
    }
}
