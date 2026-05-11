package com.example.hello.services;

import com.example.hello.models.User;
import com.example.hello.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow() ;
    }

    public User getUserByName(String username){
        return userRepository.findByUsername(username) ;
    }


    public User addUser(User user){
        return userRepository.save(user) ;
    }


    public void registerUser(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public void updateUser(String principalName, String username, String firstname, String lastname, String email, String password, String address, String phone, String genre){
        User currentUser = this.getUserByName(principalName);
        currentUser.setUsername(username);
        currentUser.setFirstname(firstname);
        currentUser.setLastname(lastname);
        currentUser.setPassword(passwordEncoder.encode(password));
        currentUser.setEmail(email);
        currentUser.setPhone(phone);
        currentUser.setAddress(address);
        currentUser.setGenre(genre);
        userRepository.save(currentUser);
    }

    public void followUser(String currentUsername, String targetUsername) {
        User currUser = userRepository.findByUsername(currentUsername);
        User targetUser = userRepository.findByUsername(targetUsername);

        currUser.getFollowing().add(targetUser);
        userRepository.save(currUser);
    }

    public void unfollowUser(String currentUsername, String targetUsername) {
        User currentUser = userRepository.findByUsername(currentUsername);
        User targetUser = userRepository.findByUsername(targetUsername);

        currentUser.getFollowing().remove(targetUser);
        userRepository.save(currentUser);
    }

    public boolean isFollowing(User currentUser, User targetUser) {
        if (currentUser == null || targetUser == null) {
            return false;
        }
        return currentUser.getFollowing().contains(targetUser);
    }

    public List<User> searchUsers(String query){
        return userRepository
                .findByUsernameContainingIgnoreCaseOrFirstnameContainingIgnoreCase(
                        query,
                        query
                );
    }

}
