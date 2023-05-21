package WeatherWearsYou.item;

import java.util.List;

public class ItemFilter {
    private List<Integer> categories;
    private String style;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer gender;

    // Constructors
    public ItemFilter() {
    }

    public ItemFilter(List<Integer> categories, String style, Integer minPrice, Integer maxPrice, Integer gender) {
        this.categories = categories;
        this.style = style;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.gender = gender;
    }

    // Getters
    public List<Integer> getCategories() {
        return categories;
    }

    public String getStyle() {
        return style;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public Integer getGender() {
        return gender;
    }

    // Setters
    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
