package WeatherWearsYou.item;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class ItemPK  implements Serializable {
    private Integer category;
    private Integer id;
}
