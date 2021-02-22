### Refaktoriseringsplan
1. Skapa Application, IController, IModel, IView och, main.
   
2. Bestäm vad typerna skall vara för instansen av Application i main. Detta är viktigt
   då IController, IModel, och IView beror på dessa. I vårat fall blir det någon form
   av JComponent, och ActionEvent eftersom att vi använder Swing.
   
3. I Application definierar vi run() så att den kör update() i en loop.\
   update() skall använda de funktionerna som finns i interfaces så\
   att programmet fungerar. T.ex. så controllers getter sättas in som argument
   till update() i modellen. Klassens parametertyper skall matchas till dess interfaces 
   parametertyper genom att stoppas in som parameterargument. 

4. Skapa Transporter och JComponent specifika implementationer 
   av IController, IModel, och IView. Detta kan ske parallellt\
   eftersom att metoder och liknande kallas genom interfaces, och kräver ej 
   konkreta implementationer.


 3 och 4 går att göra parallellt

   


