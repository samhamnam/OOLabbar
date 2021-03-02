CarController är alldeles för smart eftersom den har hand om tiden och modellen. 
Den har även en del av application is sig eftersom en även hanterar tid. Men lustig nog
så ligger själva knapparna inte alls i CarController utan i view vilket gör den alldeles 
för tjock.

Till sist så har vi DrawPanel som faktiskt är en bra view eftersom den är small och ritar enbart upp.
Det ända negativa är att den själv får matcha bilarna em dess bilder vilket borde istället gjorts
i modellen, vilket gör DrawPanel lite för smart. 
