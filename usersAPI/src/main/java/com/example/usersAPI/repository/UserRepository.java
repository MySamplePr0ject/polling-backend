package com.example.usersAPI.repository;

import com.example.usersAPI.model.User;
import com.example.usersAPI.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String TABLE = "users";
    public User save (User user) throws DataAccessException{
                String sql = "INSERT INTO " + TABLE + " (name, surname, email, age, address) VALUES ( ?, ?, ?, ?, ?);";
                jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getEmail(), user.getAge(), user.getAddress());
                return getUserByEmail(user.getEmail());
    }

    public User update (User user){
        try {
            String sql = "UPDATE " + TABLE +" SET name = ?, surname = ?, email = ?, age = ?, address = ? WHERE id = ?;";
            jdbcTemplate.update (sql, user.getName(), user.getSurname(), user.getEmail(), user.getAge(), user.getAddress(), user.getId());
            return user;
        }catch (DataAccessException e){
            throw new IllegalArgumentException("E-mail already exists");
        }
    }

    public User deleteById (int id){
        try {
            User deletedUser = getUserById(id);
            String sql = "DELETE FROM " + TABLE + " WHERE id = ?";
            jdbcTemplate.update(sql, id);
            return deletedUser;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private User getUserByEmail (String email){
        try {
            String sql = "SELECT * FROM " + TABLE + " WHERE email = ?";
            return jdbcTemplate.queryForObject(sql, new UserMapper(), email);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public User getUserById (int id){
        try {
            String sql = "SELECT * FROM " + TABLE + " WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<User> getAllUsers (){
        try {
            String sql = "SELECT * FROM " + TABLE;
            return jdbcTemplate.query(sql, new UserMapper());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
