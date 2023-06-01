package WeatherWearsYou.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

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

    public List<Item> getItemsByChatGPTResponse(String chatGPTResponse, Integer gen) throws IOException {
        LinkedHashMap<String, List<String>> categories = jsonMapper.readValue(chatGPTResponse, LinkedHashMap.class);
        List<Item> items = new ArrayList<>();
        Integer categoryID;
        for (Map.Entry<String, List<String>> entry : categories.entrySet()) {
            String category = entry.getKey();
            switch (category) {
                case "Top":
                    categoryID = TOP;
                    break;
                case "Bottom":
                    categoryID = BOTTOM;
                    break;
                case "Outer":
                    categoryID = OUTER;
                    break;
                case "Shoes":
                    categoryID = SHOES;
                    break;
                default:
                    categoryID = -1;
                    return items;
            }
            List<String> itemTypes = entry.getValue();
            List<Item> temp = new ArrayList<>();
            for (String itemType : itemTypes) {
                int idx = itemType.indexOf("(");
                if (idx == -1) { //no detail hashtag
                    temp.addAll(itemRepository.getItems(categoryID, itemType, gen));
                } else {
                    String[] tags = itemType.substring(idx + 1).trim().split(",\\s*");
                    itemType = itemType.substring(0, idx);
                    List<Item> temp2 = new ArrayList<>();
                    for (String tag : tags) {
                        tag = tag.replace(")", "");
                        temp2.addAll(itemRepository.getItemsByTag(categoryID, itemType, tag, gen));
                    }
                    Collections.sort(temp2);
                    if(temp2.size() < 5) {
                        temp.addAll(temp2);
                    } else {
                        temp.addAll(temp2.subList(0, 4));
                    }
                }
            }
            Set<Item> set = new HashSet<Item>(temp);
            temp = new ArrayList<>(set);
            Collections.sort(temp);
            if(temp.size() < 10) {
                items.addAll(temp);
            } else {
                items.addAll(temp.subList(0, 10));
            }
        }
        return items;
    }

    public List<Item> getItemsByChatGPTResponseAndPriceRange(String chatGPTResponse, Integer minPrice, Integer maxPrice, Integer gen) throws IOException {
        LinkedHashMap<String, List<String>> categories = jsonMapper.readValue(chatGPTResponse, LinkedHashMap.class);
        List<Item> items = new ArrayList<>();
        Integer categoryID;
        for (Map.Entry<String, List<String>> entry : categories.entrySet()) {
            String category = entry.getKey();
            switch (category) {
                case "Top":
                    categoryID = TOP;
                    break;
                case "Bottom":
                    categoryID = BOTTOM;
                    break;
                case "Outer":
                    categoryID = OUTER;
                    break;
                case "Shoes":
                    categoryID = SHOES;
                    break;
                default:
                    categoryID = -1;
                    return items;
            }
            List<String> itemTypes = entry.getValue();
            List<Item> temp = new ArrayList<>();
            for (String itemType : itemTypes) {
                int idx = itemType.indexOf("(");
                if (idx == -1) { //no detail hashtag
                    temp.addAll(itemRepository.getItemsByPrice(categoryID, itemType, minPrice, maxPrice, gen));
                } else {
                    String[] tags = itemType.substring(idx + 1).trim().split(",\\s*");
                    itemType = itemType.substring(0, idx);
                    for (String tag : tags) {
                        tag = tag.replace(")", "");
                        temp.addAll(itemRepository.getItemsByTagAndPrice(categoryID, itemType, tag, minPrice, maxPrice, gen));
                    }
                }
            }
            Set<Item> set = new HashSet<Item>(temp);
            temp = new ArrayList<>(set);
            Collections.sort(temp);
            if(temp.size() < 10) {
                items.addAll(temp);
            } else {
                items.addAll(temp.subList(0, 10));
            }
        }
        return items;
    }
}