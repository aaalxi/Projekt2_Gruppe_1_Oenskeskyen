package com.example.projekt2_gruppe_1_oenskeskyen.repository;


import com.example.projekt2_gruppe_1_oenskeskyen.model.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class WishRepo {

    @Autowired                          //opretter datasource objekt inde i klassen
    DataSource dataSource;



    public void createWish (Wish wish) {
        String sql = "INSERT INTO wish (name, description, url, price, priority) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, wish.getName());   //skal stemme overens med række i parameteroverførsel
            statement.setString(2, wish.getDescription());
            statement.setString(3, wish.getUrl());
            statement.setDouble(4, wish.getPrice());
            statement.setInt(5, wish.getPriority());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public Wish findByWishID (int id) {
        Wish wish = null;
        String sql = "SELECT * FROM wish WHERE id = ?";

        try (Connection connection = dataSource.getConnection();            //lukker auto. forbindelser
        PreparedStatement statement = connection.prepareStatement(sql)){    //beskytter mod SQL injection

            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    wish = new Wish();      //skal bruge en tom konstruktør
                    wish.setName(result.getString("name"));
                    wish.setDescription(result.getString("description"));
                    wish.setUrl(result.getString("url"));
                    wish.setPrice(result.getDouble("price"));
                    wish.setPriority(result.getInt("priority"));
                    wish.setCreatedAt(result.getTimestamp("created_at").toLocalDateTime());
                }
                if (wish == null) { System.out.println("No wish found with id " + id); }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wish;
    }

}