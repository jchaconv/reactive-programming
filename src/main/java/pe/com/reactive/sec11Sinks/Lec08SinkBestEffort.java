package pe.com.reactive.sec11Sinks;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;

import java.time.Duration;

public class Lec08SinkBestEffort {

    public static void main(String[] args) {

        // En el video establece la propiedad pero el resultado es el mismo
        // System.setProperty("reactor.bufferSize.small", "16");

        /* 1) Con directAllOrNothing el rendimiento de "Julio" se ve afectado por "Cesar"
        por lo que ambos solo pueden procesar 31 items. */
        //Sinks.Many<Object> sink = Sinks.many().multicast().directAllOrNothing();

        /* 2) Por eso usamos la opción directBestEffort y de esta manera "Julio"
        procesa en toda su capacidad */
        Sinks.Many<Object> sink = Sinks.many().multicast().directBestEffort();

        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("Julio"));

        //Simulamos que este pipeline tarda en procesar los items
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("Cesar"));

        for (int i = 0; i < 100; i++) {
            sink.tryEmitNext(i);
        }

        Util.sleepSeconds(10);
    }

}
