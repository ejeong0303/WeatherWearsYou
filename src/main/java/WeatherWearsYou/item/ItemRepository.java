package WeatherWearsYou.item;

import WeatherWearsYou.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, String>, JpaSpecificationExecutor<Item> {
    @Query("SELECT i FROM Item i WHERE i.name LIKE %?1%")
    List<Item> getContainingItem(String word);


}
