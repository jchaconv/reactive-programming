package pe.com.reactive.sec07Backpressure;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec05Error {

    /*
    * onBackpressureError() sirve para emitir un mensaje en caso de error
    * Se agrego una validación en el for para que si fluxSink es cancelado
    * se termine el flujo.
    *
    * Al recibir el item 16 muestra el mensaje:
    * ERROR :: The receiver is overrun by more signals than expected (bounded queue...)
    * porque la queue es muy lenta para procesar todos los items y termina la ejecución
    * del pipeline. Interesante el poner una condicional en el for.
    *
    * */

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");

        Flux.create(fluxSink -> {
                    for (int i = 1; i < 201 && !fluxSink.isCancelled(); i++) {
                        fluxSink.next(i);
                        System.out.println("Pushed :: " + i);
                        Util.sleepMillis(1);
                    }
                    fluxSink.complete();
                })
                .onBackpressureError()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    Util.sleepMillis(10);
                })
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);

    }


}
