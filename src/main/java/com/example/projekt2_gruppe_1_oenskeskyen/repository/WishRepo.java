package com.example.projekt2_gruppe_1_oenskeskyen.repository;


import com.example.projekt2_gruppe_1_oenskeskyen.model.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class WishRepo {

    @Autowired                          //opretter datasource objekt inde i klassen
    DataSource dataSource;

    public void createWish (Wish wish) {
        String sql = "INSERT INTO wish (wish_list_id, name, description, url, price, priority) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, wish.getWishListID());
            statement.setString(2, wish.getName());   //skal stemme overens med række i parameteroverførsel
            statement.setString(3, wish.getDescription());
            statement.setString(4, wish.getUrl());
            statement.setDouble(5, wish.getPrice());
            statement.setInt(6, wish.getPriority());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Wish findWishByWishID (int id) {
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

    public ArrayList<Wish> getWishesByWishlistID(int wishlistID){
        ArrayList<Wish> list = new ArrayList<>();
        String sql = "SELECT * FROM wish WHERE wish_list_id = ?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, wishlistID);

            try (ResultSet rs = statement.executeQuery()){
                while (rs.next()) {
                    Wish wish = new Wish(
                            rs.getInt("id"),
                            rs.getInt("wish_list_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("url"),
                            rs.getDouble("price"),
                            rs.getInt("priority"),
                            rs.getTimestamp("created_at").toLocalDateTime()
                    );
                    list.add(wish);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}