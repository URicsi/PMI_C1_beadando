# PMI_C1_beadando

Ez egy korházi/rendelői beteg nyilvántartó program ahol betegeket lehet felvenni,módosítani,törölni és listázni. A módosításokat egy xml fájlban végzi el a program.

1 List
1 majd enter megnyomása után a program a bevitel sorrendjében kiírja az összes adatot.

2 Add new patient
2 majd enter megnyomása után a program sorban kéri az adatokat

Bemenő adatok: Új beteg megadásakor először meg kell adni a beteg nevét majd a születési évét utána a lakcímét és végül a betegségét. Az egyes adatok bevitele után enterrel lép a következő műveletre. 

Adat bevitelkor a következőkre kell figyelni: - Betegség bevitelkor speciális karakterek nem használhatóak. 
                                              - A megadott évszámnak 1900 és 2022 között kell lennie és betűt nem tartalmazhat.

3 Modify a patient
3 majd enter megnyomása után a program bekéri a módosítandó személy nevét majd sorban a többi adatát. Fontos, hogy a módosítandó személy nevét pontosan adjuk meg. Az itt bevitt adattal a korábbi adatok felülírásra kerülnek. Az adatbevitel módja megegyezik a 2-es ponttal.

4 Delete a patient
4 majd enter megnyomása után a program bekéri a törlendő személy nevét. A pontos név megadása után entert nyomva törlődik a beteg minden adata. A törlés nem vissza vonható.

0 Exit 
0 majd enter megnyomása után a program futása befejeződik.

Hibaüzenetek, okaik és teendők: 
                         Not valid option. - Nem létező opció számot adott meg, javítsa az opció számot.
                         There is no patient with this name. - Olyan páciens nevet adott meg, aki nem szerepel az adatbázisban. Kérjük adjon meg érvényes nevet.
                         Birth year cannot be less than 1900 or greater 2022. - 1900-nál kevesebb vagy 2022-nél nagyobb születési évet adott meg. A megadott tartományba                                                                                 eső értéket adjon meg.
                         Not valid birth year. - Az évszám helyére nem számot adott meg. Javítsa a bevitt adatot.
                         Not existing disease. - Nem létező betegség nevet adott meg, vagy elírta a betegség nevét. Kérjük adjon meg érvényes betegség nevet.
