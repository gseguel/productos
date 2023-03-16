
Se realizo proyecto con:
Maven
MVC/springboot
Java 11

Se deben actualizar las dependencias de maven y levantar el proyecto spring boot.

Se pueden realizar pruebas directamente en el back con la siguiente URL de swagger o desde el frontend.

swagger:
http://localhost:8090/swagger-ui.html o con postman

frontend:
http://localhost:4200/


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

En base de datos se creo una sequencia para el SKU.
