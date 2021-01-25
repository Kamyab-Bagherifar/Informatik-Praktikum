# Template-Repository für folgende Aufgaben (siehe Moodle)
- ADT List in Java
- Stack und Queue
- ADT Set über ADT List implementieren
- ADT Map mit ListSet implementieren
- ADT Set und ADT Map mit Tree implementieren
- Graphen

## Anpassung an Maven

Die Datei test/BUILD können Sie löschen,
es sei denn Sie nutzen Bazel als Build-System.

Für Maven müssen Sie die Verzeichnisstruktur an die Maven-Konventionen anpassen.

Dazu `src` in `src/main/java`
und `test` in `src/test/java`
verschieben!

Die Hilfsklasse `list.JqwikUtils` benötigt die Klasse
`SerializationUtils` der Apache Commons Lang 3.9 API!

Sie müssen den Import für das Interface `Function` anpassen!
Verzeichnisse mit Code für noch nicht aktuelle Aufgaben müssen Sie in Intellij über "Project Structure" erst mal "excluden".

## Mindestanforderungen für Abnahme des Codes:

- Ihre Programme enthalten keine der folgenden Anweisungen: Schleifen (while, for),  ++, --, +=, -=
- Alle Variablen sind final.
- Alle Datenfelder sind final.
- Alle Konstruktoren sind final und private.
- Keine Methode hat den Rückgabetyp void.
- Jede if-Anweisung hat einen else-Zweig.
  (Am Besten Sie verwenden immer den ternären Ausdruck: ...?...:... )
- Sie müssen erklären können,
  welche Nachteile Programme haben,
  die o.g. Anforderungen nicht erfüllen!
