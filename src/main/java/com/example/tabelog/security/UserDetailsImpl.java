package com.example.tabelog.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.tabelog.entity.User;

public class UserDetailsImpl implements UserDetails {

	private final User user; // アプリケーション独自のUserエンティティ
	private final Collection<GrantedAuthority> authorities; // ユーザーのロール・権限

	// コンストラクタ
	public UserDetailsImpl(User user, Collection<GrantedAuthority> authorities) {
		this.user = user;
		this.authorities = authorities;
	}

	// カスタムメソッド: アプリケーションのUserオブジェクトを取得
	public User getUser() {
		return user;
	}

	// パスワードを取得（ハッシュ化済み）
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	// ユーザー名（この場合はメールアドレス）を取得
	@Override
	public String getUsername() {
		return user.getEmail();
	}

	// ユーザーの権限を返す
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	// アカウントが有効期限切れでないか
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// アカウントがロックされていないか
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 資格情報（パスワード）が有効期限切れでないか
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// アカウントが有効か
	@Override
	public boolean isEnabled() {
		return user.getEnabled() != null && user.getEnabled();
	}
}
