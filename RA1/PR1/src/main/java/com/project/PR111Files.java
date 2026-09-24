package com.project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PR111Files {

    public static void main(String[] args) {
        String camiDirectori = System.getProperty("user.dir") + "/data/pr111";
        gestionarArxius(camiDirectori);
    }

    // Rep la ruta del directori on cal crear la carpeta myFiles
    public static void gestionarArxius(String camiDirectori) {
        try{
            //1 crear la carpeta myFiles (creando rutas intermedias si no existen)
            Path directoriMyFiles = Paths.get(camiDirectori,"MyFiles");
            Files.createDirectories(directoriMyFiles);

            //2 crear los dos archivos file1.txt y file2.txt dentro de la carpeta myFiles
            Path file1 = directoriMyFiles.resolve("file1.txt");
            Path file2 = directoriMyFiles.resolve("file2.txt");

            if (!Files.exists(file1)){
                Files.createFile(file1);
            }
            if (!Files.exists(file2)){
                Files.createFile(file2);
            }

            //3 mostrar el primer listado
            System.out.println("Els arxius de la carpeta són:");
            try(Stream<Path> fills = Files.list(directoriMyFiles)){
                fills.forEach(fitxer -> System.out.println(fitxer.getFileName()));
            }

            //4 renombrar file2.txt
            Path renamedFile = directoriMyFiles.resolve("renamedFile.txt");
            Files.move(file2,renamedFile);

            //5 eliminar file1.txt
            Files.delete(file1);

            //6 volver a mostrar el listado de archivos
            System.out.println("Els arxius de la carpeta són:");
            try(Stream<Path> fills = Files.list(directoriMyFiles)){
                fills.forEach(fitxer -> System.out.println(fitxer.getFileName()));
            }

        } catch (IOException e) {
            System.out.println("S'ha produït un error de xarxa o fitxer: " + e.getMessage());
        }
    }
}
