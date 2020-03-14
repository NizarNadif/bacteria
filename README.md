# Progetto batteri informatica

## Incontro 1 (12/03/2020  10:50 - 13:50):

**Obiettivo:** discutere la modalità di lavoro e le idee che ci sono venute, decidere la cadenza degli incontri.

**Partecipanti:** tutti i membri del gruppo.

**Argomenti trattati / decisioni prese:** ci siamo confrontati fra di noi discutendo delle nostre proposte per implementare il batterio e sono emerse le seguenti proposte:
<li>Mappare il terreno e condividere le posizioni del cibo trovate da ogni batterio con gli altri per poi permettere ai batteri che non hanno trovato autonomamente del cibo di portarsi nella posizione segnalata;</li>
<li>Durante l’implementazione di questa idea ci siamo accorti che la modalità con la quale un batterio cercava il cibo in una zona ampia (200 x 200) della mappa rendeva non necessaria la condivisione delle posizioni trovate con la scansione prima citata. Abbiamo quindi deciso di non implementare al momento la prima idea per concentrarci sulla seguente;</li>
<li>Ogni batterio controlla il quadrato di lato 10 attorno a sé in cerca di cibo e si sposta nella posizione di quello più vicino.
Ogni 24 mosse così compiute il batterio controlla invece il quadrato di lato 200 attorno a sé con una distanza di 20 tra un controllo e l’altro in modo da trovare nuove zone di cibo e si sposta nella migliore fra queste. Se non trova il cibo in entrambi i casi effettua un movimento con sforzo pari a uno, utile per rendere possibile la duplicazione ma inutile per la ricerca di nuove zone.</li>
Infine abbiamo fissato le date dei prossimi incontri.

**Lavoro da svolgere per il prossimo incontro:** trovare un nome al nostro batterio e apportare singolarmente delle modifiche al batterio per poi discuterne con gli altri.

## Incontro 2 (14/03/2020 10:15 – 12:00):

**Obiettivo:** confrontarci sulle idee venuteci nei giorni passati dal precedente incontro, visionare le implementazioni e ricavare dall’unione di queste la miglior versione del batterio. Inoltre presentare il nostro batterio e le sue versioni precedenti con i loro funzionamenti logici ai professori Bugatti Alessandro e Donato Tobia.

**Partecipanti:** tutti i membri del gruppo e i professori.

**Argomenti trattati / decisioni prese:** L’incontro si è svolto in due parti, la prima fra noi membri del gruppo e la seconda con la presenza dei professori.
Durante lo svolgimento della prima parte ognuno di noi ha presentato il lavoro svolto dall’ultimo incontro spiegando le modifiche apportate al codice:
<li>L’idea di Cornacchiari è stata quella di non controllare l’area più ampia ogni 25 mosse, ma bensì di effettuare questo controllo solo se la ricerca di cibo nella zona più ristretta non ha portato a risultati. Questo fa sì che ogni batterio consumi completamente ogni zona circostante evitando di allontanarsi prima che questa venga terminata e, nel caso in cui un batterio non abbia attorno a sé del cibo cerca subito un’altra zona nella quale dirigersi.</li>
<li>Quella di Nadif riguardava il movimento del batterio: anziché far effettuare al batterio uno spostamento inutile è meglio sfruttarlo per andare alla ricerca di nuove aree di cibo. Per fare questo il batterio si muove in diagonale rimbalzando a contatto con i bordi del terreno.
È stata inoltre ridotta l’area nella quale i batteri possono muoversi in quanto le zone esterne a quest’area vengono controllate tramite la ricerca ampia del batterio; evitiamo così che questa ricerca venga effettuata fuori dai limiti del terreno, migliorando le prestazioni.</li>
Unendo queste modifiche è stata creata la prima versione del nostro batterio.
Nella seconda parte dell’incontro è stato presentato il batterio agli insegnanti ed è stata chiesta al professor Bugatti la possibilità di sfidare i batteri vincitori della scorsa edizione del torneo, egli ci ha concesso di rivelarci solo l’eventuale vittoria del nostro batterio.
**Lavoro da svolgere per il prossimo incontro:** apportare ulteriori modifiche al batterio.


