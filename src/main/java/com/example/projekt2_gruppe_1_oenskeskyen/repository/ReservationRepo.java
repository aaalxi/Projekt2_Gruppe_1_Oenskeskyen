package com.example.projekt2_gruppe_1_oenskeskyen.repository;

import com.example.projekt2_gruppe_1_oenskeskyen.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ReservationRepo {

    @Autowired
    DataSource dataSource;

    public void createReservationForWish(int wishID, int reservingUserID) {
        String sql = "INSERT INTO reservation (wish_id, reserved_by_user_id) VALUES(?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, wishID);
            statement.setInt(2, reservingUserID);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Reservation getReservationByWishID(int wishID) {
        String sql = "SELECT * FROM reservation WHERE wish_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, wishID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Reservation reservation = new Reservation();

                reservation.setId(resultSet.getInt("id"));
                reservation.setWishID(resultSet.getInt("wish_id"));
                reservation.setReservedByUserID(resultSet.getInt("reserved_by_user_id"));
                reservation.setReservedAt(resultSet.getTimestamp("reserved_at").toLocalDateTime());

                return reservation;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteReservationForWish(int wishID) {
        String sql = "DELETE FROM reservation WHERE wish_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, wishID);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
