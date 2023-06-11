package ls.imgur.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ls.imgur.app.model.CustomUser;
import ls.imgur.app.model.Role;
import ls.imgur.app.model.User;
import ls.imgur.app.service.IUserService;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@DependsOn({"passwordEncoder"})
@Service("customUserDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private IUserService userService;

	@Override
	@Transactional(readOnly = true)
	public CustomUser loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userService.findByUserName(userName);
		logger.info("User : {}", user);
		if (user == null) {
			logger.info("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		return prepareCustomUser(user);
	}
	
	
	public CustomUser prepareCustomUser(User user) {
		//Set<Long> grantedRoleIds = getRolesIdsSet(user.getRoles());
		Set<String> grantedRoleNames = getRolesSet(user.getRoles());
		Set<String> grantedAuthorities = getAuthoritiesSet(user.getRoles());
		Set<String> rolesAndAuthorities = new HashSet<String>();
		rolesAndAuthorities.addAll(grantedRoleNames);
		rolesAndAuthorities.addAll(grantedAuthorities);
		Map<Long, String> grantedRoleMap = getRoleMap(user.getRoles());
		return new CustomUser(user, grantedRoleMap, grantedAuthorities,
				(Set<GrantedAuthority>) getGrantedAuthorities(rolesAndAuthorities));
	}

	private final Set<GrantedAuthority> getGrantedAuthorities(Set<String> rolesAndAuthorities) {
		return rolesAndAuthorities.stream().map(p -> new SimpleGrantedAuthority(p)).collect(Collectors.toSet());
	}

	
	
	/*
	 * Returns Set<String> all roles names assigned to logged in user
	 */
	private final Set<Long> getRolesIdsSet(final Set<Role> roles) {
		return roles.stream().map(r -> r.getRoleId()).collect(Collectors.toSet());
	}

	/*
	 * Returns Set<String> all roles names assigned to logged in user
	 */
	private final Set<String> getRolesSet(final Set<Role> roles) {
		return roles.stream().map(r -> "ROLE_" + r.getRoleName()).collect(Collectors.toSet());
	}

	/*
	 * Returns Set<String> all privileges names mapped with logged in user assigned
	 * roles
	 */
	private final Set<String> getAuthoritiesSet(final Set<Role> roles) {
		Set<String> authorities = roles.stream().flatMap(r -> r.getPrivileges().stream().map(p -> p.getPrivName()))
				.collect(Collectors.toSet());
		return authorities;
	}
	
	private final Map<Long, String> getRoleMap(final Set<Role> roles) {
		Map<Long, String> roleMap = roles.stream().collect(Collectors.toMap(x-> x.getRoleId(), x-> "ROLE_"+x.getRoleName()));
		return roleMap;
	}

}
