package com.serheev.service;

import com.serheev.dto.User;
import com.serheev.exception.UserNotFoundException;
import com.serheev.model.UserEntity;
import com.serheev.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public User create(User user) {
        return convertToDto(userRepository.saveAndFlush(new UserEntity()
                        .setLogin(user.getLogin())
                        .setPassword(passwordEncoder.encode(user.getPassword()))
                        .setRole(user.getRole())
                        .setEnabled(user.isEnabled())
                )
        );
    }

    @Transactional
    public void update(User user) {
        Optional<UserEntity> userEntity = userRepository.findById(user.getId());
        if (!userEntity.isPresent()) {
            return;
        }
        userEntity.get()
                .setLogin(user.getLogin())
                .setPassword(passwordEncoder.encode(user.getPassword()))
                .setRole(user.getRole())
                .setEnabled(user.isEnabled());
    }

    @Transactional
    public void update(Long id, User user) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (!userEntity.isPresent()) {
            return;
        }
        userEntity.get()
                .setLogin(user.getLogin())
                .setPassword(passwordEncoder.encode(user.getPassword()))
                .setRole(user.getRole())
                .setEnabled(user.isEnabled());
    }

    public void delete(User user) {
        userRepository.deleteById(user.getId());
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void truncate() {
        userRepository.deleteAll();
    }

    @Transactional
    public User findUserById(Long id) {
        if (userRepository.findUserEntityById(id) == null) {
            throw new UserNotFoundException("Specify other id!");
        }
        return convertToDto(userRepository.findUserEntityById(id));
    }

    @Transactional
    public User findUserByLogin(String login) {
        if (userRepository.findUserEntityByLogin(login) == null) {
            throw new UserNotFoundException("Specify other login!");
        }
        return convertToDto(userRepository.findUserEntityByLogin(login));
    }

    public List<User> findAll() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public UserEntity getUserEntity(User user) {
        return convertToEntity(user);
    }

    public User getUserDto(UserEntity userEntity) {
        return convertToDto(userEntity);
    }

    private User convertToDto(UserEntity userEntity) {
        User user = modelMapper.map(userEntity, User.class);
        user.setLogin(userEntity.getLogin());
        user.setPassword(userEntity.getPassword());
        user.setRole(userEntity.getRole());
        user.setEnabled(userEntity.getEnabled());
        return user;
    }

    private UserEntity convertToEntity(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userEntity.setLogin(user.getLogin());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());
        userEntity.setEnabled(user.isEnabled());
        return userEntity;
    }
}
