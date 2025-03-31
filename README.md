
### Instrucciones de uso:


1. Asegurarse de tener instalado docker y docker-compose y openjdk 21 y tener configurado java 21
2. Correr `docker-compose up` en una terminal en la raiz del proyecto para levantar contenedor mysql (esto crea la bd demoitpdb, root/1234 en localhost:3306)
3. Correr `mvn clean spring-boot:run` en una nueva ventana terminal
4. Ingresar a `http://localhost:8001/swagger-ui/index.html` para ver la interfaz de swagger
5. Ingresar con admin/1234


### Consigna:

### El challenge se trata de generar los siguientes 3 endpoints:

- Uno que traiga las empresas que hicieron transferencias el último mes
- Otro que traiga las empresas que se adhirieron el último mes.
- El último que haga la adhesión de una empresa.

__Deseable:__ usar arquitectura hexagonal
__Base:__ puede usarse relacional o no relacional

__Datos de la empresa:__ CUIT, Razón Social, Fecha Adhesión

__Datos de la transferencia:__ Importe, Id Empresa, Cuenta Débito, Cuenta Crédito.
