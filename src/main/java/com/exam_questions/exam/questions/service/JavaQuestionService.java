package com.exam_questions.exam.questions.service;

import com.exam_questions.exam.questions.exception.ExceptionEmpty;
import com.exam_questions.exam.questions.exception.QuestionAlreadyException;
import com.exam_questions.exam.questions.exception.QuestionNotFoundException;
import com.exam_questions.exam.questions.interf.QuestionService;
import com.exam_questions.exam.questions.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (!questions.add(question)) {
            throw new QuestionAlreadyException();
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)) {
            throw new QuestionNotFoundException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new ExceptionEmpty();
        }
        int random = ThreadLocalRandom.current().nextInt(questions.size());
        return questions.stream()
                .skip(random)
                .findFirst()
                .orElseThrow(ExceptionEmpty::new);
    }
}
