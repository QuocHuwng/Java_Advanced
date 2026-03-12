package KiemTraSS5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductManager manager = new ProductManager();
        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.println("Nhap lua chon: ");
        do {
            System.out.println("1. Them san pham moi");
            System.out.println("2. Hien thi danh sach san pham");
            System.out.println("3. Cap nhat so luong theo ID");
            System.out.println("4. Xoa san pham da het hang");
            System.out.println("5. Thoat");
            System.out.print("Nhap lua chon: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Price: ");
                    double price = sc.nextDouble();

                    System.out.print("quantity: ");
                    int qty = sc.nextInt();
                    sc.nextLine();

                    System.out.println("category: ");
                    String cate = sc.nextLine();

                    Product p = new Product(id, name, price, qty, cate);
                    manager.addProduct(p);
                    break;

                case 2:
                    manager.displayProducts();
                    break;
                case 3:
                    System.out.print("Nhap id: ");
                    int updateID = sc.nextInt();

                    System.out.print("Quantity moi: ");
                    int newQty= sc.nextInt();
                    manager.updateQuantity(updateID, newQty);
                    break;
                case 4:
                    manager.deleteOutOfStock();
                    break;
                case 5:
                    System.out.println("Thoat chuong trinh");
                    break;
                default:
                    System.out.println("Lua chon khong hop le");
            }
        }while (choice != 5);
    }
}
