package com.example.pollQuestionAPI.repository;

import com.example.pollQuestionAPI.model.Question;
import com.example.pollQuestionAPI.repository.mapper.QuestionMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
public class QuestionRepository {
    private final String TABLE = "questions";
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Question save (Question question){
        try {
            String sql = "INSERT INTO " + TABLE + " (question, answer_a, answer_b, answer_c, answer_d) VALUES (?, ?, ?, ?, ?);";
            jdbcTemplate.update(sql, question.getQuestion(), question.getAnswerA(), question.getAnswerB(), question.getAnswerC(), question.getAnswerD());
//            System.out.println(question.getQuestion());
            Question createdQuestion = this.getByQuestion(question.getQuestion());
            return createdQuestion;
        }catch (DataIntegrityViolationException e){
            throw e;
        }
    }

    public Question update (Question question){
        try{
            String sql = "UPDATE " + TABLE + " SET question = ?, answer_a = ?, answer_b = ?, answer_c = ?, answer_d = ? WHERE id = ?";
            jdbcTemplate.update(sql, question.getQuestion(), question.getAnswerA(), question.getAnswerB(), question.getAnswerC(), question.getAnswerD(), question.getId());
            return this.getByID(question.getId());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Question deleteByID (int id){
        try{
            String sql = "DELETE FROM " + TABLE + " WHERE id =?";
            Question deletedQuestion = this.getByID(id);
            jdbcTemplate.update(sql, id);
            return deletedQuestion;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Question getByID (int id){
        try{
            String sql = "SELECT * FROM " + TABLE + " WHERE id =?";
            return jdbcTemplate.queryForObject(sql, new QuestionMapper(), id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Question getByQuestion (String question){
        try{
            String sql = "SELECT * FROM " + TABLE + " WHERE question = ?";
//            System.out.println("parameter: " + question + "\nsql tring: " + sql + "\nTABLE: " + TABLE);
            return jdbcTemplate.queryForObject(sql, new QuestionMapper(), question);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Question> getAllQuestions(){
        try{
            String sql = "SELECT * FROM " + TABLE;
            return jdbcTemplate.query(sql, new QuestionMapper());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
