package Session12.Bai3;

import java.sql.*;
import java.util.Scanner;

public class Bai3 {
    public static void main(String[] args) {

        String url = "jdbc:mysql://127.0.0.1:3306/hospital";
        String user = "root";
        String password = "123456";

        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap surgery id ");
        int surgeryId = sc.nextInt();

        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            // Phai dung CallableStatement de goi Stored Procedure
            CallableStatement cs = conn.prepareCall("{call GET_SURGERY_FEE(?, ?)}");

            // Tham so dau vao
            cs.setInt(1, surgeryId);

            // Phai dang ky tham so dau ra truoc khi execute
            // Neu khong se bi loi column index out of range
            // Kieu DECIMAL trong SQL tuong ung voi Types.DOUBLE hoac Types.DECIMAL trong Java
            cs.registerOutParameter(2, Types.DOUBLE);

            // Thuc thi
            cs.execute();

            // Lay gia tri dau ra
            double cost = cs.getDouble(2);

            System.out.println("Chi phi phau thuat la " + cost);

            cs.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}