package com.example.tabelog.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.tabelog.entity.Role;
import com.example.tabelog.entity.User;
import com.example.tabelog.form.SignupForm;
import com.example.tabelog.form.UserCreateForm;
import com.example.tabelog.form.UserEditForm;
import com.example.tabelog.repository.RoleRepository;
import com.example.tabelog.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	// メールアドレスが登録されているか確認
	public boolean isEmailRegistered(String email) {
		return userRepository.findByEmailIgnoreCase(email).isPresent();
	}

	// デフォルトロールを取得または作成
	private Role getOrCreateDefaultRole(String roleName) {
		return roleRepository.findByName(roleName)
				.orElseGet(() -> {
					Role newRole = new Role();
					newRole.setName(roleName);
					return roleRepository.save(newRole);
				});
	}

	// ユーザーIDで検索
	public User findById(Integer id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
	}

	// 認証されたユーザーを取得
	public User getAuthenticatedUser(Integer userId) {
		return findById(userId);
	}

	// 現在認証されているユーザーを取得
	public User getAuthenticatedUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	// ユーザー作成（一般ユーザー）
	public User create(SignupForm form) {
		return createUserWithRole(form, "ROLE_GENERAL");
	}

	// 課金ユーザー作成
	public User createPremiumUser(SignupForm form) {
		return createUserWithRole(form, "ROLE_PREMIUM_USER");
	}

	// 管理者作成
	public User createAdminUser(UserCreateForm form) {
		return createUserWithRole(form, "ROLE_ADMIN");
	}

	// 共通のユーザー作成ロジック
	private User createUserWithRole(Object form, String roleName) {
		String name, furigana, postalCode, address, phoneNumber, email, password;

		if (form instanceof SignupForm signupForm) {
			name = signupForm.getName();
			furigana = signupForm.getFurigana();
			postalCode = signupForm.getPostalCode();
			address = signupForm.getAddress();
			phoneNumber = signupForm.getPhoneNumber();
			email = signupForm.getEmail();
			password = signupForm.getPassword();
		} else if (form instanceof UserCreateForm userCreateForm) {
			name = userCreateForm.getName();
			furigana = userCreateForm.getFurigana();
			postalCode = userCreateForm.getPostalCode();
			address = userCreateForm.getAddress();
			phoneNumber = userCreateForm.getPhoneNumber();
			email = userCreateForm.getEmail();
			password = userCreateForm.getPassword();
		} else {
			throw new IllegalArgumentException("Unsupported form type");
		}

		// メールアドレスの重複チェック
		if (isEmailRegistered(email)) {
			throw new EmailAlreadyExistsException("このメールアドレスはすでに登録されています。");
		}

		// 新規ユーザーを作成
		User user = new User();
		user.setName(name);
		user.setFurigana(furigana);
		user.setPostalCode(postalCode);
		user.setAddress(address);
		user.setPhoneNumber(phoneNumber);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password)); // パスワードをハッシュ化
		user.setEnabled(false); // 初期状態では無効

		// ロールを取得または作成
		Role role = getOrCreateDefaultRole(roleName);
		user.addRole(role);

		return userRepository.save(user);
	}

	// ユーザー情報更新
	public void update(UserEditForm userEditForm) {
		User user = findById(userEditForm.getId());
		user.setName(userEditForm.getName());
		user.setFurigana(userEditForm.getFurigana());
		user.setPostalCode(userEditForm.getPostalCode());
		user.setAddress(userEditForm.getAddress());
		user.setPhoneNumber(userEditForm.getPhoneNumber());
		user.setEmail(userEditForm.getEmail());
		user.setPassword(passwordEncoder.encode(userEditForm.getPassword()));
		userRepository.save(user);
	}

	// ユーザー削除
	public void delete(Integer userId) {
		userRepository.deleteById(userId);
	}

	// メールアドレスが変更されたか確認
	public boolean isEmailChanged(UserEditForm userEditForm) {
		User currentUser = userRepository.findById(userEditForm.getId())
				.orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userEditForm.getId()));
		return !userEditForm.getEmail().equalsIgnoreCase(currentUser.getEmail());
	}

	// 特定のロールを持っているか確認
	public boolean hasRole(User user, String roleName) {
		return user.getRoles().stream()
				.anyMatch(role -> role.getName().equals(roleName));
	}

	// カスタム例外
	public static class EmailAlreadyExistsException extends RuntimeException {
		public EmailAlreadyExistsException(String message) {
			super(message);
		}
	}

	// ユーザー作成（管理者用）
	public User createUser(UserCreateForm form) {
		if (isEmailRegistered(form.getEmail())) {
			throw new IllegalArgumentException("このメールアドレスは既に登録されています。");
		}

		User user = new User();
		user.setName(form.getName());
		user.setFurigana(form.getFurigana());
		user.setPostalCode(form.getPostalCode());
		user.setAddress(form.getAddress());
		user.setPhoneNumber(form.getPhoneNumber());
		user.setEmail(form.getEmail());
		user.setPassword(passwordEncoder.encode(form.getPassword())); // パスワードをハッシュ化
		user.setEnabled(true); // 管理者作成の場合は有効状態で登録

		// デフォルトロールを設定（フォームでロール指定がない場合、一般ユーザーをデフォルトとする）
		String roleName = (form.getRoleName() != null) ? form.getRoleName() : "ROLE_GENERAL";
		Role role = getOrCreateDefaultRole(roleName);
		user.addRole(role);

		return userRepository.save(user);
	}

	// パスワードが一致しているか確認するメソッド
	public boolean isSamePassword(String password, String passwordConfirmation) {
		if (password == null || passwordConfirmation == null) {
			return false;
		}
		return password.equals(passwordConfirmation);
	}

	// ユーザーを有効化するメソッド
	public void enableUser(User user) {
		user.setEnabled(true); // ユーザーを有効化
		userRepository.save(user); // 変更をデータベースに保存
	}
}
