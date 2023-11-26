package com.cibertec.edu.pe.DSWII_CL3_UrteagaViktto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/filesdoc")
public class DocFileUploadController {

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> handleDocFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.getOriginalFilename().toLowerCase().endsWith(".doc")) {
            return ResponseEntity.badRequest().body("El archivo debe ser un DOC.");
        }
        if (file.getSize() > 2 * 1024 * 1024) {
            return ResponseEntity.badRequest().body("El tamaño del archivo debe ser como máximo 2MB.");
        }

        return ResponseEntity.ok("Archivo DOC subido con éxito.");
    }
}
