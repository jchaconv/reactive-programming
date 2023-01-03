package pe.com.reactive.sec11Sinks;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

/*
* En este ejercicio podemos ver que se pueden tener muchos subscribers
* y solo un publisher pero igual reciben los items
* */

public class Lec03SinkOneMultipleSubscribers {

    public static void main(String[] args) {

        //mono 1 value  / empty / error
        Sinks.One<Object> sink = Sinks.one(); //To Publish

        Mono<Object> mono = sink.asMono(); //To Subscribe

        mono.subscribe(Util.subscriber("Julio"));
        mono.subscribe(Util.subscriber("Cesar"));

        sink.tryEmitValue("hola");


    }


}
