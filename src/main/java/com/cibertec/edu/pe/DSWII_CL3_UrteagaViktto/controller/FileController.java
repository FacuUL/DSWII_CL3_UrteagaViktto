package com.cibertec.edu.pe.DSWII_CL3_UrteagaViktto.controller;

import com.cibertec.edu.pe.DSWII_CL3_UrteagaViktto.model.response.ResponseFile;
import com.cibertec.edu.pe.DSWII_CL3_UrteagaViktto.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/filespdf")
public class FileController {
    @PostMapping
    @PreAuthorize("hasRole('SUPERVISOR')")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.getOriginalFilename().toLowerCase().endsWith(".pdf")) {
            return ResponseEntity.badRequest().body("El archivo debe ser un PDF.");
        }

        return ResponseEntity.ok("Archivo PDF subido con éxito.");
    }
}

