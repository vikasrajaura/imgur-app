package ls.imgur.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ls.imgur.app.dao.IRoleDao;
import ls.imgur.app.dao.IUserDao;
import ls.imgur.app.model.Role;
import ls.imgur.app.model.User;

import java.sql.Timestamp;
import java.util.*;

@Service("userService")
@Transactional(readOnly = false)
public class UserService implements IUserService {
	
	@Autowired
	private IUserDao userDao;

	@Autowired
	private IRoleDao roleDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userDao.save(user);
	}

	@Override
	public List<User> saveAll(List<User> users) {
		return userDao.saveAll(users);
	}

	@Override
	public void persist(User user) {
		userDao.save(user);
	}

	@Override
	public User update(User user) {
		userDao.update(user.getFirstName(), user.getMiddleName(), user.getLastName(),
				user.getEmail(), user.getGender(), user.getUserId());
		return user;
	}

	@Override
	public Collection<User> updateAll(Collection<User> users) {
		users.forEach(e -> userDao.update(e.getFirstName(), e.getMiddleName(), e.getLastName(), e.getEmail(),
				e.getGender(), e.getUserId()));
		Set<Long> userIds = new HashSet<>();
		users.forEach(e -> userIds.add(e.getUserId()));
		return userDao.findAllById(userIds);
	}

	@Override
	public void delete(User e) {
		userDao.delete(e);
	}

	@Override
	public void deleteAll(List<User> users) {
		userDao.deleteAll(users);
	}

	@Override
	public void deleteById(Long id) {
		userDao.deleteById(id);
	}

	@Override
	public void deleteByIds(List<Long> ids) {
		ids.forEach(id -> userDao.deleteById(id));
	}

	@Override
	public Optional<User> findById(Long id)  {
		Optional<User> user = userDao.findById(id);
		return user;
	}

	@Override
	public List<User> findAll()  {
		return userDao.findAll();
	}

	@Override
	public User findByUserName(String userName) {
		return userDao.findByUserName(userName);
	}

	@Override
	public int updateLastLogin(Timestamp lastLogin, Long userId) {
		return userDao.updateLastLogin(lastLogin, userId);
	}

	@Override
	public List<User> findByIds(List<Long> ids) {
		return userDao.findAllById(ids);
	}

	@Override
	public User saveUserWithRoles(User user, List<Long> roleIds) {
		Set<Role> roles = null;
		//try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			roles = new HashSet<Role>(roleDao.findAllById(roleIds));
			user.setRoles(roles);
			userDao.save(user);
		/*} catch (DAOException e) {
			LOGGER.error("Exception Occured:" + User.class.getCanonicalName() + ": " + e.getMessage());
			throw new ServiceException(20, e.getErMsg(), e.getErDetails());
		}*/
		return user;
	}
	
    public int changePassword(String newPassword, Long userId) {
    	return userDao.changePassword(newPassword, userId);
    }

    public int enableOrDisableUser(boolean isEnabled, Long userId) {
    	return userDao.enableOrDisableUser(isEnabled, userId);
    }

}
