package com.studentregistration.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static final String HOST = "localhost";
    private static final String PORT = "3306";          // use 3307 if you changed XAMPP
    private static final String DB   = "student_registration";
    private static final String USER = "root";          // XAMPP default
    private static final String PASS = "";              // blank if not set

    private static final String URL =
            "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB + "?useSSL=false&serverTimezone=UTC";

    public static Connection get() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
