package com.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PR115cp {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Error: Has d'indicar dues rutes d'arxiu.");
            System.out.println("Ús: PR115cp <origen> <destinació>");
            return;
        }

        // Ruta de l'arxiu origen
        String rutaOrigen = args[0];
        // Ruta de l'arxiu destinació
        String rutaDesti = args[1];

        // Crida al mètode per copiar l'arxiu
        copiarArxiu(rutaOrigen, rutaDesti);
    }

    // Mètode per copiar un arxiu de text de l'origen al destí
    public static void copiarArxiu(String rutaOrigen, String rutaDesti) {
        
        //Convertimos los String en objetos Path (¡Esto es lo que faltaba!)
        Path origen = Paths.get(rutaOrigen);
        Path desti = Paths.get(rutaDesti);


        try {
            // Comprovem si el fitxer original acaba amb un salt de línia
            String contingutComplet = Files.readString(origen, StandardCharsets.UTF_8);
            boolean acabaAmbSalt = contingutComplet.endsWith("\n") || contingutComplet.endsWith("\r");

            // Creem una llista per guardar les línies temporalment
            List<String> linies = new ArrayList<>();

            // Obrim el lector línia a línia usant l'API NIO.2 en UTF-8
            try (BufferedReader br = Files.newBufferedReader(origen, StandardCharsets.UTF_8)) {
                String linia;
                while ((linia = br.readLine()) != null) {
                    linies.add(linia);
                }
            }

                    // Obrim l'escriptor usant l'API NIO.2 en UTF-8
            try (BufferedWriter bw = Files.newBufferedWriter(desti, StandardCharsets.UTF_8)) {
                for (int i = 0; i < linies.size(); i++) {
                    bw.write(linies.get(i));
                    
                    // Si no és l'última línia, sempre afegim salt de línia
                    if (i < linies.size() - 1) {
                        bw.newLine();
                    } else {
                        // Si és l'última línia, només afegim salt si l'origen en tenia
                        if (acabaAmbSalt) {
                            bw.newLine();
                        }
                    }
                }
            }

            // Missatge si tot s'ha realitzat correctament
            System.out.println("La còpia s'ha realitzat correctament.");

        } catch (IOException e) {
            // Gestió de qualsevol error durant el procés de lectura o escriptura
            System.out.println("Error: La còpia ha fallat. " + e.getMessage());
        }


    }
}