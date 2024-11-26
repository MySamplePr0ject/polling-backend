package com.example.pollResaultsAPI.repository;

import com.example.pollResaultsAPI.model.AnswerSet;
import com.example.pollResaultsAPI.repository.mapper.AnswerSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Locale;

@Repository
public class AnswerSetRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String TABLE = "results";

    public AnswerSet save (AnswerSet answerSet){
        try {
            String sql = "INSERT INTO " + TABLE + " (question_ref, answer, user_id) VALUES ( ?, ?, ? )";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update( connection -> {
                        PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
                        ps.setInt(1, answerSet.getQuestionRef());
                        ps.setString(2, String.valueOf(answerSet.getAnswerGiven()));
                        ps.setInt(3, answerSet.getUserId());
                        return ps;
                    },keyHolder);
            int newId = keyHolder.getKey().intValue();
//            System.out.println(newId);
            return getResultSetById(newId);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public AnswerSet update (AnswerSet answerSet){
        try {
            String sql = "UPDATE " + TABLE + " SET question_ref = ?, answer = ?, user_id = ? WHERE id = ?";
            jdbcTemplate.update(sql, answerSet.getQuestionRef(), answerSet.getAnswerGiven(), answerSet.getUserId(), answerSet.getId());
            return getResultSetById(answerSet.getId());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public AnswerSet delete (int id){
        try {
            String sql = "DELETE FROM " + TABLE + " WHERE id = ?";
            AnswerSet deletedResponse = getResultSetById(id);
            jdbcTemplate.update(sql, id);
            return deletedResponse;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<AnswerSet> getResultsByUser (int userId){
        try {
            String sql = "SELECT * FROM " + TABLE + " WHERE user_id = ?";
            return jdbcTemplate.query(sql, new AnswerSetMapper(), userId);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<AnswerSet> getResultsByQuestion (int questionId){
        try {
            String sql = "SELECT * FROM " + TABLE + " WHERE question_ref = ?";
//            System.out.println(jdbcTemplate.query(sql, new AnswerSetMapper(), questionId));
            return jdbcTemplate.query(sql, new AnswerSetMapper(), questionId);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public AnswerSet getResultSetById (int id){
        try {
            String sql = "SELECT * FROM " + TABLE + " WHERE id = ?";
//            System.out.println(jdbcTemplate.queryForObject(sql, new AnswerSetMapper(), id));
            return jdbcTemplate.queryForObject(sql, new AnswerSetMapper(), id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    //Handles answers deletion upon user deletion.
    public List<AnswerSet> deleteAllUserAnswers (int id){
        try{
            List<AnswerSet> deletedResponses = getResultsByUser(id);
            String sql = "DELETE FROM " + TABLE + " WHERE user_id = ?";
            jdbcTemplate.update(sql, id);
            return deletedResponses;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
