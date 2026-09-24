package com.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PR110ReadFileTest {

    @TempDir
    Path directoriTemporal;

    @Test
    void testLlegirIMostrarFitxer() {
        // Preparació: Crear un fitxer de prova amb contingut conegut
        String nomFitxer = "GestioTasques.java";
        Path camiFitxer = directoriTemporal.resolve(nomFitxer);
        List<String> contingutEsperat = List.of(
                "public class GestioTasques {",
                "    public static void main(String[] args) {",
                "        System.out.println(\"Hola, món!\");",
                "    }",
                "}"
        );

        try {
            Files.write(camiFitxer, contingutEsperat, StandardCharsets.UTF_8);

            // Redirigir la sortida estàndard a un stream per capturar-la
            ByteArrayOutputStream sortidaCapturada = new ByteArrayOutputStream();
            PrintStream sortidaOriginal = System.out;
            System.setOut(new PrintStream(sortidaCapturada, true, StandardCharsets.UTF_8));

            // Executar el mètode a provar (llegirIMostrarFitxer)
            PR110ReadFile.llegirIMostrarFitxer(camiFitxer.toString());

            // Restaurar la sortida estàndard
            System.setOut(sortidaOriginal);

            // Processar la sortida capturada
            // "\\R" accepta qualsevol salt de línia (\n o \r\n), tant si l'alumne usa println() com "\n"
            String[] sortida = sortidaCapturada.toString(StandardCharsets.UTF_8).split("\\R");
            assertEquals(contingutEsperat.size(), sortida.length, "El nombre de línies hauria de coincidir");

            // Verificar que cada línia té el format correcte
            for (int i = 0; i < contingutEsperat.size(); i++) {
                String liniaEsperada = String.format("%d: %s", i + 1, contingutEsperat.get(i));
                assertEquals(liniaEsperada, sortida[i], "La línia hauria de coincidir");
            }

        } catch (IOException e) {
            fail("No hauria de fallar amb excepció: " + e.getMessage());
        }
    }
}
