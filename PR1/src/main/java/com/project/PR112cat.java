package com.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class PR112cat {

    public static void main(String[] args) {
        // Comprovar que s'ha proporcionat una ruta com a paràmetre
        if (args.length == 0) {
            System.out.println("No s'ha proporcionat cap ruta d'arxiu.");
            return;
        }

        // Obtenir la ruta del fitxer des dels paràmetres
        String rutaArxiu = args[0];
        mostrarContingutArxiu(rutaArxiu);
    }

    // Funció per mostrar el contingut de l'arxiu o el missatge d'error corresponent
    public static void mostrarContingutArxiu(String rutaArxiu) {
        File fitxer = new File(rutaArxiu);

        //1 saber si exite ese archivo
        if (fitxer.exists() && fitxer.isDirectory()) {
            System.out.println("El path no correspon a un arxiu, sinó a una carpeta.");
            return;
        }

        // 2. Validar si el archivo NO existe
        if (!fitxer.exists()) {
            System.out.println("El fitxer no existeix o no és accessible.");
            return;
        }
        
        //3 leer el archivo con utf8
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fitxer), StandardCharsets.UTF_8))){
            String linia;
            while((linia = br.readLine()) != null){
                //4 mostrar el contenido
                System.out.println(linia);
            }
        } catch (IOException e) {
            System.out.println("Error en llegir el fitxer: " + e.getMessage());
        }
    }
}
