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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping("/note")
    public String addNote(Authentication authentication, @ModelAttribute("noteForm") NoteForm noteForm, Model model) {
//        chatForm.setUserName(authentication.getName());
        noteService.addNote(noteForm);
        model.addAttribute("notes", noteService.getAllNotes());
        noteForm.setNoteTitle("");
        noteForm.setNoteDescription("");
        return "result";
    }

    @PostMapping("/credential")
    public String addNote(Authentication authentication, @ModelAttribute("credential") CredentialForm credentialForm, Model model) {
        credentialService.addCredential(credentialForm);
        model.addAttribute("credentials", credentialService.getAllCredentials());
        credentialForm.setUrl("");
        credentialForm.setUserName("");
        credentialForm.setUserId("");
        return "result";
    }
}
