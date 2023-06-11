package ls.imgur.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "IMAGE")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "TITLE", nullable = true, length = 500)
    private String title;

    @Column(name = "DESCRIPTION", nullable = true, length = 1000)
    private String description;

    @Column(name = "DATETIME", nullable = true)
    private Integer datetime;

    @Column(name = "TYPE", nullable = true)
    private String type;

    @Column(name = "ANIMATED", nullable = true)
    private boolean animated;

    @Column(name = "WIDTH", nullable = true)
    private Integer width;

    @Column(name = "HEIGHT", nullable = true)
    private Integer height;

    @Column(name = "SIZE", nullable = true)
    private Integer size;

    @Column(name = "VIEWS", nullable = true)
    private Integer views;

    @Column(name = "BANDWIDTH", nullable = true)
    private Integer bandwidth;

    @Column(name = "FAVORITE", nullable = true)
    private boolean favorite;

    @Column(name = "ACCOUNT_ID", nullable = true)
    private Integer account_id;

    @Column(name = "DELETEHASH", nullable = true)
    private String deletehash;

    @Column(name = "NAME", nullable = true)
    private String name;

    @Column(name = "LINK", nullable = true)
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
