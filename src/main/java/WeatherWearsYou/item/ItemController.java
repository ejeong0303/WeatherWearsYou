package WeatherWearsYou.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://ec2-52-78-226-157.ap-northeast-2.compute.amazonaws.com:8080")
@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getItemsByFilter(@RequestParam String chatGPTResponse,
                                       @RequestParam(required = false) Double minPrice,
                                       @RequestParam(required = false) Double maxPrice,
                                       @RequestParam(required = false) String style) throws IOException {
        if (minPrice != null && maxPrice != null && style != null) {
            return itemService.getItemsByChatGPTResponseAndStyleAndPriceRange(chatGPTResponse, style, minPrice, maxPrice);
        }
        else if (minPrice != null && maxPrice != null) {
            return itemService.getItemsByChatGPTResponseAndPriceRange(chatGPTResponse, minPrice, maxPrice);
        }
        else if (style != null) {
            return itemService.getItemsByChatGPTResponseAndStyle(chatGPTResponse, style);
        }
        else {
            return itemService.getItemsByChatGPTResponse(chatGPTResponse);
        }
    }
}
