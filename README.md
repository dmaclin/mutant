# Mutant Rest
Ejercicio mercado libre <br>
# Cobertura Testing
[![Coverage Status](https://coveralls.io/repos/github/dmaclin/mutant/badge.png)](https://coveralls.io/github/dmaclin/mutant)
<br>
# Servicios<br>
## /mutant <br>
### EndPoint<br>
https://mutantrest-242718.appspot.com/mutant/services/mutant <br>
### Request
-   HTTP Method: POST
-   Content-Type: Application/JSON

#### JSON de Ingreso
     {
        "dna": [
        "TCGT",
        "AGTC",
        "TTAT",
        "TGAG"]
    }
### Response
- HTTP Status 200, 403 o 503 <br>
  Al código de estado se anexa, como respuesta del servicio, un json con la siguiente estructura y atributos <br>
  <b>excepcion:</b> String en donde se escribe un mensaje en caso de haber ocurrido una excepción en el servicio.<br>
  <b>huboExcepcion:</b>Atributo booleano que informa que ocurrió una excepción. Es el primer atributo que debería validar un                                       cliente del servicio, y en caso que sea true, levantar el atributo excepción. (status 503) <br>
  <b>resultado:</b> Atributo donde se presenta el resultado del endpoint. En este caso es true (status 200) en caso que la secuencia de ADN sea mutante y false (status 403> en caso contrario.<br>
  
## /stats <br>
 ### EndPoint <br>
https://mutantrest-242718.appspot.com/mutant/services/stats <br>
 ### Request
-   HTTP Method: GET
-   Content-Type: Application/JSON

 ### Response
  - HTTP Status 200, 403 o 503 <br>
  La respuesta del servicio es un json con la siguiente estructura y atributos <br>
  <b>excepcion:</b> String en donde se escribe un mensaje en caso de haber ocurrido una excepción en el servicio.<br>
  <b>huboExcepcion:</b>Atributo booleano que informa que ocurrió una excepción. Es el primer atributo que debería validar un                                       cliente del servicio, y en caso que sea true, levantar el atributo excepción. (status 503)<br>
  <b>resultado:</b> Atributo donde se presenta el resultado del endpoint. En este caso se trata de un objeto json que contiene las estádisticas (status 200) y tiene el siguiente formato (los valores son a modo de ejemplo) 
  <b>{ "count_human_dna": 1, "count_mutant_dna": 1, "ratio": 1 }</b> <br> Si no hay estadísticas en la base devuelve el status 403.
