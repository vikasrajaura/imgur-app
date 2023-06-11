package ls.imgur.app.dao;

import ls.imgur.app.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("imageDao")
public interface IImageDao extends JpaRepository<Image, String> {

}
