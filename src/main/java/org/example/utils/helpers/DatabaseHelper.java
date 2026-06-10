package org.example.utils.helpers;

import org.example.config.Constants;
import org.example.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

import static org.example.config.Constants.*;

public final class DatabaseHelper {
    private static final Logger log = LoggerFactory.getLogger(DatabaseHelper.class);
    private static final String DB_URL = "jdbc:postgresql://%s:%s/%s";
    private static final String USER_GET_QUERY = "SELECT id, username, password, role FROM usr WHERE username = ?";
    private static final String DELETE_USER_QUERY = "DELETE FROM usr WHERE username = ?";
    private static final String DELETE_LOG_MESSAGE = "User '{}' deleted from database";

    private DatabaseHelper() {
        throw new UnsupportedOperationException(Constants.Errors.UTILITY_CLASS_INSTANTIATION);
    }

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    String.format(DB_URL,
                            Constants.Db.HOST,
                            Constants.Db.PORT,
                            Constants.Db.NAME),
                    Constants.Db.USER,
                    Constants.Db.PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(Constants.Errors.DB_CONNECTION_ERROR, e);
        }
    }

    public static UserDTO getUser(String username) {
        try (var connection = getConnection();
             var statement = connection.prepareStatement(USER_GET_QUERY)) {
            statement.setString(1, username);

            try (var rs = statement.executeQuery()) {
                return rs.next() ? new UserDTO(rs.getInt(ID),
                        rs.getString(USERNAME),
                        rs.getString(PASSWORD),
                        rs.getString(ROLE)) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(Constants.Errors.DB_QUERY_ERROR, e);
        }
    }

    public static void deleteUser(String username) {
        try (var connection = getConnection();
             var statement = connection.prepareStatement(DELETE_USER_QUERY)) {
            statement.setString(1, username);
            statement.executeUpdate();
            log.info(DELETE_LOG_MESSAGE, username);
        } catch (SQLException e) {
            throw new RuntimeException(Constants.Errors.DB_QUERY_ERROR, e);
        }
    }
}