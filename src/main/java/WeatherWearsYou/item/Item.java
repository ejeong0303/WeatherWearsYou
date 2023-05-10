package WeatherWearsYou.item;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @Column(name = "item_id")
    private String id;

    @Column(name = "category")
    private String category;

    @Column(name = "itemType")
    private String itemType;

    @Column(name = "name")
    private String name;

    @Column(name = "style")
    private String style;

    @Column(name = "price")
    private double price;

    @Column(name = "gender")
    private String gender;

    // Constructors
    public Item() {
    }

    public Item(String id, String category, String itemType, String name, String style, double price, String gender) {
        this.id = id;
        this.category = category;
        this.itemType = itemType;
        this.name = name;
        this.style = style;
        this.price = price;
        this.gender = gender;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getItemType() {
        return itemType;
    }

    public String getName() {
        return name;
    }

    public String getStyle() {
        return style;
    }

    public double getPrice() {
        return price;
    }

    public String getGender() {
        return gender;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
