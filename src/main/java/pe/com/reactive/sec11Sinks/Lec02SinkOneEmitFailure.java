package pe.com.reactive.sec11Sinks;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class Lec02SinkOneEmitFailure {

    public static void main(String[] args) {

        //mono 1 value  / empty / error
        Sinks.One<Object> sink = Sinks.one(); //To Publish

        Mono<Object> mono = sink.asMono(); //To Subscribe

        mono.subscribe(Util.subscriber("Julio")); //Con esto no pasa nada

        //sink.tryEmitError(new RuntimeException("err"));
        sink.emitValue("item1", (signalType, emitResult) -> {
            System.out.println(signalType.name());
            System.out.println(emitResult.name());
            return false;
        });

        //Con este pipeline forzamos el error porque Mono no emite más de un item
        sink.emitValue("item2", (signalType, emitResult) -> {
            System.out.println(signalType.name());
            System.out.println(emitResult.name());
            /*Si dejo return true el signal se queda abierto y
            se reintenta una y otra vez y siempre retornará error */
            return false;
        });


    }


}
