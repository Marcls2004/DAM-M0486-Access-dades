# MP0486-RA1 - PR1.1 Lectura i escriptura d'arxius en Java #

[![Java CI with Maven](https://github.com/jpala4-ieti/DAM-M0486-RA1-PR1.1-Practica-Punt-Partida-26-27/actions/workflows/maven.yml/badge.svg)](https://github.com/jpala4-ieti/DAM-M0486-RA1-PR1.1-Practica-Punt-Partida-26-27/actions/workflows/maven.yml)

Pràctica avaluable del RA1 (persistència en fitxers). Cal completar els mètodes buits de les classes del paquet `com.project` fins que **tots els tests** de `src/test` passin. Els tests són l'especificació: llegiu-los abans de programar.

Es valora, a més del funcionament, la gestió d'excepcions (CA 1.6) i que el codi estigui comentat (CA 1.7).

### Normes comunes ###

* Tots els fitxers de text es llegeixen i s'escriuen amb **UTF-8** (`StandardCharsets.UTF_8`), mai amb el charset per defecte.
* Els mètodes reben la ruta com a paràmetre i **no** fan `System.exit()`; els errors es gestionen dins del mètode.
* Els fitxers de treball es creen dins de la carpeta `data/` del projecte (els `main` ja passen la ruta correcta). Els tests, en canvi, treballen sobre un directori temporal.
* Cap mètode pot llençar excepcions cap enfora (`throws`) llevat que el punt de partida ja ho indiqui.

### Exercicis ###

**PR110ReadFile** — `llegirIMostrarFitxer(String camiFitxer)`
Mostra per pantalla el contingut del fitxer línia a línia, precedint cada línia pel seu número començant per 1, amb el format `N: contingut` (dos punts i un espai). Proveu-ho amb `data/GestioTasques.java`.

**PR111Files** — `gestionarArxius(String camiDirectori)`
Dins de la ruta rebuda: crea la carpeta `myFiles`, hi crea dos fitxers buits `file1.txt` i `file2.txt`, mostra el llistat de la carpeta, reanomena `file2.txt` a `renamedFile.txt`, esborra `file1.txt` i torna a mostrar el llistat. Al final només ha de quedar `renamedFile.txt`.

**PR112cat** — `mostrarContingutArxiu(String rutaArxiu)`
Versió mínima de la comanda `cat`. Rep la ruta com a argument de línia de comandes i mostra el contingut. Missatges exactes en cas d'error:
* si la ruta és una carpeta: `El path no correspon a un arxiu, sinó a una carpeta.`
* si no existeix o no es pot llegir: `El fitxer no existeix o no és accessible.`

**PR113sobreescriu** — `escriureFrases(String camiFitxer)`
Escriu al fitxer, sobreescrivint-lo si existeix, aquestes dues línies. Cada línia, també l'última, acaba amb un salt de línia (`\n`); no s'ha d'afegir cap línia en blanc addicional:
```
I can only show you the door
You're the one that has to walk through it
```

**PR113append** — `afegirFrases(String camiFitxer)`
Igual que l'anterior, però afegint les frases al final del fitxer sense esborrar el que ja hi havia. Executat dues vegades sobre un fitxer buit, el fitxer ha de tenir exactament quatre línies, cadascuna acabada amb un salt de línia.

**PR114linies** — `generarNumerosAleatoris(String camiFitxer)`
Genera 10 números enters aleatoris i els escriu al fitxer, un per línia. A diferència del PR113, l'última línia **no** ha d'acabar amb salt de línia.

**PR115cp** — `copiarArxiu(String rutaOrigen, String rutaDesti)`
Versió mínima de la comanda `cp` per a fitxers de text. Rep origen i destí com a arguments. La còpia ha de ser exacta: conserva les línies en blanc intermèdies i el salt de línia final si l'origen en té (i no n'afegeix cap si no en té). Si l'origen no existeix, no s'ha de crear el fitxer de destí.

### Compilació i execució ###

Cal Maven i JDK 21.
```bash
mvn clean compile
```

Executar una classe concreta (Windows / Linux-macOS):
```bash
.\run.ps1 com.project.PR110ReadFile
./run.sh com.project.PR110ReadFile
```

Els scripts accepten també els arguments del programa:
```bash
.\run.ps1 com.project.PR112cat data/GestioTasques.java
./run.sh com.project.PR115cp data/GestioTasques.java data/copia.java
```

O bé directament amb Maven:
```bash
mvn compile exec:java -PrunMain "-Dexec.mainClass=com.project.PR112cat" "-Dexec.args=data/GestioTasques.java"
mvn compile exec:java -PrunMain "-Dexec.mainClass=com.project.PR115cp" "-Dexec.args=data/GestioTasques.java data/copia.java"
```

### Execució de tests ###
```bash
# Tots els tests
mvn test
# Un test concret
mvn test -Dtest=PR112catTest
# Tots els tests d'una família
mvn test -Dtest="PR113*"
```

### Lliurament ###

* Treballeu en un **repositori privat** propi (a partir d'aquest punt de partida) i compartiu-lo amb l'usuari `jpala4-ieti`.
* Feu commit i push a cada avenç. El workflow de GitHub Actions executa els tests a cada push: en el lliurament final el semàfor del repositori ha de quedar en verd.
* Deseu la memòria (l'enunciat en PDF, amb el nom i l'enllaç al repositori emplenats) a `doc/memoria.pdf`.
* Lliureu l'URL del repositori a Moodle.

### Visual Studio Code: resseteig de l'entorn de programació Java ###

Si Visual Studio Code no es comporta com esperem:

* Recarregar la finestra: Paleta de Comandes (**Ctrl+Maj+P**), "**Developer: Reload Window**".
* Netejar l'espai de treball: Paleta de Comandes (**Ctrl+Maj+P**), "**Java: Clean Java Language Server Workspace**".
