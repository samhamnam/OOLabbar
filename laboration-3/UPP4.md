3.2
De båda viewsen måste ha tillgång till samma model.\
3.3
Modellen borde bestämma vad vilken view skall ha.

Observer:
* Finns det något ställe i er design där ni redan använder detta pattern?\
Nej det har vi ej.
* Finns det något ställe där ni kan förbättra er design genom att använda detta design pattern?\
Relationen mellan controllers och modellen skulle kunna förbättras med den. Det skulle bli mer ocp
  eftersom den sista modellen inte behöver kalla på cleaEvents efter varje tick/frame. Vilket bryter mot ocp 
  eftersom man måste gå in och redigera existerande kod om man vill lägga till en nya model med samma controller. 
  
 

Factory method:
* Finns det något ställe i er design där ni redan använder detta pattern?\
  Nej.
* Finns det något ställe där ni kan förbättra er design genom att använda detta design pattern?\
Det skulle kunna användas för att skapa bilarna då de kan ta in olika navigators och liknande. Det skulle
  även kunna försimpla main men det hjälper inte direkt någon design princip eftersom de enbart är långa rader 
  utan någon logik. Vi skulle kunna göra ett factory för views som är tänkt till samma window och låta den factory 
  själv beräkna hur den skal dimensionera views i en window. Detta skulle hjälpa lite med ocp eftrsom det skulle 
  bli lättare att lägga till nya views. 


State:
* Finns det något ställe i er design där ni redan använder detta pattern?\
  Närmsta är Transporter med Navigation som skulle kunna bytas ut under runtime, och det skulle
  fundamentalt ändra hur bilarna kontrolleras. Annars är det hur events "matchas" då man trycker på knapparna
  eftersom att varje knapp kör olika funktioner, och de skulle även kunna bytas ut under runtime.
* Finns det något ställe där ni kan förbättra er design genom att använda detta design pattern?\
  Inte vad vi kan komma på.
  
Composite:
* Finns det något ställe i er design där ni redan använder detta pattern?\
  Våra Views är likt composite då de representerar flera komponenter som sätts in i en JPanel.
  Och varje JPanel kan innehålla JComponents. På så sätt kan vi gömma 
* Finns det något ställe där ni kan förbättra er design genom att använda detta design pattern?\

