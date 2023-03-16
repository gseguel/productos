Se debe ejecutar proyecto con Spring boot
una vez inicializado se pueden realizar pruebas del back con la siguiente URL de swagger 
http://localhost:8080/swagger-ui.html o con postman


Se realizo proyecto con:
Maven
MVC/springboot
Java 11

para persistencia se utilizo MyIbatis ya que ahorra mucho codigo JDBC
Se utilizo base de datos postgres
Se añadio Swagger
La arquitectura utilizada es:
- config(configuracion de swagger)
- Controller
- Service
- Repository (contiene mapper para las consultas a base de datos)
- model
- exceptions (Se crearon excepciones para errores mas descriptivos)

Se realizaron JUNIT con Mockito cumpliendo con el 87% de cobertura

Se utilizo Range para la validacion de la secuencia en la insercion de SKU

Se utilizo Optional para el retorno de un objeto o empty, para asegurarme que no llegara null
