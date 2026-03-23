package Session12.Bai2;

import java.sql.*;
import java.util.Scanner;

public class Bai2 {
    public static void main(String[] args) {

        String url = "jdbc:mysql://127.0.0.1:3306/hospital";
        String user = "root";
        String password = "123456";

        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap id benh nhan ");
        int patientId = sc.nextInt();

        System.out.print("Nhap nhiet do ");
        double temp = sc.nextDouble();

        System.out.print("Nhap nhip tim ");
        int heartRate = sc.nextInt();

        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            // PreparedStatement giup xu ly dung kieu du lieu
            // setDouble va setInt tu dong chuyen doi dung dinh dang so
            // khong phu thuoc vao dau cham hay dau phay cua he dieu hanh
            // tranh loi sai cu phap SQL khi noi chuoi

            String sql = "UPDATE Vitals SET temperature = ?, heart_rate = ? WHERE p_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            // Truyen dung kieu du lieu vao SQL
            ps.setDouble(1, temp);
            ps.setInt(2, heartRate);
            ps.setInt(3, patientId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Cap nhat thanh cong");
            } else {
                System.out.println("Khong tim thay benh nhan");
            }

            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}