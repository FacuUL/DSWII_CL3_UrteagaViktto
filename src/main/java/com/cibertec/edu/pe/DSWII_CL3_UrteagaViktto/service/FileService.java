package com.cibertec.edu.pe.DSWII_CL3_UrteagaViktto.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public interface FileService {
    void savePdfFile(MultipartFile file) throws IOException;
    void saveDocFile(MultipartFile file) throws IOException;
}

