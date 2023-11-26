package com.cibertec.edu.pe.DSWII_CL3_UrteagaViktto.controller;

import com.cibertec.edu.pe.DSWII_CL3_UrteagaViktto.model.response.ResponseFile;
import com.cibertec.edu.pe.DSWII_CL3_UrteagaViktto.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/filespdf")
    public ResponseEntity<String> uploadPdfFile(@RequestParam("file") MultipartFile file) {
        try {
            fileService.savePdfFile(file);
            return ResponseEntity.ok("Archivo PDF subido con éxito.");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error al subir el archivo PDF.");
        }
    }

    @PostMapping("/filesdoc")
    public ResponseEntity<String> uploadDocFile(@RequestParam("file") MultipartFile file) {
        try {
            fileService.saveDocFile(file);
            return ResponseEntity.ok("Archivo DOC subido con éxito.");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error al subir el archivo DOC.");
        }
    }
}

