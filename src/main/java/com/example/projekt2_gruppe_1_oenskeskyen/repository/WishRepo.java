package com.example.projekt2_gruppe_1_oenskeskyen.repository;

import com.example.projekt2_gruppe_1_oenskeskyen.model.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


@Repository
public class WishRepo {

    @Autowired
    DataSource dataSource;

    public void deleteById(int id) {
        String sql = "DELETE FROM wish WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Wish findById(int id) {
        String sql = "SELECT * FROM wish WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Wish(
                        rs.getInt("id"),
                        rs.getInt("wish_list_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("priority"),
                        rs.getObject("created_at", LocalDateTime.class)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteReservationByWishId(int wishId) {
        String sql = "DELETE FROM reservation WHERE wish_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, wishId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}