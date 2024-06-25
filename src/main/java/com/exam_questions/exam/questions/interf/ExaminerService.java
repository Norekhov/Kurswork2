package com.exam_questions.exam.questions.interf;

import com.exam_questions.exam.questions.model.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);
}
