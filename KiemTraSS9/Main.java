package KiemTraSS9;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDatabase db = ProductDatabase.getInstance();

        int choice = 0;

        do {
            System.out.println("\n1. Thêm mới sản phẩm");
            System.out.println("2. Xem danh sách sản phẩm");
            System.out.println("5. Thoát");
            System.out.print("Chọn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhập loại (1: Physical, 2: Digital): ");
                    int type = sc.nextInt();
                    sc.nextLine();

                    System.out.print("ID: ");
                    String id = sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Price: ");
                    double price = sc.nextDouble();

                    System.out.print(type == 1 ? "Weight: " : "Size: ");
                    double extra = sc.nextDouble();

                    Product p = ProductFactory.createProduct(type, id, name, price, extra);

                    if (p != null) {   // sửa pull -> null
                        db.addProduct(p); // thêm tham số
                        System.out.println("Thêm thành công!");
                    } else {
                        System.out.println("Tạo sản phẩm thất bại!");
                    }
                    break;

                case 2:
                    if (db.getAllProducts().isEmpty()) {
                        System.out.println("Danh sách trống!");
                    } else {
                        for (Product prod : db.getAllProducts()) {
                            prod.displayInfo();
                        }
                    }
                    break;

                case 5:
                    System.out.println("Thoát chương trình.");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (choice != 5);
    }
}