package com.project;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PR114linies {

    public static void main(String[] args) {
        // Definir el camí del fitxer dins del directori "data"
        String camiFitxer = System.getProperty("user.dir") + "/data/numeros.txt";

        // Crida al mètode que genera i escriu els números aleatoris
        generarNumerosAleatoris(camiFitxer);
    }

    // Mètode per generar 10 números aleatoris i escriure'ls al fitxer
    public static void generarNumerosAleatoris(String camiFitxer) {
    
        Path ruta = Paths.get(camiFitxer);
        
        try (BufferedWriter bw = Files.newBufferedWriter(ruta, StandardCharsets.UTF_8)) {
            for (int i = 0; i < 10; i++){
                int numeroAleatori = (int) (Math.random() * 100);
                bw.write(String.valueOf(numeroAleatori));

                if (i < 9) { // Evitar afegir un salt de línia després de l'últim número
                    bw.newLine();
                }
            }
        } catch (IOException e){
            System.out.println("Error en generar l'arxiu de números: " + e.getMessage());
        }


    }
}
