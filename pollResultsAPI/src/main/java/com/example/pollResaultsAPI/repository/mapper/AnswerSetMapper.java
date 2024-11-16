package com.example.pollResaultsAPI.repository.mapper;

import com.example.pollResaultsAPI.model.AnswerSet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;

public class AnswerSetMapper implements RowMapper<AnswerSet> {
    @Override
    public AnswerSet mapRow(java.sql.ResultSet rs, int rowNum) throws SQLException {
        AnswerSet answerSet = new AnswerSet();
        answerSet.setId(rs.getInt("id"));
        answerSet.setQuestionRef(rs.getInt("question_ref"));
        answerSet.setAnswerGiven(rs.getString("answer").charAt(0));
        answerSet.setUserId(rs.getInt("user_id"));
        return answerSet;
    }
}
