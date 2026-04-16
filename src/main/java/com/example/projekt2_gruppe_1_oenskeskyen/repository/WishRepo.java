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
import java.time.LocalDateTime;

@Repository
public class WishRepo {

    @Autowired                          //opretter datasource objekt inde i klassen
    DataSource dataSource;

    public void createWish (Wish wish) {
        String sql = "INSERT INTO wish (wish_list_id, name, description, url, price, currency, priority) VALUES (?, ?, ?, ?, ?,?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, wish.getWishListID());
            statement.setString(2, wish.getName());   //skal stemme overens med række i parameteroverførsel
            statement.setString(3, wish.getDescription());
            statement.setString(4, wish.getUrl());
            statement.setDouble(5, wish.getPrice());
            statement.setString(6,wish.getCurrency());
            statement.setInt(7, wish.getPriority());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Wish findWishByWishID(int id) {
        Wish wish = null;
        String sql = "SELECT * FROM wish WHERE id = ?";

        try (Connection connection = dataSource.getConnection();            //lukker auto. forbindelser
             PreparedStatement statement = connection.prepareStatement(sql)) {    //beskytter mod SQL injection

            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    wish = new Wish(
                            result.getInt("id"),
                            result.getInt("wish_list_id"),
                            result.getString("name"),
                            result.getString("description"),
                            result.getString("url"),
                            result.getDouble("price"),
                            result.getString("currency"),
                            result.getInt("priority"),
                            result.getTimestamp("created_at").toLocalDateTime()
                    );
                }
                if (wish == null) {
                    System.out.println("No wish found with id " + id);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wish;
    }

    public ArrayList<Wish> getWishesByWishlistID(int wishlistID) {
        ArrayList<Wish> list = new ArrayList<>();
        String sql = "SELECT * FROM wish WHERE wish_list_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, wishlistID);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Wish wish = new Wish(
                            rs.getInt("id"),
                            rs.getInt("wish_list_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("url"),
                            rs.getDouble("price"),
                            rs.getString("currency"),
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

    public void deleteWishByWishId(int id) {
        String sql = "DELETE FROM wish WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
}
    public void updateWishById (Wish wish) {
        String sql = "UPDATE wish SET name = ?, description = ?, url = ?, price = ?, currency = ?, priority = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, wish.getName());
            statement.setString(2, wish.getDescription());
            statement.setString(3, wish.getUrl());
            statement.setDouble(4, wish.getPrice());
            statement.setString(5, wish.getCurrency());
            statement.setInt(6, wish.getPriority());
            statement.setInt(7, wish.getID());


            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}