package pe.com.reactive.sec11Sinks;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

//To disable history
public class Lec07SinkMultiDisable {

    public static void main(String[] args) {

        //Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();
        //2) Se agrega directAllOrNothing para borrar el historial de los mensajes sin enviar
        Sinks.Many<Object> sink = Sinks.many().multicast().directAllOrNothing();

        Flux<Object> flux = sink.asFlux();

        //1) Si no hay subscribers creados estos mensajes se guardan en la cola
        //y son enviados al primer subscriber activo
        sink.tryEmitNext("Hi");
        sink.tryEmitNext("how are you");

        flux.subscribe(Util.subscriber("Julio"));
        flux.subscribe(Util.subscriber("Cesar"));
        sink.tryEmitNext("?");


    }


}
