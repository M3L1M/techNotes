package com.techNotes.techNotesapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techNotes.techNotesapi.model.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

	Note getByTitle(String title);


}
