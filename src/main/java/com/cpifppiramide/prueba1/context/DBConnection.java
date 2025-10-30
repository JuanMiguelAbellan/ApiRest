package com.cpifppiramide.prueba1.context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {


    private static Connection connection;

    private DBConnection(){}

    public static Connection getInstance() {
        if(connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://rdsservidor.csf3z3itqpav.us-east-1.rds.amazonaws.com:3306/examen",
                        "admin", "Qzmpwxno1029.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

}
