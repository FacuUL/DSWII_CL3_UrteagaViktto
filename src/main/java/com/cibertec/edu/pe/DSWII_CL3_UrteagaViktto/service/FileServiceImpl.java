package com.cibertec.edu.pe.DSWII_CL3_UrteagaViktto.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    private static final String PDF_DIRECTORY = "ruta/a/tu/directorio/Documentos/pdf";
    private static final String DOC_DIRECTORY = "ruta/a/tu/directorio/Documentos/doc";

    @Override
    public void savePdfFile(MultipartFile file) throws IOException {
        saveFile(file, PDF_DIRECTORY);
    }

    @Override
    public void saveDocFile(MultipartFile file) throws IOException {
        saveFile(file, DOC_DIRECTORY);
    }

    private void saveFile(MultipartFile file, String directory) throws IOException {
        // Verificar el directorio y crearlo si no existe
        Path path = Paths.get(directory);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        // Guardar el archivo en el directorio
        Files.copy(file.getInputStream(), Paths.get(directory, file.getOriginalFilename()));
    }
}
