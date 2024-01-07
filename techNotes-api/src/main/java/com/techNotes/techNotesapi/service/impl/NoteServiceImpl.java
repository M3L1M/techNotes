package com.techNotes.techNotesapi.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.techNotes.techNotesapi.exception.BusinessRulesException;
import com.techNotes.techNotesapi.model.entity.Note;
import com.techNotes.techNotesapi.model.repository.NoteRepository;
import com.techNotes.techNotesapi.service.NoteService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class NoteServiceImpl implements NoteService{

	private final NoteRepository repository;
	
	@Override
	public List<Note> getAllNotes() {
		List<Note> listNotes=repository.findAll();
		
		if(listNotes.isEmpty()) {
			throw new BusinessRulesException("Nenhuma nota encontrada");
		}
		return listNotes;
	}

	@Override
	@Transactional
	public Note createNewNote(Note note) {
		validateFields(note);
		
		
		return repository.save(note);
	}

	private void validateFields(Note note) {
		if(note.getTitle().isEmpty() || note.getTitle().isEmpty()) {
			throw new BusinessRulesException("Todos os campos são obrigatórios");
		}
		
		
	}

	@Override
	@Transactional
	public Note updateNote(Integer id, Note note) {
		validateFields(note);
		
		return repository.findById(id).map(entry -> {
			return repository.save(note);
		}).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nota não cadastrada na base de dados"));

	}

	@Override
	@Transactional
	public Integer deleteNote(Integer id) {
		repository.findById(id).map(entity -> {

			repository.delete(entity);
			return Void.TYPE;
		}).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nota não cadastrada na base de dados"));
		return id;
	}
	
}
