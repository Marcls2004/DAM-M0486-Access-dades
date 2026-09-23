package com.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PR115cpTest {

    @TempDir
    Path directoriTemporal;

    @Test
    void testCopiarArxiuSenseSaltFinal() throws IOException {
        // Fitxer origen amb línies en blanc intermèdies i SENSE salt de línia final
        File fitxerOrigen = new File(directoriTemporal.toFile(), "origen.txt");
        String contingutOrigen = "Primera línia\n\nSegona línia\n\nTercera línia";
        Files.writeString(fitxerOrigen.toPath(), contingutOrigen, StandardCharsets.UTF_8);

        // Definir la ruta del fitxer de destí dins del directori temporal
        File fitxerDesti = new File(directoriTemporal.toFile(), "desti.txt");

        // Executar el mètode que copia l'arxiu
        PR115cp.copiarArxiu(fitxerOrigen.getPath(), fitxerDesti.getPath());

        // Comprovar que el fitxer de destí existeix
        assertTrue(fitxerDesti.exists(), "El fitxer de destí hauria d'existir");

        // La còpia ha de ser exacta: mateixes línies (també les buides) i sense salt de línia final.
        // Es normalitzen els salts (\r\n -> \n) perquè el test no depengui del sistema operatiu.
        String contingutDesti = Files.readString(fitxerDesti.toPath(), StandardCharsets.UTF_8);
        assertEquals(contingutOrigen, contingutDesti.replace("\r\n", "\n"),
                "El destí ha de ser una còpia exacta de l'origen (mateixes línies, també les buides).");
        assertFalse(contingutDesti.endsWith("\n") || contingutDesti.endsWith("\r"),
                "Si l'origen no acaba amb salt de línia, el destí tampoc.");
    }

    @Test
    void testCopiarArxiuAmbLiniaEnBlancFinal() throws IOException {
        // Crear un fitxer origen dins del directori temporal
        File fitxerOrigen = new File(directoriTemporal.toFile(), "origen.txt");
    
        // Escriure contingut al fitxer d'origen, incloent línies en blanc.
        // Files.write() afegeix un salt de línia després de cada element, també de l'últim,
        // de manera que el fitxer origen acaba amb "\n". Aquest test comprova que la còpia el conserva.
        List<String> contingutOrigen = new ArrayList<>();
        contingutOrigen.add("Primera línia");
        contingutOrigen.add(""); // Línia en blanc
        contingutOrigen.add("Segona línia");
        contingutOrigen.add(""); // Línia en blanc
        contingutOrigen.add("Tercera línia");
    
        Files.write(fitxerOrigen.toPath(), contingutOrigen, StandardCharsets.UTF_8);
    
        // Definir la ruta del fitxer de destí dins del directori temporal
        File fitxerDesti = new File(directoriTemporal.toFile(), "desti.txt");
    
        // Executar el mètode que copia l'arxiu
        PR115cp.copiarArxiu(fitxerOrigen.getPath(), fitxerDesti.getPath());
    
        // Comprovar que el fitxer de destí existeix
        assertTrue(fitxerDesti.exists(), "El fitxer de destí hauria d'existir");
    
        // Llegir tot el contingut del fitxer de destí
        List<String> contingutDesti = Files.readAllLines(fitxerDesti.toPath(), StandardCharsets.UTF_8);
    
        // Comprovar que el contingut és el mateix incloent les línies en blanc
        assertEquals(contingutOrigen, contingutDesti, "El contingut del fitxer de destí hauria de ser igual al contingut d'origen.");
    
        // Comprovar que l'arxiu acaba amb una línia en blanc (salt de línia)
        try (BufferedReader reader = new BufferedReader(new FileReader(fitxerDesti, StandardCharsets.UTF_8))) {
            int lastChar = reader.read();
            while (reader.ready()) {
                lastChar = reader.read();
            }
            assertEquals('\n', lastChar, "El fitxer hauria d'acabar amb un salt de línia.");
        }
    }
        

    @Test
    void testArxiuNoExisteix() {
        // Definir la ruta d'un fitxer origen que no existeix
        File fitxerInexistent = new File(directoriTemporal.toFile(), "inexistent.txt");

        // Definir la ruta del fitxer de destí dins del directori temporal
        File fitxerDesti = new File(directoriTemporal.toFile(), "desti.txt");

        // Executar el mètode que intenta copiar un fitxer inexistent
        PR115cp.copiarArxiu(fitxerInexistent.getPath(), fitxerDesti.getPath());

        // Comprovar que el fitxer de destí no s'ha creat
        assertFalse(fitxerDesti.exists(), "El fitxer de destí no hauria de crear-se si l'origen no existeix.");
    }
}
