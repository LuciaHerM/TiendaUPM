package es.upm.etsisi.poo;

public class Product_Basic extends Product {
    public Category category;
    public Product_Basic(String id, String name, Category category, Double price) {
        super(id,name,price);
        this.category = category;
    }

    public Product_Basic() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return " {class:Product, id:" + id + ", name:'" + name + "', category:" + category + ", price:" + price + "}";
    }


}
