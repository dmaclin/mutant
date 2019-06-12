# Mutant Rest
Ejercicio mercado libre <br>
Cobertura [![Coverage Status](https://coveralls.io/repos/github/dmaclin/mutant/badge.png)](https://coveralls.io/github/dmaclin/mutant)
<br>
## Servicios<br>
### Post --> /mutant <br>
- <b>EndPoint</b><br>
https://mutantrest-242718.appspot.com/mutant/services/mutant <br>
- <b>Consideraciones para el consumo </b><br>
  - <u>Atributos Header</u><br>
Content-Type: application/json<br>
  - <u>Response</u> <br>
  La respuesta del servicio es un json con la siguiente estructura y atributos <br>
  <b>excepcion:</b> String en donde se escribe un mensaje en caso de haber ocurrido una excepción en el servicio.<br>
  <b>huboExcepcion:</b>Atributo booleano que informa que ocurrió una excepción. Es el primer atributo que debería validar un                                       cliente del servicio, y en caso que sea true, levantar el atributo excepción. <br>
  <b>resultado:</b> Atributo donde se presenta el resultado del endpoint. En este caso es true en caso que la secuencia de ADN sea mutante y false en caso contrario.<br>
### Get --> /stats <br>
- <b>EndPoint</b><br>
https://mutantrest-242718.appspot.com/mutant/services/stats <br>
- <b>Consideraciones para el consumo </b><br>
  - <u>Atributos Header</u><br>
Content-Type: application/json<br>
  - <u>Response</u> <br>
  La respuesta del servicio es un json con la siguiente estructura y atributos <br>
  <b>excepcion:</b> String en donde se escribe un mensaje en caso de haber ocurrido una excepción en el servicio.<br>
  <b>huboExcepcion:</b>Atributo booleano que informa que ocurrió una excepción. Es el primer atributo que debería validar un                                       cliente del servicio, y en caso que sea true, levantar el atributo excepción. <br>
  <b>resultado:</b> Atributo donde se presenta el resultado del endpoint. En este caso se trata de un objeto json que contiene las estádisticas y tiene el siguiente formato (los valores son a modo de ejemplo) 
  <b>{ "count_human_dna": 1, "count_mutant_dna": 1, "ratio": 1 }</b> <br>
