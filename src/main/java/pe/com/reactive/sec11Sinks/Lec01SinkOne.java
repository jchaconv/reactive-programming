package pe.com.reactive.sec11Sinks;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

/*
* Sinks.One emite solo un valor
* emitValue, emitEmpty, emitError llaman internamente a:
* tryEmitValue, tryEmitEmpty, tryEmitError
*
*
* */

public class Lec01SinkOne {

    public static void main(String[] args) {

        //mono 1 value  / empty / error
        Sinks.One<Object> sink = Sinks.one(); //To Publish

        Mono<Object> mono = sink.asMono(); //To Subscribe

        mono.subscribe(Util.subscriber("Julio")); //Con esto no pasa nada

        sink.tryEmitValue("hola"); //Se tuvo que agregar esto

    }


}
