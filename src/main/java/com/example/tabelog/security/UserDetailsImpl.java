package com.example.tabelog.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.tabelog.entity.User;

public class UserDetailsImpl implements UserDetails {

	private final User user;
	private final Collection<GrantedAuthority> authorities;

	public UserDetailsImpl(User user) {
		this.user = user;
		this.authorities = user.getRoles().stream()
				.map(role -> role.getName().startsWith("ROLE_")
						? new SimpleGrantedAuthority(role.getName())
						: new SimpleGrantedAuthority("ROLE_" + role.getName()))
				.collect(Collectors.toList());
	}

	public User getUser() {
		return user;
	}

	// 名前を取得するメソッドを追加
	public String getFullName() {
		return user.getName();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.getEnabled();
	}
}
