package pe.com.reactive.sec11Sinks;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec09Replay {

    /* Esta opción se usa para guardar todos los mensajes en memoria y puedan
    ser emitidos a todos los subscribers independientemente de su creación. */

    public static void main(String[] args) {

        Sinks.Many<Object> sink = Sinks.many().replay().all();

        Flux<Object> flux = sink.asFlux();

        sink.tryEmitNext("Hi");
        sink.tryEmitNext("how are you");

        flux.subscribe(Util.subscriber("Julio"));
        flux.subscribe(Util.subscriber("Cesar"));
        sink.tryEmitNext("?");
        flux.subscribe(Util.subscriber("Aaron"));

        sink.tryEmitNext("new msg");
    }


}
