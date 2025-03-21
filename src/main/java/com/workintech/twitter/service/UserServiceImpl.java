package com.workintech.twitter.service;

import com.workintech.twitter.entity.User;
import com.workintech.twitter.exceptions.UserNotFoundException;
import com.workintech.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {

        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User not found " + id));
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User replaceOrCreate(Long id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()){
            user.setId(id);
            return userRepository.save(user);
        }
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        User userToUpdate = userRepository
                .findById(id)
                .orElseThrow(()-> new UserNotFoundException("User not found" + id));

        if(user.getName() != null){
            userToUpdate.setName(user.getName());
        }
        return userRepository.save(userToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);

    }
}
