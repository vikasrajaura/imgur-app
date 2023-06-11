package ls.imgur.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ls.imgur.app.model.Role;

@Repository("roleDao")
public interface IRoleDao extends JpaRepository<Role, Long> {

}
