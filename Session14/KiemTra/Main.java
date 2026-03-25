package Session14.KiemTra;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static String url = "jdbc:mysql://127.0.0.1:3306/bank";
    static String user = "root";
    static String password = "123456";

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        Scanner sc = new Scanner(System.in);
        System.out.print("From account: ");
        String fromId = sc.nextLine();
        System.out.print("To account: ");
        String toId = sc.nextLine();
        System.out.print("Amount: ");
        double amount = sc.nextDouble();
        try{
            conn.setAutoCommit(false);
            PreparedStatement check = conn.prepareStatement(
                    "SELECT Balance FROM Accounts WHERE AccountId = ?"
            );
            check.setString(1, fromId);
            ResultSet rs = check.executeQuery();
            if (!rs.next()){
                System.out.println("Tai khoan gui khong ton tai");
                conn.rollback();
                return;
            }
            double balance = rs.getDouble("Balance");
            if (balance < amount){
                System.out.println("Khong du tien");
                conn.rollback();
                return;
            }
            CallableStatement cs = conn.prepareCall("{call sp_UpdateBalance(?, ?)}");
            cs.setString(1, fromId);
            cs.setDouble(2, -amount);
            cs.execute();
            cs.setString(1, toId);
            cs.setDouble(2, amount);
            cs.execute();
            conn.commit();
            System.out.println("Chuyen tien thanh cong");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM  Accounts");
            ResultSet rs2 = ps.executeQuery();
            while (rs2.next()){
                System.out.println(
                        rs2.getString("AccountId") + " - " +
                                rs2.getString("FullName") + " - " +
                                rs2.getDouble("Balance")
                );
            }
        } catch (Exception e){
            conn.rollback();
            System.out.println("Giao dich that bai");
        }
        conn.close();
    }
}