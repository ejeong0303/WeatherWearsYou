package WeatherWearsYou.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, String>, JpaSpecificationExecutor<Item> {
    //@Query("SELECT i FROM Item i WHERE i.name LIKE %?1%")
    //List<Item> getContainingItem(String word);

    @Query(value = "SELECT * FROM items WHERE category = ?1 and item_type LIKE ?2 order by item_rank LIMIT 4", nativeQuery = true)
    List<Item> getItems(Integer category, String type);

    @Query(value = "SELECT * FROM items WHERE category = ?1 and item_type LIKE ?2 and style LIKE %?3% order by item_rank LIMIT 4", nativeQuery = true)
    List<Item> getItemsByTag(Integer category, String type, String tag);

    @Query(value = "SELECT * FROM items WHERE category = ?1 and item_type LIKE ?2 and price > ?3 and price < ?4 order by item_rank LIMIT 4", nativeQuery = true)
    List<Item> getItemsByPrice(Integer category, String type, Integer min, Integer max);

    @Query(value = "SELECT * FROM items WHERE category = ?1 and item_type LIKE ?2 and style LIKE %?3% and price > ?4 and price < ?5 order by item_rank LIMIT 4", nativeQuery = true)
    List<Item> getItemsByTagAndPrice(Integer category, String type, String tag, Integer min, Integer max);
}
