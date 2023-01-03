package pe.com.reactive.sec11Sinks;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

//Usar unicast cuando se quiere tener solo un subscriber
public class Lec04SinkUnicast {

    public static void main(String[] args) {

        //handle through which we would push items
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();

        //handle through which subscribers will receive items
        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("Julio"));
        //Cesar :: ERROR :: UnicastProcessor allows only a single Subscriber
        //flux.subscribe(Util.subscriber("Cesar"));

        sink.tryEmitNext("Hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");


    }


}
