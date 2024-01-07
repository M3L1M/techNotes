package com.techNotes.techNotesapi.service;

import java.util.List;

import com.techNotes.techNotesapi.model.entity.User;

public interface UserService {
	public List<User> getAllUsers();
	public User createNewUser(User user);
	public User updateUser(Integer id,User user);
	public Integer deleteUser(Integer id);
}
