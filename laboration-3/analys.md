### Analys
**DrawPanel**:\
*Ansvarsområden*: Att rita upp alla bilarna och bakgrunden.

Denna är ok, den har ett konkret ansvarsområde och delegerar ut ansvaret för
att knyta ihop bilar och dess bild.


**CarView**:\
*Ansvarsområden*: Hanterar funktionen på alla knappar och innehåller alla knappar.
Knyter även ihop DrawPanel och en CarController så att det logiska kan kommunicera 
med det grafiska. Är den som bestämmer vad som skall målas.

Det blir fel när den både bestämmer över vad skärmen innehåller
samt att den håller i att uppdatera logiken i CarController. Att den har en DrawPanel är ok 
eftersom det är en sak som skall målas. Dessutom så är knapparna en egen grej skilt från grafik(view), 
därför bör de skapas knytas till en händelse någon annan stans.


**CarController**:\
*Ansvarsområden*: Hanterar bilarna, och är det som CarView kallar på för att kontrollera bilarna. 
Är även våran main klass, och hanterar start och tid/uppdatering av programmet,
och i uppdateringen så sker målandet.\
Denna klassen tar alltså hand om alldeles för mycket och den borde bara ta hand
om att skicka instruktioner till bilarna. Det värsta brottet mot SoC är att den även har hand om
uppdateringen av spelet. Detta skapar följdproblemet att den logiska biten nu måste hantera även det visuella 
då detta är knytet till uppdatering av programmet. 

<!--
**Bilar**:\
*Ansvarsområden*: bra :) yayy! jäte best :-) (^:
-->