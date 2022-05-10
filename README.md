# PMI_C1_beadando

//a feladat – egy rövid összefoglaló leírás is kell az áttekintés miatt és egy részletes a pontos használathoz;
a futási környezet leírása: számítógép, operációs rendszer, memóriaméret, perifériaigény, grafikus kártya, ... (megegyezik a fejlesztői dokumentáció ugyanilyen című részével);
a használat leírása – hogyan kell a programot betölteni/elindítani, milyen kérdéseket tesz fel, mik a lehetséges válaszok, mik a program egyes lépései, lehetőségei (nagyvonalú funkcionális leírás);
//bemenő adatok, eredmények, szolgáltatások részletes leírása: mit, mikor, milyen sorrendben kell megadni (részletes funkcionális leírás);
mintaalkalmazás – példafutás. A felhasználó – főleg a betanító – ennek alapján tudja előre – gép nélkül – „elképzelni” a programot;
//hibaüzenetek és a hibák lehetséges okai – mi a teendő valamely hibaüzenet láttán.

Ez egy korházi/rendelői beteg nyilvántartó program ahol betegeket lehet felvenni,módosítani és törölni őket ezeket a módosításokat egy xml fájlban csinálja.



Bemenő adatok: Új beteg meg adásakor először meg kell adni a beteg nevét majd a születésí évét utűnna a lakcímét és végül a betegségét.



Lehetséges hibaüzenetek: Not valid option. - Ha nem létező opció számot adunk meg.
                         There is no patient with this name. - Ha módosítani vagy törölni szeretnénk valakit aki nem szerepel a listában.
                         Birth year cannot be less than 1900 or greater 2022. - Ha 1900-nál kevesebb vagy 2022-nél nagyobb születési évet adunk meg.
                         Not valid birth year. - Ha évszám helyett más adatot adunk meg.
                         Not existing disease. - Ha nem létező betegség nevet adunk meg vagy ha el írjuk a betegség nevét.
