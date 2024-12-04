package com.example.tabelog.service;

<<<<<<< HEAD
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

	public UserService(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	// メールアドレスが登録されているか確認
	public boolean isEmailRegistered(String email) {
		return userRepository.findByEmailIgnoreCase(email).isPresent();

	}

	// デフォルトロールを取得または作成
	private Role getOrCreateDefaultRole() {
		return roleRepository.findByName("ROLE_USER")
				.orElseGet(() -> {
					Role newRole = new Role();
					newRole.setName("ROLE_USER");
					return roleRepository.save(newRole);
				});
	}

	// ユーザーIDを取得
	public User findById(Integer id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
	}

	// 認証されたユーザーを取得
	public User getAuthenticatedUser(Integer userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
	}

	// メールアドレスが変更されたか確認
	public boolean isEmailChanged(UserEditForm userEditForm) {
		User currentUser = userRepository.findById(userEditForm.getId())
				.orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userEditForm.getId()));
		return !userEditForm.getEmail().equalsIgnoreCase(currentUser.getEmail());
	}

	// ユーザーを有効にする
	public void enableUser(User user) {
		user.setEnabled(true); // ユーザーを有効にする
		userRepository.save(user); // 更新されたユーザーを保存
	}

	// UserService に createUser メソッドを追加
	// UserCreateForm 用のメソッド
	public User createUser(UserCreateForm form) {
		return createCommonUser(form.getName(), form.getFurigana(), form.getPostalCode(), form.getAddress(),
				form.getPhoneNumber(), form.getEmail(), form.getPassword());
	}

	// SignupForm 用のメソッド
	public User create(SignupForm form) {
		return createCommonUser(form.getName(), form.getFurigana(), form.getPostalCode(), form.getAddress(),
				form.getPhoneNumber(), form.getEmail(), form.getPassword());
	}

	// 共通のユーザー作成ロジック
	private User createCommonUser(String name, String furigana, String postalCode, String address,
			String phoneNumber, String email, String password) {
		if (isEmailRegistered(email)) {
			throw new EmailAlreadyExistsException("このメールアドレスはすでに登録されています。");
		}

		User user = new User();
		user.setName(name);
		user.setFurigana(furigana);
		user.setPostalCode(postalCode);
		user.setAddress(address);
		user.setPhoneNumber(phoneNumber);
		user.setEmail(email);
		user.setPassword(password); // パスワードそのまま保存（後でハッシュ化が必要）
		user.setEnabled(false); // 初期状態で無効

		Role role = getOrCreateDefaultRole();
		user.setRole(role);

		return userRepository.save(user);
	}

	// ユーザー情報を更新
	public void update(UserEditForm userEditForm) {
		User user = userRepository.findById(userEditForm.getId())
				.orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userEditForm.getId()));
		user.setName(userEditForm.getName());
		user.setFurigana(userEditForm.getFurigana());
		user.setPostalCode(userEditForm.getPostalCode());
		user.setAddress(userEditForm.getAddress());
		user.setPhoneNumber(userEditForm.getPhoneNumber());
		user.setEmail(userEditForm.getEmail());
		user.setPassword(userEditForm.getPassword()); // 必要に応じてパスワードのハッシュ化
		userRepository.save(user);
	}

	// ユーザーが入力した「パスワード」と「パスワード確認」が一致しているかどうか確認
	public boolean isSamePassword(String password, String passwordConfirmation) {
		if (password == null || passwordConfirmation == null) {
			return false;
		}
		return password.equals(passwordConfirmation);
	}

	// カスタム例外: メールアドレスがすでに登録されている場合にスロー
	public static class EmailAlreadyExistsException extends RuntimeException {
		public EmailAlreadyExistsException(String message) {
			super(message);
		}
	}

=======
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.tabelog.entity.Role;
import com.example.tabelog.entity.User;
import com.example.tabelog.form.SignupForm;
import com.example.tabelog.form.UserEditForm;
import com.example.tabelog.repository.RoleRepository;
import com.example.tabelog.repository.UserRepository;

import jakarta.transaction.Transactional;

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

	@Transactional
	public User create(SignupForm signupForm) {
		User user = new User();
		Role role = roleRepository.findByName("ROLE_GENERAL");

		user.setName(signupForm.getName());
		user.setFurigana(signupForm.getFurigana());
		user.setPostalCode(signupForm.getPostalCode());
		user.setAddress(signupForm.getAddress());
		user.setPhoneNumber(signupForm.getPhoneNumber());
		user.setEmail(signupForm.getEmail());
		user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
		user.setRole(role);
		user.setEnabled(false);

		return userRepository.save(user);
	}

	@Transactional
	public void update(UserEditForm userEditForm) {
		User user = userRepository.getReferenceById(userEditForm.getId());

		user.setName(userEditForm.getName());
		user.setFurigana(userEditForm.getFurigana());
		user.setPostalCode(userEditForm.getPostalCode());
		user.setAddress(userEditForm.getAddress());
		user.setPhoneNumber(userEditForm.getPhoneNumber());
		user.setEmail(userEditForm.getEmail());

		userRepository.save(user);
	}

	// メールアドレスが登録済みかどうかをチェックする
	public boolean isEmailRegistered(String email) {
		User user = userRepository.findByEmail(email);
		return user != null;
	}

	// パスワードとパスワード（確認用）の入力値が一致するかどうかをチェックする
	public boolean isSamePassword(String password, String passwordConfirmation) {
		return password.equals(passwordConfirmation);
	}

	// ユーザーを有効にする
	@Transactional
	public void enableUser(User user) {
		user.setEnabled(true);
		userRepository.save(user);
	}

	// メールアドレスが変更されたかどうかをチェックする
	public boolean isEmailChanged(UserEditForm userEditForm) {
		User currentUser = userRepository.getReferenceById(userEditForm.getId());
		return !userEditForm.getEmail().equals(currentUser.getEmail());
	}
>>>>>>> branch 'main' of https://github.com/mick309/springboot-tabelog-kadai.git
}
