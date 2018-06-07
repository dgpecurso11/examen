# examen
Examen del curso básico de microservicios


-----------------------------------------------------------------------------------------------------------------------------------
OK
1.- Crear repositorio llamado examen en github.
-----------------------------------------------------------------------------------------------------------------------------------
OK
2.- Clonar el respo de gustavo Vertx.
-----------------------------------------------------------------------------------------------------------------------------------
3.- Crear 4 servicios REST del tipo GET, que tenga una clase llamada Calculadora con los 4 siguientes servicios:
    -Suma
    -Resta
    -División
    -Multiplicación
-----------------------------------------------------------------------------------------------------------------------------------
4.- Agregar pruebas unitarias para cada operación.

result = sendGet("http://localhost:8081/api/suma?a=50&b=9");
 logger.info(result);

result = sendGet("http://localhost:8081/api/resta?a=50&b=9");
 logger.info(result);

result = sendGet("http://localhost:8081/api/multiplica?a=50&b=9");
 logger.info(result);

result = sendGet("http://localhost:8081/api/divide?a=50&b=9");
 logger.info(result);
-----------------------------------------------------------------------------------------------------------------------------------
OK
5.- Subir todo al repo examen.

https://github.com/dgpecurso11/examen
-----------------------------------------------------------------------------------------------------------------------------------

6.- Poner un readme con todas las instruccioenes para correr el servidor Vertx desde un docker con java.

#Compilar el proyexto examen con un docker, yo selecciones el kebblar/jdk18-utf8-debug-maven
docker run -it -v /home/gustavo/curso-dgpe/examen/:/codigo kebblar/jdk18-utf8-debug-maven mvn -f /codigo clean package


#Correr el jar con el docker kebblar/jdk18-utf8-debug-maven
#Para probar entrar a la siguiente URL: 
docker run -d -p 8081:8080 -v /home/gustavo/curso-dgpe/examen/:/codigo kebblar/jdk18-utf8-debug-maven java -jar /codigo/target/sample-1.0-SNAPSHOT-fat.jar



PRUEBAS

http://localhost:8081/api/suma?a=50&b=9
http://localhost:8081/api/resta?a=50&b=9
http://localhost:8081/api/multiplica?a=50&b=9
http://localhost:8081/api/divide?a=50&b=9


-----------------------------------------------------------------------------------------------------------------------------------
OK
7.- Escribir en el readme como seria el proceso para balancear 6 contenedores iguales al interior usando haproxy. 

7.1.- Editar el siguiente archivo: sudo nano /etc/default/haproxy 
################################################################################
# Defaults file for HAProxy
#
# This is sourced by both, the initscript and the systemd unit file, so do not
# treat it as a shell script fragment.

# Change the config file location if needed
CONFIG="/etc/haproxy/haproxy.cfg"
ENABLED=1
# Add extra flags here, see haproxy(1) for a few options
#EXTRAOPTS="-de -m 16"
################################################################################


7.2.- Editar el siguiente archivo: sudo nano /etc/haproxy/haproxy.cfg 
################################################################################


frontend www
        bind localhost:9090
        default_backend site-backend
backend site-backend
        mode http
        balance roundrobin
        server lamp1 localhost:8081 check
        server lamp2 localhost:8082 check
        server lamp3 localhost:8083 check
        server lamp1 localhost:8084 check
        server lamp2 localhost:8085 check
        server lamp3 localhost:8086 check
################################################################################


7.3- entrar a la siguinete url: http://localhost:9090/api/operacionGet?tipo=suma&a=5&b=9

-----------------------------------------------------------------------------------------------------------------------------------



