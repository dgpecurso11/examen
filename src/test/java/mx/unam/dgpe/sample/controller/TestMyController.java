package mx.unam.dgpe.sample.controller;

import org.apache.log4j.Logger;
import org.junit.Test;

import io.vertx.core.AbstractVerticle;
import static mx.unam.dgpe.sample.controller.RestUtil.*;

public class TestMyController extends AbstractVerticle {
    private static final Logger logger = Logger.getLogger(TestMyController.class);
    
    @Test
    public void ok() throws Exception {
        String result = sendGet("https://www.binance.com/api/v3/ticker/price?symbol=BTCUSDT");
        logger.info(result);

//http://localhost:8081/api/suma?a=50&b=9
//http://localhost:8081/api/resta?a=50&b=9
//http://localhost:8081/api/multiplica?a=50&b=9
//http://localhost:8081/api/divide?a=50&b=9
	/*

result = sendGet("http://localhost:8081/api/suma?a=50&b=9");
 logger.info(result);

result = sendGet("http://localhost:8081/api/resta?a=50&b=9");
 logger.info(result);

result = sendGet("http://localhost:8081/api/multiplica?a=50&b=9");
 logger.info(result);

result = sendGet("http://localhost:8081/api/divide?a=50&b=9");
 logger.info(result);
*/
    }

}
