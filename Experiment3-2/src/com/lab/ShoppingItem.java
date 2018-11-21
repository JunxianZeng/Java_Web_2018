package com.lab;
import java.io.Serializable;

public class ShoppingItem implements Serializable{
    private Product product; // 商品信息
    private int quantity; //商品数量
 
    public ShoppingItem(Product product, int quantity) {
         this.product = product;
         this.quantity = quantity;
    }
    //属性的getter方法和setter方法
    public Product getProduct() {
       return product;
    }
    public void setPtoduct(Product product) {
       this.product = product;
    }
    public int getQuantity() {
       return quantity;
    }
    public void setQuantity(int quantity) {
       this.quantity = quantity;
    }
}
