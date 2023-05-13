package WeatherWearsYou.item;

import java.util.List;

public class ItemFilter {
    private List<String> categories;
    private String style;
    private Double minPrice;
    private Double maxPrice;
    private String gender;

    // Constructors
    public ItemFilter() {
    }

    public ItemFilter(List<String> categories, String style, Double minPrice, Double maxPrice, String gender) {
        this.categories = categories;
        this.style = style;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.gender = gender;
    }

    // Getters
    public List<String> getCategories() {
        return categories;
    }

    public String getStyle() {
        return style;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public String getGender() {
        return gender;
    }

    // Setters
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
