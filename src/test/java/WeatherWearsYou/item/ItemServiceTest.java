package WeatherWearsYou.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ItemServiceTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TestEntityManager entityManager;

    private ItemService itemService;

    @BeforeEach
    public void setUp() {
        itemService = new ItemService(itemRepository, new ObjectMapper());

        Item item1 = new Item("1", "outer", "Denim jacket", "Levi's Denim Jacket", "Casual", 90, "unisex");
        Item item2 = new Item("2", "outer", "Puffer jacket", "North Face Puffer Jacket", "Sporty", 150, "unisex");
        Item item3 = new Item("3", "top", "Button-up shirt", "Ralph Lauren Button-up Shirt", "Formal", 70, "male");
        Item item4 = new Item("4", "top", "Knit sweater", "H&M Knit Sweater", "Casual", 60, "female");
        Item item5 = new Item("5", "bottom", "Jeans", "Levi's 501 Jeans", "Casual", 80, "male");
        Item item6 = new Item("6", "bottom", "Chino pants", "Zara Chino Pants", "Smart Casual", 50, "male");
        Item item7 = new Item("7", "shoes", "Sneakers", "Adidas Ultraboost", "Casual", 120, "unisex");
        Item item8 = new Item("8", "shoes", "Derby shoes", "Clarks Derby Shoes", "Formal", 110, "male");

        entityManager.persist(item1);
        entityManager.persist(item2);
        entityManager.persist(item3);
        entityManager.persist(item4);
        entityManager.persist(item5);
        entityManager.persist(item6);
        entityManager.persist(item7);
        entityManager.persist(item8);
    }

    @Test
    public void testGetItemsByChatGPTResponse() throws Exception {
        // Adjust your test data to match the actual data you are using in your service implementation
        String chatGPTResponse = "{\"outer\": [\"Denim jacket\", \"Puffer jacket\"], \"top\": [\"Button-up shirt\"]}";

        List<Item> items = itemService.getItemsByChatGPTResponse(chatGPTResponse);

        assertThat(items).hasSize(3);
        assertThat(items.get(0).getName()).isEqualTo("Levi's Denim Jacket");
        assertThat(items.get(1).getName()).isEqualTo("North Face Puffer Jacket");
        assertThat(items.get(2).getName()).isEqualTo("Ralph Lauren Button-up Shirt");
    }
}
