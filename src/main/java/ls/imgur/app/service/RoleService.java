package ls.imgur.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ls.imgur.app.dao.IRoleDao;
import ls.imgur.app.model.Role;

import java.util.*;

@Service("roleService")
@Transactional(readOnly = false)
public class RoleService implements IRoleService {
	
	@Autowired
	private IRoleDao roleDao;

	@Override
	public Role save(Role role) {
		return roleDao.save(role);
	}

	@Override
	public List<Role> saveAll(List<Role> roles) {
		return roleDao.saveAll(roles);
	}

	@Override
	public void persist(Role role) {
		roleDao.save(role);
	}

	@Override
	public Role update(Role role) {
		return roleDao.save(role);
		// return roleDao.getOne(role.getRoleId());
	}

	@Override
	public Collection<Role> updateAll(Collection<Role> roles) {
		Set<Long> roleIds = new HashSet<>();
		roles.forEach(e -> roleIds.add(e.getRoleId()));
		return roleDao.findAllById(roleIds);
	}

	@Override
	public void delete(Role role) {
		roleDao.delete(role);
	}

	@Override
	public void deleteAll(List<Role> roles) {
		roleDao.deleteAll(roles);
	}

	@Override
	public void deleteById(Long id) {
		roleDao.deleteById(id);
	}

	@Override
	public void deleteByIds(List<Long> ids) {
		ids.forEach(id -> roleDao.deleteById(id));
	}

	@Override
	public Optional<Role> findById(Long id)  {
		Optional<Role> role = roleDao.findById(id);
		return role;
	}

	@Override
	public List<Role> findAll()  {
		return roleDao.findAll();
	}

	@Override
	public List<Role> findByIds(List<Long> ids) {
		return roleDao.findAllById(ids);
	}

}
