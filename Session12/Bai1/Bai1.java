package Session12.Bai1;

import java.sql.*;
import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args) {

        String url = "jdbc:mysql://127.0.0.1:3306/hospital";
        String user = "root";
        String password = "123456";

        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap ma bac si ");
        String code = sc.nextLine();

        System.out.print("Nhap mat khau ");
        String pass = sc.nextLine();

        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            // PreparedStatement chong SQL Injection vi cau SQL duoc bien dich truoc
            // Cau truc lenh SQL duoc giu nguyen khong bi thay doi
            // Du lieu nhap vao duoc truyen rieng khong noi truc tiep vao cau lenh

            String sql = "SELECT * FROM Doctors WHERE code = ? AND pass = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            // Gia tri nhap vao chi la du lieu thuong
            // Khong the bien thanh cau lenh SQL de tan cong
            ps.setString(1, code);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Dang nhap thanh cong");
            } else {
                System.out.println("Sai ma hoac mat khau");
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}