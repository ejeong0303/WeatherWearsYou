package WeatherWearsYou.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ItemServiceTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TestEntityManager entityManager;

    private ItemService itemService;

    /*
    @BeforeEach
    public void setUp() {
        itemService = new ItemService(itemRepository, new ObjectMapper());

        Item item1 = new Item(1, OUTER, "Denim jacket", "Levi's Denim Jacket", 90, UNISEX, "https://www.musinsa.com/app/goods/1", "Casual");
        Item item2 = new Item(2, OUTER, "Puffer jacket", "North Face Puffer Jacket", 150, UNISEX, "https://www.musinsa.com/app/goods/2", "Sporty");
        Item item3 = new Item(3, TOP, "Button-up shirt", "Ralph Lauren Button-up Shirt", 70, MALE, "https://www.musinsa.com/app/goods/3", "Formal");
        Item item4 = new Item(4, TOP, "Knit sweater", "H&M Knit Sweater", 60, FEMALE, "https://www.musinsa.com/app/goods/4", "Casual");
        Item item5 = new Item(5, BOTTOM, "Jeans", "Levi's 501 Jeans", 80, MALE, "https://www.musinsa.com/app/goods/5", "Casual");
        Item item6 = new Item(6, BOTTOM, "Chino pants", "Zara Chino Pants", 50, MALE, "https://www.musinsa.com/app/goods/6", "Smart Casual");
        Item item7 = new Item(7, SHOES, "Sneakers", "Adidas Ultraboost", 120, UNISEX, "https://www.musinsa.com/app/goods/7", "Casual");
        Item item8 = new Item(8, SHOES, "Derby shoes", "Clarks Derby Shoes", 110, MALE, "https://www.musinsa.com/app/goods/8", "Formal");

        entityManager.persist(item1);
        entityManager.persist(item2);
        entityManager.persist(item3);
        entityManager.persist(item4);
        entityManager.persist(item5);
        entityManager.persist(item6);
        entityManager.persist(item7);
        entityManager.persist(item8);
    }

     */

    @Test
    public void testGetItemsByChatGPTResponse() throws Exception {
        itemService = new ItemService(itemRepository, new ObjectMapper());
        // Adjust your test data to match the actual data you are using in your service implementation
        String chatGPTResponse = "{\"Top\":[\"셔츠/블라우스(오버핏, 루즈핏셔츠)\",\"민소매 티셔츠(크롭)\",\"긴소매 티셔츠(오버핏)\"],\"Bottom\":[\"트레이닝/조거 팬츠(와이드핏)\",\"숏팬츠(밴딩)\",\"코튼 팬츠(와이드핏)\"],\"Outer\":[\"나일론/코치 재킷\",\"스타디움 재킷\",\"기타 아우터(경량)\"],\"Shoes\":[\"패션스니커즈화(블랙)\",\"캔버스/단화\",\"샌들(서머슈즈)\"]}";

        List<Item> items = itemService.getItemsByChatGPTResponse(chatGPTResponse, 0);

        //assertThat(items).hasSize(3);
        //assertThat(items.get(0).getName()).isEqualTo("Levi's Denim Jacket");
        //assertThat(items.get(1).getName()).isEqualTo("North Face Puffer Jacket");
        //assertThat(items.get(2).getName()).isEqualTo("Ralph Lauren Button-up Shirt");

        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i).getName());
            System.out.println(items.get(i).getCategory());
            System.out.println(items.get(i).getItemType());
        }
    }
}
