package ls.imgur.app.service;

import ls.imgur.app.model.User;

import java.sql.Timestamp;
import java.util.List;

public interface IUserService extends IJpaGenericService<User, Long> {

	User findByUserName(String userName);
	
	int updateLastLogin(Timestamp lastLogin, Long userId);

	User saveUserWithRoles(User user, List<Long> roleIds) ;
	
    int changePassword(String newPassword, Long userId);
    
    int enableOrDisableUser(boolean isEnabled, Long userId);


}
