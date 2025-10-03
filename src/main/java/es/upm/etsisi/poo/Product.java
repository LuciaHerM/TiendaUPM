package es.upm.etsisi.poo;

public class Product {
    private String id;
    private String name;
    private Category category;
    private Double price;
    public Product(String id, String name, Category category, Double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }
    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{class:Product, id:"+id+", name:'"+name+"', category:"+category+", price:"+price+"}";
    }

}
