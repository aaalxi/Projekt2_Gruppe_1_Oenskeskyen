package com.example.projekt2_gruppe_1_oenskeskyen.repository;

import com.example.projekt2_gruppe_1_oenskeskyen.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.sql.Date.valueOf;

@Repository
public class UserRepo {

    @Autowired
    DataSource dataSource;

    public User getUserbyID(int id){
        User user = null;
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getString("email"),
                            resultSet.getDate("birthday").toLocalDate(),
                            resultSet.getTimestamp("created_at").toLocalDateTime()
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User findByUsername(String username){
        String sql = "SELECT * FROM users WHERE username = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                User user = new User();

                user.setID(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setBirthday(resultSet.getTimestamp("birthday").toLocalDateTime().toLocalDate());
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));

                return user;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void createUser(User user){
        String sql = "INSERT INTO users (username, email, password, birthday) VALUES(?,?,?,?)";

        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1,user.getUsername());
            statement.setString(2,user.getEmail());
            statement.setString(3,user.getPassword());
            statement.setDate(4,valueOf(user.getBirthday()));

            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public User findByEmail(String email){

        String sql = "SELECT * FROM users WHERE email = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                User user = new User();

                user.setID(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setBirthday(resultSet.getTimestamp("birthday").toLocalDateTime().toLocalDate());
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));

                return user;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void updateUser(User user){
        String sql = "UPDATE users SET username = ? WHERE id = ?";

        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, user.getUsername());
            statement.setInt(2, user.getId());

            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
