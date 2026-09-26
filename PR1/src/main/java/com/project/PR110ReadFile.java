package com.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class PR110ReadFile {

    public static void main(String[] args) {
        String camiFitxer = System.getProperty("user.dir") + "/data/GestioTasques.java";
        llegirIMostrarFitxer(camiFitxer);  // Només cridem a la funció amb la ruta del fitxer
    }

    // Funció que llegeix el fitxer i mostra les línies amb numeració
    public static void llegirIMostrarFitxer(String camiFitxer) {
         // Usamos try-with-resources para asegurar que el archivo se cierre automáticamente
        try (BufferedReader br = new BufferedReader(new FileReader(camiFitxer))){
            String linia;
            int numeroLinia = 1;

            // Leemos el archivo línea por línea hasta el final
            while ((linia = br.readLine()) != null){
                System.out.println(numeroLinia + ": " + linia);
                numeroLinia++;
            }
        } catch (FileNotFoundException e){
            System.out.println("Error: El fitxer no existeix a la ruta especifica.");
        }catch (IOException e){
            System.out.println("Error en llegir el fitxer: " + e.getMessage());
        }
    }
}
