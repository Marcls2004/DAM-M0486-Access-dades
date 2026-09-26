package com.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PR115cp {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Error: Has d'indicar dues rutes d'arxiu.");
            System.out.println("Ús: PR115cp <origen> <destinació>");
            return;
        }

        String rutaOrigen = args[0];
        String rutaDesti = args[1];

        copiarArxiu(rutaOrigen, rutaDesti);
    }

    public static void copiarArxiu(String rutaOrigen, String rutaDesti) {
        // 1. Convertir a objectes Path (API NIO.2)
        Path origen = Paths.get(rutaOrigen);
        Path desti = Paths.get(rutaDesti);

        // 2. Verificació de l'existència i que sigui fitxer regular
        if (!Files.exists(origen) || !Files.isRegularFile(origen)) {
            System.out.println("Error: El fitxer d'origen no existeix o és una carpeta.");
            return;
        }

        // 3. Advertència si el fitxer de destinació ja existeix
        if (Files.exists(desti)) {
            System.out.println("Advertència: El fitxer de destinació ja existeix i serà sobreescrit.");
        }

        try {
            // 4. Determinar si acaba en salt de línia usant String i un sencer (int)
            String contingut = Files.readString(origen, StandardCharsets.UTF_8);
            int ultimCaracter = -1;

            if (contingut.length() > 0) {
                ultimCaracter = contingut.charAt(contingut.length() - 1);
            }

            // 5. Còpia línia a línia en UTF-8 usant els Buffers obligatoris
            try (BufferedReader br = Files.newBufferedReader(origen, StandardCharsets.UTF_8);
                 BufferedWriter bw = Files.newBufferedWriter(desti, StandardCharsets.UTF_8)) {

                String linia = br.readLine();
                if (linia != null) {
                    bw.write(linia);
                    
                    while ((linia = br.readLine()) != null) {
                        bw.newLine(); // Afegeix el salt per a la línia següent
                        bw.write(linia);
                    }
                }

                // 6. Si l'últim caràcter era un salt de línia ('\n' és 10, '\r' és 13), l'afegim al destí
                if (ultimCaracter == 10 || ultimCaracter == 13) {
                    bw.newLine();
                }
            }

            System.out.println("La còpia s'ha realitzat correctament.");

        } catch (IOException e) {
            System.out.println("Error: La còpia ha fallat. " + e.getMessage());
        }
    }
}
