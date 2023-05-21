package WeatherWearsYou.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static WeatherWearsYou.item.Item.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {

    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemService itemService;

    private List<Item> mockItems;

    @BeforeEach
    public void setUp() {
        Item item1 = new Item(1, BOTTOM, "itemType1", "name1", 10, MALE, "url1", "style1");
        Item item2 = new Item(2, OUTER, "itemType2", "name2", 20, FEMALE, "url2", "style2");
        mockItems = Arrays.asList(item1, item2);
    }

    @Test
    public void testGetItemsByFilter() throws IOException {
        String chatGPTResponse = "{\"category1\": [\"itemType1\"]}";
        when(itemService.getItemsByChatGPTResponse(chatGPTResponse)).thenReturn(mockItems);

        List<Item> result = itemController.getItemsByFilter(chatGPTResponse, null, null, null);

        assertEquals(mockItems, result);
        verify(itemService, times(1)).getItemsByChatGPTResponse(chatGPTResponse);
    }
}
