package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.FileDB;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FileService {

    private Logger logger = LoggerFactory.getLogger(FileService.class);
    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            if (file == null) {
                throw new StorageException(
                        "Cannot store file that already exists.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getSize(), file.getBytes(), null);
                fileMapper.insert(fileDB);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    public List<FileDB> getAllFiles() {
        return fileMapper.getAllFiles();
    }

    public int deleteFile(FileForm fileForm) {
        return fileMapper.delete(fileForm.getFileId());
    }

    public FileDB getFile(Integer fileId) {
        return fileMapper.getFileById(fileId);
    }
}
