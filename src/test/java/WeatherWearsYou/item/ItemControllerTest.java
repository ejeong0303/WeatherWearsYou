package WeatherWearsYou.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static WeatherWearsYou.item.Item.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ItemControllerTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TestEntityManager entityManager;

    private ItemService itemService;


    @Test
    public void testGetItemsByFilter() throws IOException {
        String chatGPTResponse = "{\"Top\":[\"셔츠/블라우스(오버핏, 루즈핏셔츠)\",\"민소매 티셔츠(크롭)\",\"긴소매 티셔츠(오버핏)\"],\"Bottom\":[\"트레이닝/조거 팬츠(와이드핏)\",\"숏팬츠(밴딩)\",\"코튼 팬츠(와이드핏)\"],\"Outer\":[\"나일론/코치 재킷\",\"스타디움 재킷\",\"기타 아우터(경량)\"],\"Shoes\":[\"패션스니커즈화(블랙)\",\"캔버스/단화\",\"샌들(서머슈즈)\"]}";
        //when(itemService.getItemsByChatGPTResponse(chatGPTResponse)).thenReturn(mockItems);
        itemService = new ItemService(itemRepository, new ObjectMapper());
        ItemController itemController = new ItemController(itemService);

        ResponseEntity<LinkedHashMap<String, Object>> result = itemController.getItemsByFilter(chatGPTResponse, null, null, "female");

        System.out.println("Response:");

        System.out.println(result.getBody().toString());
        //assertEquals(mockItems, result);
        //verify(itemService, times(1)).getItemsByChatGPTResponse(chatGPTResponse);
    }
}
