package WeatherWearsYou.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

import static WeatherWearsYou.item.Item.*;

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
                                                                          @RequestParam(required = false) Integer maxPrice,
                                                                          @RequestParam String gender) throws IOException {
        List<Item> items;
        Integer gen;
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        switch(gender) {
            case "male" :
                gen = MALE;
                break;
            case "female" :
                gen = FEMALE;
                break;
            case "unisex" :
                gen = UNISEX;
                break;
            default : gen = UNISEX;
        }

        if (minPrice != null && maxPrice != null) {
            items = itemService.getItemsByChatGPTResponseAndPriceRange(chatGPTResponse, minPrice, maxPrice, gen);
        } else if (minPrice != null && maxPrice == null) {
            items = itemService.getItemsByChatGPTResponseAndPriceRange(chatGPTResponse, minPrice, 10000000, gen);
        } else if (minPrice == null && maxPrice != null) {
            items = itemService.getItemsByChatGPTResponseAndPriceRange(chatGPTResponse, -1, maxPrice, gen);
        }else {
            items = itemService.getItemsByChatGPTResponse(chatGPTResponse, gen);
        }
        result.put("data", items);
        result.put("count", items.size());

        return ResponseEntity.ok().body(result);
    }
}
