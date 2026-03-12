package KiemTraSS5;

import java.util.ArrayList;
import java.util.Optional;

public class ProductManager {

    private ArrayList<Product> products = new ArrayList<>();

    public void addProduct(Product p) {

        boolean exists = products.stream()
                .anyMatch(pr -> pr.getId() == p.getId());

        if (exists) {
            throw new InvalidProductException("ID đã tồn tại!");
        }

        products.add(p);
        System.out.println("Thêm sản phẩm thành công!");
    }

    public void displayProducts() {

        if (products.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }

        System.out.printf("%-5s %-15s %-10s %-10s %-10s\n",
                "ID", "Name", "Price", "Qty", "Category");

        products.forEach(p -> System.out.printf(
                "%-5d %-15s %-10.2f %-10d %-10s\n",
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getQuantity(),
                p.getCategory()
        ));
    }

    public void updateQuantity(int id, int newQty) {

        Optional<Product> product = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst();

        if (product.isPresent()) {
            product.get().setQuantity(newQty);
            System.out.println("Cập nhật thành công!");
        } else {
            throw new InvalidProductException("Không tìm thấy sản phẩm!");
        }
    }

    public void deleteOutOfStock() {

        products.removeIf(p -> p.getQuantity() == 0);

        System.out.println("Đã xóa các sản phẩm hết hàng!");
    }
}

