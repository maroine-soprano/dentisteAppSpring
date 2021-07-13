package org.mk.dentisteapp.services;

import org.mk.dentisteapp.dao.UserRepository;
import org.mk.dentisteapp.entities.Role;
import org.mk.dentisteapp.entities.User;
import org.mk.dentisteapp.util.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		if (email.trim().isEmpty()) {
			throw new UsernameNotFoundException("email is empty");
		}

		User user = userRepository.findFirstByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("User " + email + " not found");
		}

		if (userRepository.findFirstByActiveAndConfirmeAndEmail(true,true,email)==null){
			throw new UsernameNotFoundException("not confirme,active");
		}

		return new UserPrincipal(user,getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Role role = user.getRole();
		authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		return authorities;
	}
}
