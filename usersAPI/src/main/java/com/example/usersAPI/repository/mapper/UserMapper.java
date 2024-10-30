package com.example.usersAPI.repository.mapper;

import com.example.usersAPI.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setEmail(rs.getString("email"));
        user.setAge(rs.getInt("age"));
        user.setAddress(rs.getString("address"));
        user.setDateOfJoin(rs.getDate("date_of_join"));
        return user;
    }
}
