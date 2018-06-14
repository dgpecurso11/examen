package mx.unam.dgpe.sample.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class Calculadora extends AbstractVerticle {

    private static final Logger logger = Logger.getLogger(Calculadora.class);

    public void start(Future<Void> fut) {
        logger.info("Inicializando Vertical");
        Router router = Router.router(vertx);
        ;
        // System.out.println("-------------> "+ router.route("/*").handler(StaticHandler.create("assets")));
        // el directorio "upload-folder" será creado en la misma ubicación que el jar fue ejecutado
        router.route().handler(BodyHandler.create().setUploadsDirectory("upload-folder"));

        router.get("/api/suma").handler(this::suma);
        router.get("/api/resta").handler(this::resta);
        router.get("/api/multiplica").handler(this::multiplica);
        router.get("/api/divide").handler(this::divide);
        router.route("/*").handler(StaticHandler.create("assets")); // para invocar asi: http://localhost:8080/index.html
        System.out.println("-------------> " + router.route("/*"));

        // Create the HTTP server and pass the "accept" method to the request handler.
        vertx.createHttpServer().requestHandler(router::accept).listen(
                config().getInteger("http.port", 9090), result -> {
            if (result.succeeded()) {
                fut.complete();
            } else {
                fut.fail(result.cause());
            }
        });

        logger.info("Vertical iniciada !!!");
    }

    private void suma(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();

        String operandoA = request.getParam("a");
        String operandoB = request.getParam("b");
        String jsonResponse = sumaOperacion(operandoA, operandoB, request);
        response.setStatusCode(200).
                putHeader("content-type", "application/json; charset=utf-8").
                end(jsonResponse);
    }

    private void resta(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();

        String operandoA = request.getParam("a");
        String operandoB = request.getParam("b");
        String jsonResponse = restaOperacion(operandoA, operandoB);
        response.setStatusCode(200).
                putHeader("content-type", "application/json; charset=utf-8").
                end(jsonResponse);
    }

    private void multiplica(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();

        String operandoA = request.getParam("a");
        String operandoB = request.getParam("b");
        String jsonResponse = multiplicaOperacion(operandoA, operandoB);
        response.setStatusCode(200).
                putHeader("content-type", "application/json; charset=utf-8").
                end(jsonResponse);
    }

    private void divide(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();

        String operandoA = request.getParam("a");
        String operandoB = request.getParam("b");
        String jsonResponse = divideOperacion(operandoA, operandoB);
        response.setStatusCode(200).
                putHeader("content-type", "application/json; charset=utf-8").
                end(jsonResponse);
    }

    //http://localhost:8081/api/suma?a=5&b=9
    private String sumaOperacion(String operandoA, String operandoB, HttpServerRequest request) {

        int r = 0;

        if (operandoA != "" && operandoB != "") {
            r = Integer.parseInt(operandoA) + Integer.parseInt(operandoB);
        } else {
            r = -1;
        }

        Map<String, String> resultado = new HashMap<>();
        resultado.put("operación", "suma");
        resultado.put("resultado", "" + r);
        resultado.put("current node IP", "" + request.localAddress().host());
        resultado.put("caller ip", "" + request.remoteAddress().host());

        return Json.encodePrettily(resultado);
    }

    //http://localhost:8081/api/resta?a=50&b=9
    private String restaOperacion(String operandoA, String operandoB) {

        int r = 0;

        if (operandoA != "" && operandoB != "") {
            r = Integer.parseInt(operandoA) - Integer.parseInt(operandoB);
        } else {
            r = -1;
        }

        Map<String, String> resultado = new HashMap<>();
        resultado.put("operación", "resta");
        resultado.put("resultado", "" + r);

        return Json.encodePrettily(resultado);
    }

    //http://localhost:8081/api/multiplica?a=50&b=9
    private String multiplicaOperacion(String operandoA, String operandoB) {

        int r = 0;

        if (operandoA != "" && operandoB != "") {
            r = Integer.parseInt(operandoA) * Integer.parseInt(operandoB);
        } else {
            r = -1;
        }

        Map<String, String> resultado = new HashMap<>();
        resultado.put("operación", "multiplicacion");
        resultado.put("resultado", "" + r);

        return Json.encodePrettily(resultado);
    }

    //http://localhost:8081/api/divide?a=50&b=9
    private String divideOperacion(String operandoA, String operandoB) {

        int r = 0;

        if (operandoA != "" && operandoB != "") {
            r = Integer.parseInt(operandoA) - Integer.parseInt(operandoB);
        } else {
            r = -1;
        }

        Map<String, String> resultado = new HashMap<>();
        resultado.put("operación", "división");
        resultado.put("resultado", "" + r);

        return Json.encodePrettily(resultado);
    }

    public static void main(String[] args) {

        System.out.println("HOLA");

    }

}
