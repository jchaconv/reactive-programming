package pe.com.reactive.sec11Sinks;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec06SinkMulti {

    public static void main(String[] args) {

        Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();

        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("Julio"));
        flux.subscribe(Util.subscriber("Cesar"));

        sink.tryEmitNext("Hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");


    }


}
