# Progetto batteri informatica

## Incontro 1 (12/03/2020 10:50 - 13:50):

***Obiettivo:*** discutere la modalità di lavoro e le idee che ci sono venute, decidere la
cadenza degli incontri.

***Partecipanti:*** tutti i membri del gruppo.

***Argomenti trattati / decisioni prese:*** abbiamo discusso le nostre idee per il
funzionamento del batterio e sono emerse le seguenti proposte:
* Mappare il terreno e condividere le posizioni del cibo trovate da ogni batterio con gli altri per poi permettere ai batteri che non hanno trovato autonomamente del cibo di portarsi nella posizione segnalata;
* Durante l’implementazione di questa idea ci siamo accorti che la modalità con la quale un batterio cercava il cibo in una zona ampia (200 x 200) della mappa rendeva non necessaria la condivisione delle posizioni trovate con la scansione prima citata. Abbiamo quindi deciso di non implementare al momento la prima idea per concentrarci sulla seguente;
* Ogni batterio controlla il quadrato di lato 10 attorno a sé in cerca di cibo e si sposta nella posizione di quello più vicino. Ogni 24 mosse così compiute il batterio controlla invece il quadrato di lato 200 attorno a sé con una distanza di 20 tra un controllo e l’altro in modo da trovare nuove zone di cibo e si sposta nella migliore fra queste. Se non trova il cibo in entrambi i casi effettua un movimento con sforzo pari a uno, utile per rendere possibile la duplicazione ma inutile per la ricerca di nuove zone.
Infine abbiamo fissato le date dei prossimi incontri.

***Lavoro da svolgere per il prossimo incontro:*** trovare un nome al nostro batterio e
apportare singolarmente delle modifiche al batterio per poi discuterne con gli altri.


## Incontro 2 (14/03/2020 10:15 – 12:00):

***Obiettivo:*** confrontarci sulle idee venuteci nei giorni passati dal precedente ## Incontro,
visionare le implementazioni e ricavare dall’unione di queste la miglior versione del
batterio. Inoltre presentare il nostro batterio e le sue versioni precedenti con i loro
funzionamenti logici ai professori Bugatti Alessandro e Donato Tobia.

***Partecipanti:*** tutti i membri del gruppo e i professori.

***Argomenti trattati / decisioni prese:*** L’incontro si è svolto in due parti, la prima fra noi
membri del gruppo e la seconda con la presenza dei professori. Durante lo svolgimento
della prima parte ognuno di noi ha presentato il lavoro svolto dall’ultimo incontro
spiegando le modifiche apportate al codice:
*  L’idea di Cornacchiari è stata quella di non controllare l’area più ampia ogni 25 mosse, ma bensì di effettuare questo controllo solo se la ricerca di cibo nella zona più ristretta non ha portato a risultati. Questo fa sì che ogni batterio consumi completamente ogni zona circostante evitando di allontanarsi prima che questa venga terminata e, nel caso in cui un batterio non abbia attorno a sé del cibo cerca subito un’altra zona nella quale dirigersi.
*  Quella di Nadif riguardava il movimento del batterio: anziché far effettuare al batterio uno spostamento inutile è meglio sfruttarlo per andare alla ricerca di nuove aree di cibo. Per fare questo il batterio si muove in diagonale rimbalzando a contatto con i bordi del terreno. È stata inoltre ridotta l’area nella quale i batteri possono muoversi in quanto le zone esterne a quest’area vengono controllate tramite la ricerca ampia del batterio; evitiamo così che venga effettuata fuori dai limiti del terreno, migliorando le prestazioni.
Unendo queste modifiche è stata creata la prima versione del nostro batterio.
Nella seconda parte dell’incontro è stato presentato il batterio agli insegnanti ed è stata
chiesta al professor Bugatti la possibilità di sfidare i batteri vincitori della scorsa edizione
del torneo, egli ci ha concesso di rivelarci solo l’eventuale vittoria del nostro batterio.
Il professor Bugatti ci ha inoltre detto che l’idea di Cornacchiari richiedeva troppe
operazioni, andando contro alle indicazioni inizialmente date per il progetto; abbiamo
quindi deciso di dividere le 2 ricerche in modo da effettuarle in 2 “turni di gioco” diversi,
così da rientrare nelle 100 operazioni poste come limite.

***Lavoro da svolgere per il prossimo incontro:*** apportare ulteriori modifiche al batterio,
provando anche ad implementare nuove idee (come quella originale riguardante la
segnalazione delle zone tra i vari batteri).


## Incontro 3 (17/03/2020 11:20 – 12:20):

***Obiettivo:*** visionare il lavoro svolto dagli altri membri del gruppo, scambiandoci consigli e
opinioni.

***Partecipanti:*** tutti i membri del gruppo.

***Argomenti trattati / decisioni prese:*** ognuno ha presentato il lavoro svolto dall’ultimo
incontro:
*  Maniu ha trovato la grandezza migliore per le zone da scansionare;
*  Nadif ha corretto alcuni errori presenti nel codice relativi allo spostamento, portando ad un lieve miglioramento;
*  Singh ha presentato un’idea che gli era venuta in mente e dopo una discussione nel gruppo ha deciso di implementarla in un batterio per il prossimo incontro;
*  Cornacchiari ha sviluppato la prima idea che ci era venuta durante il primo incontro, constatando però che il batterio realizzato ha prestazioni peggiori rispetto al migliore fino ad ora creato e giungendo così alla conclusione che l’idea implementata in quest’ultimo è quella da utilizzare;

***Lavoro da svolgere per il prossimo incontro:*** sviluppare le idee nuove e realizzare il
diagramma UML delle classi.


## Incontro 4 (21/03/2020 09:45 – 10:45):

***Obiettivo:*** prendere visione del lavoro svolto dall’ultimo incontro e presentare i progressi
ai docenti.

***Partecipanti:*** Cornacchiari, Nadif, Singh e i professori.

***Argomenti trattati / decisioni prese:*** nel corso dell’incontro abbiamo testato i batteri
sviluppati i quali implementavano l’idea venuta a Singh l’ultima volta, constatando però
che non riuscivano a prevalere sulla versione precedente. Abbiamo poi presentato il nostro
lavoro ai docenti, mostrando anche il diagramma UML realizzato.

***Lavoro da svolgere per il prossimo incontro:*** cercare delle migliorie da effettuare al
codice, pensare al nome da dare al nostro batterio.


## Incontro 5 (26/03/2020 10:45 – 12:00):

***Obiettivo:*** confrontarsi con i docenti per avere le indicazioni finali prima della consegna
del progetto.

***Partecipanti:*** Cornacchiari, Nadif, Singh e i professori.

***Argomenti trattati / decisioni prese:*** durante l’ultimo incontro con i docenti prima della
consegna, il professor Bugatti ci ha detto, dopo aver incontrato anche gli altri gruppi, che
potevamo non suddividere le ricerche in 2 turni diversi, in quanto anche gli altri
superavano leggermente il limite posto. Singh ha inoltre presentato una diversa
implementazione dell’idea che gli era venuta negli incontri precedenti, ma anche questa
peggiore della versione precedente del batterio.
Ci siamo poi suddivisi il lavoro da svolgere per realizzare la documentazione richiesta per
la consegna.

***Lavoro da svolgere per il prossimo incontro:*** svolgere il compito assegnato relativo ai
vari aspetti del progetto (video, diario, UML, …) per poterlo poi unire con quello degli altri
membri del gruppo.