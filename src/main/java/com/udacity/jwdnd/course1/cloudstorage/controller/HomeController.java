package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    NoteService noteService;
    CredentialService credentialService;

    public HomeController(NoteService noteService, CredentialService credentialService) {
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping()
    public String getHomePage(@ModelAttribute("noteForm") NoteForm noteForm, @ModelAttribute("credentialForm") CredentialForm credentialForm, Model model) {
        model.addAttribute("notes", this.noteService.getAllNotes());
        model.addAttribute("credentials", this.credentialService.getAllCredentials());

        return "home";
    }

    @PostMapping("/notes")
    public String addNote(Authentication authentication, @ModelAttribute("noteForm") NoteForm noteForm, Model model) {
//        chatForm.setUserName(authentication.getName());
        noteService.insertNote(noteForm);
        model.addAttribute("notes", noteService.getAllNotes());

        return "result";
    }

    @GetMapping("/notes/{noteId}")
    public String deleteNote(Authentication authentication, @ModelAttribute("noteForm") NoteForm noteForm, Model model) {
        System.out.println("Inside /notes/{noteId}");
        int test = noteService.deleteNote(noteForm);
        if (test < 0) throw new IllegalArgumentException("Error occured");
        model.addAttribute("notes", noteService.getAllNotes());

        return "result";
    }

    @PostMapping("/credentials")
    public String addCredential(Authentication authentication, @ModelAttribute("credentialForm") CredentialForm credentialForm, Model model) {
        credentialService.insertCredential(credentialForm);
        model.addAttribute("credentials", credentialService.getAllCredentials());

        return "result";
    }

    @GetMapping("/credentials/{credentialId}")
    public String deleteCredential(Authentication authentication, @ModelAttribute("credentialForm") CredentialForm credentialForm, Model model) {
        System.out.println("Inside /credentials/{credentialId}");
        int test = credentialService.deleteCredential(credentialForm);
        if (test < 0) throw new IllegalArgumentException("Error occured");
        model.addAttribute("credentials", credentialService.getAllCredentials());

        return "result";
    }
}
