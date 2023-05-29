package WeatherWearsYou.item;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "items")
@NoArgsConstructor
@IdClass(ItemPK.class)
public class Item implements Comparable<Item>{
    public static int TOP = 0;
    public static int BOTTOM = 1;
    public static int OUTER = 2;
    public static int SHOES = 3;
    public static int MALE = 0;
    public static int FEMALE = 1;
    public static int UNISEX = 2;
    @Id
    @Column(name = "item_id", nullable = false)
    private Integer id;

    //top:0 bottom:1 outer:2 shoes:3
    @Id
    @Column(name = "category", nullable = false)
    private Integer category;

    @Column(name = "itemRank", nullable = false)
    private Integer itemRank;

    @Column(name = "itemType", nullable = false, length = 30)
    private String itemType;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)

    private Integer price;

    //male:0 female:1 unisex:2
    @Column(name = "gender", nullable = false, columnDefinition = "TINYINT", length = 1)
    private Integer gender;

    @Column(name = "imgLink")
    private String imgLink;

    @Column(name = "style")
    private String style;

    // Constructors

    public Item(Integer id, Integer category, Integer itemRank, String itemType, String name, Integer price, Integer gender, String imgLink, String style) {
        this.id = id;
        this.category = category;
        this.itemRank = itemRank;
        this.itemType = itemType;
        this.name = name;
        this.price = price;
        this.gender = gender;
        this.imgLink = imgLink;
        this.style = style;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public Integer getCategory() { return category; }

    public Integer getItemRank() { return itemRank; }

    public String getItemType() {
        return itemType;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getGender() {
        return gender;
    }

    public String getImgLink() {
        return imgLink;
    }

    public String getStyle() {
        return style;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public void setItemRank(Integer itemRank) { this.itemRank = itemRank; }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setImgLink(String imgLink) { this.imgLink = imgLink; }

    public void setStyle(String style) { this.style = style; }

    @Override
    public int compareTo(Item item) {
        if (item.itemRank < itemRank) {
            return 1;
        } else if (item.itemRank > itemRank) {
            return -1;
        }
        return 0;
    }
}
