package com.example.tabelog.service;

import java.util.List;

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

    // メールアドレスが変更されたか確認
    public boolean isEmailChanged(UserEditForm userEditForm) {
        User currentUser = findById(userEditForm.getId());
        return !currentUser.getEmail().equalsIgnoreCase(userEditForm.getEmail());
    }

    // ユーザーIDで検索
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
    }

    // ロールの全取得
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
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

        if (userEditForm.getPassword() != null && !userEditForm.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userEditForm.getPassword()));
        }

        Role role = roleRepository.findById(userEditForm.getRoleId())
                .orElseThrow(() -> new EntityNotFoundException("Role not found with ID: " + userEditForm.getRoleId()));
        user.setRole(role);

        userRepository.save(user);
    }

    // サインアップ用のユーザー作成
    public User create(SignupForm signupForm) {
        if (isEmailRegistered(signupForm.getEmail())) {
            throw new IllegalArgumentException("このメールアドレスは既に登録されています。");
        }

        User user = new User();
        user.setName(signupForm.getName());
        user.setFurigana(signupForm.getFurigana());
        user.setPostalCode(signupForm.getPostalCode());
        user.setAddress(signupForm.getAddress());
        user.setPhoneNumber(signupForm.getPhoneNumber());
        user.setEmail(signupForm.getEmail());
        user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
        user.setEnabled(false); // 初期状態では無効

        Role defaultRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new EntityNotFoundException("Default role 'ROLE_USER' not found."));
        user.setRole(defaultRole);

        return userRepository.save(user);
    }

    // 管理者用のユーザー作成
    public User registerUser(UserCreateForm userCreateForm) {
        if (isEmailRegistered(userCreateForm.getEmail())) {
            throw new IllegalArgumentException("このメールアドレスは既に登録されています。");
        }

        User user = new User();
        user.setName(userCreateForm.getName());
        user.setFurigana(userCreateForm.getFurigana());
        user.setPostalCode(userCreateForm.getPostalCode());
        user.setAddress(userCreateForm.getAddress());
        user.setPhoneNumber(userCreateForm.getPhoneNumber());
        user.setEmail(userCreateForm.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateForm.getPassword()));
        user.setEnabled(true);

        Role role = roleRepository.findById(userCreateForm.getRoleId())
                .orElseThrow(() -> new EntityNotFoundException("Role not found with ID: " + userCreateForm.getRoleId()));
        user.setRole(role);

        return userRepository.save(user);
    }

    // ユーザーを有効化
    public void enableUser(User user) {
        user.setEnabled(true);
        userRepository.save(user);
    }

    // ユーザー削除
    public void delete(Integer userId) {
        userRepository.deleteById(userId);
    }

    // 課金済みユーザーの更新
    public void markAsPaid(String customerId) {
        User user = userRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with customerId: " + customerId));
        user.setPremium(true);
        userRepository.save(user);
    }

    // プレミアムステータスを更新
    public void updateUserStatus(Integer userId, boolean isPremium) {
        User user = findById(userId);
        user.setPremium(isPremium);
        userRepository.save(user);
    }

    // 認証済みのユーザーを取得
    public User getAuthenticatedUser(Integer userId) {
        return findById(userId);
    }

    // パスワードと確認用パスワードが一致しているかを検証
    public boolean isSamePassword(String password, String passwordConfirmation) {
        return password != null && password.equals(passwordConfirmation);
    }
}
