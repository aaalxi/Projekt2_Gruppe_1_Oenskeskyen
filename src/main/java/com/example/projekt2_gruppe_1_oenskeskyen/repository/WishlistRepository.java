package com.example.projekt2_gruppe_1_oenskeskyen.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Repository
public class WishlistRepository {

    @Autowired
    DataSource dataSource;

    public ArrayList<Wishlist> getWishlistsbyUserID(int userID){
        ArrayList<Wishlist> list = new ArrayList<>();
        String sql = "SELECT * FROM wish_list WHERE user_id = ?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, userID);

            try (ResultSet rs = statement.executeQuery()){
                while (rs.next()){
                    Wishlist wishlist = new Wishlist(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("title"),
                            rs.getString("share_token"),
                            rs.getTimestamp("created_at").toLocalDateTime()
                    );
                }
            }
        }
    }

    public void deleteWishlistByID(int id){
        String sql = "DELETE * FROM wish_list WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
