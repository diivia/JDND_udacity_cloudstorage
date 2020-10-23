package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    NoteService noteService;

    public HomeController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping()
    public String getHomePage(@ModelAttribute("noteForm") NoteForm noteForm, Model model) {
        model.addAttribute("notes", this.noteService.getAllNotes());
        return "home";
    }

    @PostMapping("/note")
    public String addNote(Authentication authentication, @ModelAttribute("noteForm") NoteForm noteForm, Model model) {
//        chatForm.setUserName(authentication.getName());
        noteService.addNote(noteForm);
        model.addAttribute("notes", noteService.getAllNotes());
        noteForm.setNoteTitle("");
        noteForm.setNoteDescription("");
        return "result";
    }
}
