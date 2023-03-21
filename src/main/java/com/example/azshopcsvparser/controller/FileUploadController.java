package com.example.azshopcsvparser.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileUploadController {
    
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        
        // Controlla se il file è vuoto o non valido
        if (file.isEmpty()) {
            return "Il file è vuoto o non valido.";
        }
        
        // Gestisci il file upload
        try {
            // Salva il file nella directory di destinazione
            byte[] bytes = file.getBytes();
            Path path = Paths.get("upload-dir/" + file.getOriginalFilename());
            Files.write(path, bytes);
            
            return "File caricato con successo!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Errore durante il caricamento del file.";
        }
    }
}