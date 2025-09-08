package com.studentregistration.dao;

import com.studentregistration.model.Student;
import com.studentregistration.util.DatabaseConnection;
import java.sql.*;
import java.util.*;

public class StudentDao {
    public void insert(Student s) throws Exception {
        String sql = "INSERT INTO students (reg_no,name,email,course,dob,gender) VALUES (?,?,?,?,?,?)";
        try (Connection con = DatabaseConnection.get();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getRegNo());
            ps.setString(2, s.getName());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getCourse());
            ps.setString(5, s.getDob()); // YYYY-MM-DD
            ps.setString(6, s.getGender());
            ps.executeUpdate();
        }
    }

    public List<Student> findAll() throws Exception {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT reg_no,name,email,course,dob,gender FROM students ORDER BY id DESC";
        try (Connection con = DatabaseConnection.get();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Student(
                        rs.getString("reg_no"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("course"),
                        rs.getString("dob"),
                        rs.getString("gender")));
            }
        }
        return list;
    }

    public void deleteByRegNo(String regNo) throws Exception {
        try (Connection con = DatabaseConnection.get();
             PreparedStatement ps = con.prepareStatement("DELETE FROM students WHERE reg_no=?")) {
            ps.setString(1, regNo);
            ps.executeUpdate();
        }
    }
}
