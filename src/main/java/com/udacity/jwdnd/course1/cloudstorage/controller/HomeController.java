package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.FileDB;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/home")
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    NoteService noteService;
    CredentialService credentialService;
    FileService fileService;

    public HomeController(NoteService noteService, CredentialService credentialService, FileService fileService) {
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.fileService = fileService;
    }

    @GetMapping()
    public String getHomePage(@ModelAttribute("noteForm") NoteForm noteForm, @ModelAttribute("credentialForm") CredentialForm credentialForm, Model model) {
        model.addAttribute("notes", this.noteService.getAllNotes());
        model.addAttribute("credentials", this.credentialService.getAllCredentials());
        model.addAttribute("files", this.fileService.getAllFiles());

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

    @PostMapping("/files")
    public String addFile(Authentication authentication, @RequestParam("fileUpload") MultipartFile fileUpload, Model model) {
        fileService.store(fileUpload);
        model.addAttribute("files", fileService.getAllFiles());

        return "result";
    }

    @GetMapping("/files/{fileId}")
    public String deleteFile(Authentication authentication, @ModelAttribute("fileForm") FileForm fileForm, Model model) {
        System.out.println("Inside /files/{fileId}");
        int test = fileService.deleteFile(fileForm);
        if (test < 0) throw new IllegalArgumentException("Error occured");
        model.addAttribute("files", fileService.getAllFiles());

        return "result";
    }

    @GetMapping("/files/file")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@RequestParam(value = "id") Integer id) {
        //logger.info("request to download file for user {}", id);
        FileDB file = fileService.getFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFileName() + "\"")
                .body(new ByteArrayResource(file.getFileData()));
    }

//    @ExceptionHandler(StorageFileNotFoundException.class)
//    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
//        return ResponseEntity.notFound().build();
//    }
}
