package com.example.pollQuestionAPI.repository.mapper;

import com.example.pollQuestionAPI.model.Question;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question question = new Question();
        question.setId(rs.getInt("id"));
        question.setQuestion(rs.getString("question"));
        question.setAnswerA(rs.getString("question_a"));
        question.setAnswerB(rs.getString("question_b"));
        question.setAnswerC(rs.getString("question_c"));
        question.setAnswerD(rs.getString("question_d"));
        return question;
    }
}
