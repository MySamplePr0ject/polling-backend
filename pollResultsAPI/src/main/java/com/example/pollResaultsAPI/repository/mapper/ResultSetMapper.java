package com.example.pollResaultsAPI.repository.mapper;

import com.example.pollResaultsAPI.model.ResultSet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;

public class ResultSetMapper implements RowMapper<ResultSet> {
    @Override
    public ResultSet mapRow(java.sql.ResultSet rs, int rowNum) throws SQLException {
        ResultSet resultSet = new ResultSet();
        resultSet.setId(rs.getInt("id"));
        resultSet.setQuestionRef(rs.getInt("question_ref"));
        resultSet.setAnswerGiven(rs.getString("answer"));
        resultSet.setUserId(rs.getInt("user_id"));
        return null;
    }
}
