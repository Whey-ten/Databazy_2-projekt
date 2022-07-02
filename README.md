# MMORPG hra
Mandatory project from subject Databases 2 which was later summited as yearly project and was graded B. Assignment is in Slovak language.

## Zadanie
Vytvorte nasledovnú MMORPG hru, ktorá si ukladá dáta v relačnej databáze. Každý hráč hry má svoje konto, pod ktorým môže mať vytvorených viacero herných postáv. Každá postava má svoje meno, úroveň, skúsenosť a vzhľad pozostávajúci z fixného počtu čŕt (vymyslite si, no určite má pohlavie). Postava je nejakej rasy a triedy. Rasa obmedzuje triedy, z ktorých si môže postava vybrať. Trieda a úroveň postavy určujú jej tri základné vlastnosti: maximálne zdravie, obrana a sila. Postava tiež vlastní predmety, ktoré majú názov a vylepšujú tieto tri vlastnosti alebo ich ľubovoľnú kombináciu. Efektívne vlastnosti postavy sa odvíjajú od základných vlastností jej triedy, úrovne a predmetov, ktoré vlastní.

Trieda ďalej určuje schopnosti, akými postava disponuje. Schopnosti majú svoj názov, popis a faktor sily, pričom môžu byť útočného alebo liečivého charakteru. Efekt schopnosti, či už útoku alebo liečenia, sa odvíja od faktoru sily schopnosti, od efektívnej sily postavy a v prípade útočnej schopnosti aj od obrany nepriateľa, na ktorého postava útočí. Liečivé schopnosti pridávajú postave hráča alebo inej postave zdravie a útočné ho uberajú nepriateľovi, obe v hodnote efektu schopnosti.

Postava zomiera, keď sa jej minie zdravie. Postava, ktorá zabije nepriateľa, získava skúsenosť, ktorá sa odvíja od rozdielu úrovní postáv v súboji (vzorec výpočtu získanej skúsenosti si určite sami). Každá úroveň postavy určuje, koľko skúsenosti musí mať, aby sa jej úroveň zvýšila. S mŕtvou postavou sa už nedá hrať.

Hra ponúka hráčovi služby, ktoré sú spoplatnené: presun postavy inému hráčovi a zmena vzhľadu postavy. Uchováva cenu jednotlivých služieb a históriu poplatkov každého hráča. Hráč má kredit, za ktorý platí za služby, no nemusíte uvažovať o jeho kupovaní.

**CRUD operácie**  
_Hráč:_  
- vypíš zoznam hráčov a ich údajov
- vytvor hráča
- aktualizuj údaje hráča
- vymaž hráča

_Postava:_  
- vypíš zoznam postáv a ich údajov
- vytvor postavu
- pridaj postave predmet
- aktualizuj úroveň / zdravie postavy
- vymaž postavu

_Rasa:_  
- vypíš zoznam rás
- vytvor rasu
- pridaj / vymaž triedu k rase
- vymaž rasu

_Trieda:_  
- vypíš zoznam tried a ich údaje
- vypíš základné vlastnosti a schopnosti triedy
- vytvor triedu
- aktualizuj základné vlastnosti a schopnosti triedy
- vymaž triedu

_Poplatok:_  
- vypíš poplatky, ktoré hráč vykonal

**Zložitejšie doménové operácie**  
_Útok a liečenie:_  
- Odsimulujte jeden útok alebo liečenie podľa popisu vyššie. Dávajte pozor na to, aby bola akcia vykonaná izolovane, t.j. aby sa nepriateľ nemohol začať liečiť v čase, kedy práve dostal fatálny úder, alebo opačne.  

_Metamorfóza:_  
- Operácia metamorfuje dve zvolené postavy jedného hráča do jednej silnejšej postavy. Po skončení operácie obe pôvodné postavy zanikajú a hráčovi pribudne nová metamorfovaná postava. Presný algoritmus metamorfovania si vymyslite sami.  

_Prevod postavy:_  
- Implementujte prevod postavy (služba za poplatok) hráča inému hráčovi. Kupujúci hráč prevedie určený počet kreditov predávajúcemu, no predávajúci dostane menej, lebo zároveň platí za túto službu (poplatok je fixný, určite si ho). Prevod môže nastať iba vtedy, ak majú hráči dostatok kreditov.  

_Zmena vzhľadu:_  
- Implementujte možnosť zmeniť vzhľad postavy (služba za poplatok). Zmena môže nastať iba vtedy, ak má hráč dostatok kreditov (poplatok je fixný, určite si ho).  

_Vyhodnotenie Bounty Hunter:_  
- Postava, ktorá za mesiac porazila najviac nepriateľov, dostane na konci mesiaca odmenu v podobe kreditov pre hráča (zvoľte si výšku). Na túto postavu je zároveň vypísaná odmena: traja hráči, ktorí túto postavu za uplynulý mesiac porazili najviac krát, tiež získavajú odmenu (zvoľte si tri rozdielne klesajúce výšky podľa poradia).  

**Štatistiky**  
_Vyťaženosť podľa typu hráča:_  
- Hráčov zoraďte podľa toho, koľko postáv porazili všetky ich postavy dokopy za posledný týždeň. Rozdeľte hráčov podľa toho, či ich percentil tvorí 90% a viac a zvyšok. Pre obe skupiny identifikujte zvlášť deň v týždni a zvlášť hodinu, kedy porazili najviac postáv.  

_Jack Rozparovač:_  
- Pre každý týždeň a každý mesiac vypíšte postavu (ľubovoľnú), ktorá za toto obdobie porazila najviac postáv (gender-neutral Jack zabíja aj mužov) a vypíšte k nej počet porazených postáv zvlášť pre obe pohlavia.  

## Entitno-relačný model databázy
![This is an image](https://cdn.discordapp.com/attachments/795809527643635723/856503318104506368/unknown.png)

## Štatistiky generate script pri veľkých dátach
| Názov tabuľky        | Počet generovaných dát           | Priemerný čas  |
| ------------- |:-------------:| -----:|
| Players      | 100 000 | <1s |
| Characters      | 1 000 000      |   ~85s |
| Equipment | 1 000 000      |    ~3s |
| Battle_log	      | 5 000 000      |   ~180s |
| Character_martket | 100 000      |    ~9s |
| Ingame_store      | 500 000	     |   ~35s |
| Credits_history | 1 000 000      |   ~37s |
