package pe.com.reactive.sec07Backpressure;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec04Latest {

    /*
    * En este ejercicio se puede ver que al usar onBackpressureLatest() los items son emitidos
    * uno tras otro y también es consumido "paralelamente" hasta llegar al item 16 y luego "salta"
    * al 110
    *
    *
    *
    *
    * */

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");

        Flux.create(fluxSink -> {
                    for (int i = 1; i < 201; i++) {
                        fluxSink.next(i);
                        System.out.println("Pushed :: " + i);
                        Util.sleepMillis(1);
                    }
                    fluxSink.complete();
                })
                .onBackpressureLatest() //ver la diferencia con onBackpressureDrop()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    Util.sleepMillis(10);
                })
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);

    }


}
