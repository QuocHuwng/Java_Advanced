package KiemTraSS9;

import java.util.ArrayList;
import java.util.List;

public class ProductDatabase {
    private static ProductDatabase instance;
    private List<Product> productList;

    private ProductDatabase(){
        productList = new ArrayList<>();
    }
    public static ProductDatabase getInstance(){
        if(instance == null){
            instance = new ProductDatabase();
        }
        return instance;
    }
    public void addProduct(Product p){
        productList.add(p);
    }
    public List<Product> getAllProducts(){
        return productList;
    }
    public Product finById(String id){
        for (Product p : productList){
            if (p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }
    public void deleteProduct(String id){
        Product p = finById(id);
        if (p != null){
            productList.remove(p);
        }
    }
}
