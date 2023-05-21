package WeatherWearsYou.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static WeatherWearsYou.item.Item.*;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ObjectMapper jsonMapper;

    @Autowired
    public ItemService(ItemRepository itemRepository, ObjectMapper jsonMapper) {
        this.itemRepository = itemRepository;
        this.jsonMapper = jsonMapper;
    }

    private List<Specification<Item>> buildSpecificationsFromChatGPTResponse(String chatGPTResponse) throws IOException {
        LinkedHashMap<Integer, List<String>> categories = jsonMapper.readValue(chatGPTResponse, LinkedHashMap.class);
        List<Specification<Item>> specs = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : categories.entrySet()) {
            Integer category = entry.getKey();
            List<String> itemTypes = entry.getValue();
            for (String itemType : itemTypes) {
                Specification<Item> spec = ItemSpecification.hasCategory(category)
                        .and(ItemSpecification.hasItemType(itemType));
                specs.add(spec);
            }
        }
        return specs;
    }

    public List<Item> getItemsByChatGPTResponse(String chatGPTResponse) throws IOException {
        List<Specification<Item>> specs = buildSpecificationsFromChatGPTResponse(chatGPTResponse);
        List<Item> items = new ArrayList<>();
        for (Specification<Item> spec : specs) {
            //List<Item> foundItems = itemRepository.findAll(spec);
            //System.out.println("Found items: " + foundItems);
            items.addAll(itemRepository.findAll(spec));
        }
        //System.out.println("Total items: " + items);
        return items;
    }

    public List<Item> getItemsByChatGPTResponseAndPriceRange(String chatGPTResponse, Integer minPrice, Integer maxPrice) throws IOException {
        List<Specification<Item>> specs = buildSpecificationsFromChatGPTResponse(chatGPTResponse);
        List<Item> items = new ArrayList<>();
        for (Specification<Item> spec : specs) {
            items.addAll(itemRepository.findAll(spec.and(ItemSpecification.hasPriceRange(minPrice, maxPrice))));
        }
        return items;
    }

    public List<Item> getItemsByChatGPTResponseAndStyle(String chatGPTResponse, String style) throws IOException {
        List<Specification<Item>> specs = buildSpecificationsFromChatGPTResponse(chatGPTResponse);
        List<Item> items = new ArrayList<>();
        for (Specification<Item> spec : specs) {
            items.addAll(itemRepository.findAll(spec.and(ItemSpecification.hasStyle(style))));
        }
        return items;
    }

    public List<Item> getItemsByChatGPTResponseAndStyleAndPriceRange(String chatGPTResponse, String style, Integer minPrice, Integer maxPrice) throws IOException {
        List<Specification<Item>> specs = buildSpecificationsFromChatGPTResponse(chatGPTResponse);
        List<Item> items = new ArrayList<>();
        for (Specification<Item> spec : specs) {
            items.addAll(itemRepository.findAll(spec.and(ItemSpecification.hasStyle(style)).and(ItemSpecification.hasPriceRange(minPrice, maxPrice))));
        }
        return items;
    }
}