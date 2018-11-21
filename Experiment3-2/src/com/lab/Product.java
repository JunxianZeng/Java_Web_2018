package com.lab;

public class Product{
    private int id;//商品编号
    private String name;//商品名称
    private String description; //商品描述
    private float price;//商品价格
 //构造方法
    public Product(int id, String name, String description,  float price) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
 }
 //个属性的setter方法和getter方法
 public int getId(){
     return id;
 }
 public void setId(int id) {
     this.id = id;
 }
 public String getName() {
     return name;
 }
 public void setName(String name) {
	    this.name = name;
	}
	public String getDescription() {
	    return description;
	}
	public void setDescription(String description) {
	    this.description = description;
	}
	public float getPrice() {
	    return price;
	}
	public void setPtice(float price) {
	    this.price = price;
	}
	}
