package Session12.Bai5;

import java.sql.*;
import java.util.Scanner;

public class Bai5 {

    static String url = "jdbc:mysql://127.0.0.1:3306/hospital";
    static String user = "root";
    static String password = "123456";

    public static void main(String[] args) throws Exception {

        Connection conn = DriverManager.getConnection(url, user, password);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. List");
            System.out.println("2. Add");
            System.out.println("3. Update");
            System.out.println("4. Discharge");
            System.out.println("5. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM Patients");
                    ResultSet rs = ps1.executeQuery();
                    while (rs.next()) {
                        System.out.println(
                                rs.getInt("id") + " - " +
                                        rs.getString("name") + " - " +
                                        rs.getInt("age") + " - " +
                                        rs.getString("department")
                        );
                    }
                    break;

                case 2:
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Age: ");
                    int age = sc.nextInt();

                    sc.nextLine();
                    System.out.print("Dept: ");
                    String dept = sc.nextLine();

                    PreparedStatement ps2 = conn.prepareStatement(
                            "INSERT INTO Patients(name, age, department) VALUES(?, ?, ?)"
                    );
                    ps2.setString(1, name);
                    ps2.setInt(2, age);
                    ps2.setString(3, dept);
                    ps2.executeUpdate();
                    break;

                case 3:
                    System.out.print("ID: ");
                    int id = sc.nextInt();

                    sc.nextLine();
                    System.out.print("New name: ");
                    String newName = sc.nextLine();

                    PreparedStatement ps3 = conn.prepareStatement(
                            "UPDATE Patients SET name = ? WHERE id = ?"
                    );
                    ps3.setString(1, newName);
                    ps3.setInt(2, id);
                    ps3.executeUpdate();
                    break;

                case 4:
                    System.out.print("ID: ");
                    int pid = sc.nextInt();

                    CallableStatement cs = conn.prepareCall("{call CALCULATE_DISCHARGE_FEE(?, ?)}");
                    cs.setInt(1, pid);
                    cs.registerOutParameter(2, Types.DOUBLE);
                    cs.execute();

                    double fee = cs.getDouble(2);
                    System.out.println("Fee: " + fee);
                    break;

                case 5:
                    conn.close();
                    return;
            }
        }
    }
}