package ls.imgur.app.model;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    private String id;
    private String title;
    private String description;
    private Integer datetime;
    private String type;
    private boolean animated;
    private Integer width;
    private Integer height;
    private Integer size;
    private Integer views;
    private Integer bandwidth;
    private boolean favorite;
    private Integer account_id;
    private String deletehash;
    private String name;
    private String link;

    public static Image mapper(Map<String, ?> map) {
        Image img = new Image();
        img.id = (String)map.get("id");
        img.title = (String)map.get("title");
        img.description = (String)map.get("description");
        img.datetime = (Integer)map.get("datetime");
        img.type = (String)map.get("type");
        img.animated = (boolean)map.get("animated");
        img.width = (Integer)map.get("width");
        img.height = (Integer)map.get("height");
        img.size = (Integer)map.get("size");
        img.views = (Integer)map.get("views");
        img.bandwidth = (Integer)map.get("bandwidth");
        img.favorite = (boolean)map.get("favorite");
        img.account_id = (Integer)map.get("account_id");
        img.deletehash = (String)map.get("deletehash");
        img.name = (String)map.get("name");
        img.link = (String)map.get("link");
        return img;
    }
}
