package WeatherWearsYou.item;

import org.springframework.data.jpa.domain.Specification;

public class ItemSpecification {

    public static Specification<Item> hasCategory(String category) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("category"), category);
    }

    public static Specification<Item> hasItemType(String itemType) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("itemType"), itemType);
    }

    public static Specification<Item> hasStyle(String style) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("style"), style);
    }

    public static Specification<Item> hasPriceRange(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
    }

    public static Specification<Item> hasGender(String gender) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("gender"), gender);
    }
}
