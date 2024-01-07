package com.techNotes.techNotesapi.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.techNotes.techNotesapi.exception.BusinessRulesException;
import com.techNotes.techNotesapi.model.entity.User;
import com.techNotes.techNotesapi.model.repository.UserRepository;
import com.techNotes.techNotesapi.service.UserService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository repository;

	@Override
	public List<User> getAllUsers() {

		List<User> listUsers = repository.findAll();

		if (listUsers.isEmpty()) {
			throw new BusinessRulesException("Nenhum usuário encontrado");
		}

		return listUsers;
	}

	@Override
	@Transactional
	public User createNewUser(User user) {

		validateFields(user);
		boolean existis = repository.existsByUsername(user.getUsername());

		if (existis) {
			throw new BusinessRulesException("Nome de usuario duplicado");
		}
		return repository.save(user);
	}

	private void validateFields(User user) {
		if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
			throw new BusinessRulesException("Todos os campos são obrigatórios");
		}

	}

	@Override
	@Transactional
	public User updateUser(Integer id, User user) {
		validateFields(user);

		return repository.findById(id).map(entity -> {

			User userDuplicate = repository.findByUsername(user.getUsername());

			if (userDuplicate.getUsername().equals(entity.getUsername())) {
				if (userDuplicate.getId() != entity.getId()) {
					throw new BusinessRulesException("Nome de usuario duplicado");
				}
			}

			return repository.save(user);
		}).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não cadastrado na base de dados"));

	}

	@Override
	@Transactional
	public Integer deleteUser(Integer id) {

		repository.findById(id).map(entity -> {

			repository.delete(entity);
			return Void.TYPE;
		}).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não cadastrado na base de dados"));
		return id;
	}

}
