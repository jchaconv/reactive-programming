package pe.com.reactive.sec09Batching;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec03BufferTimeout {

    public static void main(String[] args) {

        eventStream()
                .bufferTimeout(5, Duration.ofSeconds(2))
                //.2) por lo tanto usaré bufferTimeout para procesar 5 items cada 2 secs
                //.buffer(Duration.ofSeconds(2)) //El procesamiento se da cada 2 secs
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static Flux<String> eventStream() {
        //3) si quiero que se procesen menos items puedo cambiar el 10 por 800 millis y ver el efecto
        return Flux.interval(Duration.ofMillis(10)) //1)Se redujo de 300 a 10 y procesa demasiados eventos
                .map(i -> "event: " + i);
    }


}
