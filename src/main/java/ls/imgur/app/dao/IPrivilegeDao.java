package ls.imgur.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ls.imgur.app.model.Privilege;

@Repository("privilegeDao")
public interface IPrivilegeDao extends JpaRepository<Privilege, Long> {

}
