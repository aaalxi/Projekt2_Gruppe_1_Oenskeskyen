package com.example.projekt2_gruppe_1_oenskeskyen.repository;

import com.example.projekt2_gruppe_1_oenskeskyen.model.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class WishlistRepo {

    @Autowired
    DataSource dataSource;

    public void createWishlist (Wishlist w){
        String sql = "INSERT INTO wish_list (user_id, title, share_token) VALUES (?, ?, ?)";

        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1,w.getUserID());
            statement.setString(2,w.getTitle());
            statement.setString(3, w.getShareToken());
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateWishlist (Wishlist w){
        String sql = "UPDATE wish_list SET title = ?, share_token = ? WHERE id = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, w.getTitle());
            statement.setString(2,w.getShareToken());
            statement.setInt(3, w.getID());

            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Wishlist findWishlistByID (int ID){
        String sql = "SELECT * FROM wish_list WHERE id = ?";

        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1,ID);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                Wishlist wishlist = new Wishlist();

                wishlist.setID(resultSet.getInt("id"));
                wishlist.setUserID(resultSet.getInt("user_id"));
                wishlist.setTitle(resultSet.getString("title"));
                wishlist.setShareToken(resultSet.getString("share_token"));
                wishlist.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());

                return wishlist;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}

