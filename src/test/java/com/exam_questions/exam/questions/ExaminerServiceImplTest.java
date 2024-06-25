package com.exam_questions.exam.questions;


import com.exam_questions.exam.questions.exception.ExceptionAmound;
import com.exam_questions.exam.questions.exception.QuestionAlreadyException;
import com.exam_questions.exam.questions.interf.QuestionService;
import com.exam_questions.exam.questions.model.Question;
import com.exam_questions.exam.questions.service.ExaminerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    private final List<Question> questions = List.of(
            new Question("Вопрос 1", "Ответ 1"),
            new Question("Вопрос 2", "Ответ 2"),
            new Question("Вопрос 3", "Ответ 3"),
            new Question("Вопрос 4", "Ответ 4"),
            new Question("Вопрос 5", "Ответ 5")
    );

    @BeforeEach
    public void beforeEach() {
        when(questionService.getAll()).thenReturn(questions);
    }

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    public void getQuestionsNegativeTest() {
        assertThatExceptionOfType(ExceptionAmound.class)
                .isThrownBy(() -> examinerService.getQuestions(6));

        assertThatExceptionOfType(ExceptionAmound.class)
                .isThrownBy(() -> examinerService.getQuestions(0));
    }

    @Test
    public void getQuestionsTest() {
        when(questionService.getRandomQuestion()).thenReturn(
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 5", "Ответ 5"),
                new Question("Вопрос 2", "Ответ 2"),
                new Question("Вопрос 3", "Ответ 3"),
                new Question("Вопрос 3", "Ответ 3"),
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 4", "Ответ 4"),
                new Question("Вопрос 2", "Ответ 2"),
                new Question("Вопрос 5", "Ответ 5"));
        assertThat(examinerService.getQuestions(5)).containsExactlyInAnyOrder(
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 2", "Ответ 2"),
                new Question("Вопрос 3", "Ответ 3"),
                new Question("Вопрос 4", "Ответ 4"),
                new Question("Вопрос 5", "Ответ 5")
        );
    }
}
