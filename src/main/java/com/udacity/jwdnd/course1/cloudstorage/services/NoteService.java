package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class NoteService {

    NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Create NoteService");
    }

    public void insertNote(NoteForm noteForm){
        if (noteMapper.getNoteById(noteForm.getNoteId()) != null){
            updateNote(noteForm);
        } else {
            addNote(noteForm);
        }
    }

    private void updateNote(NoteForm noteForm) {
        Note note = noteMapper.getNoteById(noteForm.getNoteId());
        note.setNoteTitle(noteForm.getNoteTitle());
        note.setNoteDescription(noteForm.getNoteDescription());

        noteMapper.update(note.getNoteId(),
                note.getNoteTitle(),
                note.getNoteDescription(),
                note.getUserId());
    }

    private void addNote(NoteForm noteForm){
        Note note = new Note();
        note.setNoteTitle(noteForm.getNoteTitle());
        note.setNoteDescription(noteForm.getNoteDescription());
        note.setUserId(noteForm.getUserId());

        noteMapper.insert(note);

    }

    public List<Note> getAllNotes(){
        return noteMapper.getAllNotes();
    }

    public int deleteNote(NoteForm noteForm) {
        return noteMapper.delete(noteForm.getNoteId());
    }
}
