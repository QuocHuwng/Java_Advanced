package Session14.KiemTra;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static String url = "jdbc:mysql://127.0.0.1:3306/bank";
    static String user = "root";
    static String password = "123456";

    public static void main(String[] args) {
        Connection conn = null;
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);

            System.out.print("From account: ");
            String fromId = sc.nextLine().trim();

            System.out.print("To account: ");
            String toId = sc.nextLine().trim();

            System.out.print("Amount: ");
            double amount = sc.nextDouble();

            conn.setAutoCommit(false);

            PreparedStatement check = conn.prepareStatement(
                    "SELECT Balance FROM Accounts WHERE AccountId = ?"
            );

            check.setString(1, fromId);
            ResultSet rs = check.executeQuery();

            if (!rs.next()) {
                System.out.println("Tai khoan gui khong ton tai");
                conn.rollback();
                return;
            }

            double balance = rs.getDouble("Balance");

            if (balance < amount) {
                System.out.println("Khong du tien");
                conn.rollback();
                return;
            }

            check.setString(1, toId);
            ResultSet rs2 = check.executeQuery();

            if (!rs2.next()) {
                System.out.println("Tai khoan nhan khong ton tai");
                conn.rollback();
                return;
            }

            PreparedStatement update = conn.prepareStatement(
                    "UPDATE Accounts SET Balance = Balance + ? WHERE AccountId = ?"
            );

            update.setDouble(1, -amount);
            update.setString(2, fromId);
            update.executeUpdate();

            update.setDouble(1, amount);
            update.setString(2, toId);
            update.executeUpdate();

            conn.commit();

            System.out.println("Chuyen tien thanh cong");

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Accounts");
            ResultSet rs3 = ps.executeQuery();

            while (rs3.next()) {
                System.out.println(
                        rs3.getString("AccountId") + " - " +
                                rs3.getString("FullName") + " - " +
                                rs3.getDouble("Balance")
                );
            }

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (Exception ex) {}
            System.out.println("Giao dich that bai");
            e.printStackTrace();
        }
    }
}