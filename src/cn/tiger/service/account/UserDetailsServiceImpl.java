package cn.tiger.service.account;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;

import cn.tiger.entity.account.AcctAuthority;
import cn.tiger.entity.account.AcctRole;
import cn.tiger.entity.account.AcctUser;
/**
 * 实现SpringSecurity的UserDetailsService接口,实现获取用户Detail信息的回调函�?.
 * 
 * @author calvin
 */
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

	private AccountManager accountManager;

	/**
	 * 获取用户Details信息的回调函�?.
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		AcctUser user = accountManager.findUserByLoginName(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户" + username + " 不存在");
		}
		Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(user);

		//-- MySpace示例中无以下属�??, 暂时全部设为true. --//
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		UserDetails userdetails = new org.springframework.security.core.userdetails.User(user.getLoginName(), user
				.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);

		return userdetails;
	}

	/**
	 * 获得用户�?有角色的权限集合.
	 */
	private Set<GrantedAuthority> obtainGrantedAuthorities(AcctUser user) {
		Set<GrantedAuthority> authSet = Sets.newHashSet();
		for (AcctRole role : user.getRoleList()) {
			for (AcctAuthority authority : role.getAuthorityList()) {
				authSet.add(new GrantedAuthorityImpl(authority.getPrefixedName()));
			}
		}
		return authSet;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
}
