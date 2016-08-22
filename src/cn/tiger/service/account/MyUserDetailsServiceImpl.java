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
import cn.tiger.entity.account.UserDetails.MyUserDetails;
import cn.tiger.utils.md5.EncryptUtil;

@Transactional(readOnly = true)
public class MyUserDetailsServiceImpl  implements UserDetailsService{
	
	private AccountManager accountManager;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		AcctUser user = accountManager.findUserByLoginNameOrEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户" + username + " 不存在");
		}
		Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(user);

		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		MyUserDetails userdetails = new MyUserDetails(user.getLoginName(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);

		userdetails.setUserNameAlias(user.getName());
		
		return userdetails;
	}
	
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
