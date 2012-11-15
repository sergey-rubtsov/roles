package com.roles.assignment.service.security;

import com.roles.assignment.domain.Role;
import com.roles.assignment.domain.SystemRoles;
import com.roles.assignment.domain.User;
import com.roles.assignment.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable(preConstruction=true)
public class UserRealm extends SimpleAccountRealm {

    @Autowired
    UserService userService;
    
	public UserRealm() {
		super();
		setName("localaccounts");
		setAuthenticationTokenClass(UsernamePasswordToken.class);
		//setCredentialsMatcher(new HashedCredentialsMatcher(Sha1Hash.ALGORITHM_NAME));
        setCredentialsMatcher(new AllowAllCredentialsMatcher());
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if (principals == null) throw new AuthorizationException("PrincipalCollection was null, which should not happen");

		if (principals.isEmpty()) return null;

		if (principals.fromRealm(getName()).size() <= 0) return null;

		String username = (String) principals.fromRealm(getName()).iterator().next();
		if (username == null) return null;
		User user = userService.findByLoginLike(username);
		if (user.getRoleSet().isEmpty()) { throw new AccountException("Account " + username + " does not have any role."); };
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(user.getRoleSet());
        return simpleAuthorizationInfo;
	}

    private void addTestUser(UsernamePasswordToken upToken) {
        User user = new User();
        user.setLogin(upToken.getUsername());
        user.setPassword(upToken.getPassword());
        if (userService.countAllUsers() == 0) {
            user.addRole(new Role(SystemRoles.ROLE_ADMIN));
        } else {
            user.addRole(new Role(SystemRoles.ROLE_USER));
        }
        //userService.saveUser(user);
    }

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		String username = upToken.getUsername();

		// Null username is invalid
		if (username == null) { throw new AccountException("Null usernames are not allowed by this realm."); }

		User user = userService.findByLoginLike(username);

        if (user == null) {
            addTestUser(upToken);
            //throw new AccountException("User is not exist.");
        }

		if (user.isLocked()) { throw new LockedAccountException("Account [" + username + "] is locked."); }
		if (user.isCredentialsExpired()) {
			String msg = "The credentials for account [" + username + "] are expired";
			throw new ExpiredCredentialsException(msg);
		}
        return new SimpleAuthenticationInfo(username, user.getPassword(), new SimpleByteSource("salt"), getName());
	}

}
