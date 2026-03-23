package Session12.Bai4;

import java.sql.*;
import java.util.*;

public class Bai4 {
    public static void main(String[] args) {

        String url = "jdbc:mysql://127.0.0.1:3306/hospital";
        String user = "root";
        String password = "123456";

        // Tao du lieu gia lap
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            list.add("data_" + i);
        }

        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            // Su dung PreparedStatement giup toi uu hieu nang
            // Cau SQL duoc bien dich mot lan duy nhat
            // Tranh viec database phai parse va tao execution plan nhieu lan
            // Giam tai cho server va tang toc do xu ly

            String sql = "INSERT INTO Results(data) VALUES(?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            for (String data : list) {
                // Truyen du lieu moi moi lan lap
                ps.setString(1, data);

                // Thuc thi
                ps.executeUpdate();
            }

            ps.close();
            conn.close();

            System.out.println("Insert thanh cong");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}