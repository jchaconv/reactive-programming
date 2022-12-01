package pe.com.reactive.sec07Backpressure;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec01Demo {

    //Lo que se muestra en este ejercicio es el comportamiento por defecto que puede tener
    //un pipeline. Los items se emiten muy rápido pero el consume de los mismos demora más.
    public static void main(String[] args) {

        Flux.create( fluxSink -> {
            for (int i = 1; i < 501; i++) {
                fluxSink.next(i);
                System.out.println("Pushed :: " + i);
            }
            fluxSink.complete();
        }).publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    Util.sleepMillis(10);
                })
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);

    }

}
