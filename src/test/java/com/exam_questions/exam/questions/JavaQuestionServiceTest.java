package com.exam_questions.exam.questions;

import com.exam_questions.exam.questions.exception.ExceptionEmpty;
import com.exam_questions.exam.questions.exception.QuestionAlreadyException;
import com.exam_questions.exam.questions.exception.QuestionNotFoundException;
import com.exam_questions.exam.questions.interf.QuestionService;
import com.exam_questions.exam.questions.model.Question;
import com.exam_questions.exam.questions.service.JavaQuestionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class JavaQuestionServiceTest {

    public QuestionService questionService = new JavaQuestionService();

    private final List<Question> questions = List.of(
            new Question("Вопрос 1", "Ответ 1"),
            new Question("Вопрос 2", "Ответ 2"),
            new Question("Вопрос 3", "Ответ 3"),
            new Question("Вопрос 4", "Ответ 4"),
            new Question("Вопрос 5", "Ответ 5")
    );

    @BeforeEach
    public void beforeEach() {
        questions.forEach(questionService::add);
    }

    @AfterEach
    public void afterEach() {
        new ArrayList<>(questionService.getAll()).forEach(questionService::remove);
    }

    @Test
    public void addTest() {
        int number = questionService.getAll().size();

        Question expected = new Question("Вопрос 11", "Ответ 11");
        assertThat(questionService.getAll()).doesNotContain(expected);
        Question actual = questionService.add(new Question("Вопрос 11", "Ответ 11"));

        assertThat(actual).isEqualTo(expected);
        assertThat(questionService.getAll()).contains(expected);
        assertThat(questionService.getAll()).hasSize(number + 1);
    }

    @Test
    public void addSecondTest() {
        int number = questionService.getAll().size();

        Question expected = new Question("Вопрос 11", "Ответ 11");
        assertThat(questionService.getAll()).doesNotContain(expected);
        Question actual = questionService.add("Вопрос 11", "Ответ 11");

        assertThat(actual).isEqualTo(expected);
        assertThat(questionService.getAll()).contains(expected);
        assertThat(questionService.getAll()).hasSize(number + 1);
    }

    @Test
    public void addNegativeTest() {
        assertThatExceptionOfType(QuestionAlreadyException.class)
                .isThrownBy(() -> questionService.add(new Question("Вопрос 1", "Ответ 1")));
    }

    @Test
    public void addSecondNegativeTest() {
        assertThatExceptionOfType(QuestionAlreadyException.class)
                .isThrownBy(() -> questionService.add("Вопрос 1", "Ответ 1"));
    }

    @Test
    public void removeTest() {
        int number = questionService.getAll().size();

        Question expected = new Question("Вопрос 3", "Ответ 3");
        assertThat(questionService.getAll()).contains(expected);

        Question actual = questionService.remove(new Question("Вопрос 3", "Ответ 3"));

        assertThat(actual).isEqualTo(expected);
        assertThat(questionService.getAll()).doesNotContain(expected);
        assertThat(questionService.getAll()).hasSize(number - 1);
    }

    @Test
    public void removeNegativeTest() {
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(new Question("Вопрос 6", "Ответ 6")));
    }

    @Test
    public void getAllTest() {
        assertThat(questionService.getAll()).containsExactlyInAnyOrderElementsOf(questions);

    }

    @Test
    public void getRandomQuestionTest() {
        assertThat(questionService.getRandomQuestion()).isIn(questionService.getAll());

    }

    @Test
    public void getRandomQuestionNegativeTest() {
        afterEach();

        assertThatExceptionOfType(ExceptionEmpty.class)
                .isThrownBy(questionService::getRandomQuestion);

    }
}


