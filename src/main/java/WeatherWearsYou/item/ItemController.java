package WeatherWearsYou.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<LinkedHashMap<String, Object>> getItemsByFilter(@RequestParam String chatGPTResponse,
                                                                               @RequestParam(required = false) Integer minPrice,
                                                                               @RequestParam(required = false) Integer maxPrice) throws IOException {
        List<Item> items;
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        if (minPrice != null && maxPrice != null) {
            items = itemService.getItemsByChatGPTResponseAndPriceRange(chatGPTResponse, minPrice, maxPrice);
        } else if (minPrice != null && maxPrice == null) {
            items = itemService.getItemsByChatGPTResponseAndPriceRange(chatGPTResponse, minPrice, 10000000);
        } else if (minPrice == null && maxPrice != null) {
            items = itemService.getItemsByChatGPTResponseAndPriceRange(chatGPTResponse, -1, maxPrice);
        }else {
            items = itemService.getItemsByChatGPTResponse(chatGPTResponse);
        }
        result.put("data", items);
        result.put("count", items.size());

        return ResponseEntity.ok().body(result);
    }
}
