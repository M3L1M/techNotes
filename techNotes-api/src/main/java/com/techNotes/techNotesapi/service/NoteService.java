package com.techNotes.techNotesapi.service;

import java.util.List;

import com.techNotes.techNotesapi.model.entity.Note;

public interface NoteService {
	public List<Note> getAllNotes();
    public Note createNewNote(Note note);
    public Note updateNote(Integer id, Note note);
    public Integer deleteNote(Integer id);
}
