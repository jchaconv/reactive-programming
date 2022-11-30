package pe.com.reactive.sec06ThreadingAndSchedulers;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
* boundedElastic y parallel tienen el mismo comportamiento en este ejemplo
* todos los items son manejados por un solo thread. Schedulers != parallel execution
* Schedulers maneja un esquema más parecido a 1 producer por cada subscriber
*
* */

public class Lec03SubscribeOnMultipleItems {

    public static void main(String[] args) {

        Flux<Object> flux = Flux.create(fluxSink -> {
                    printThreadName("create");
                    for (int i = 0; i < 4; i++) {
                        fluxSink.next(i);
                        Util.sleepSeconds(3); //Se agregó para la prueba final
                    }
                    fluxSink.complete();
                })
                .doOnNext(i -> printThreadName("next " + i));

        //Primera prueba
        /*Runnable runnable = () -> flux.subscribeOn(Schedulers.boundedElastic())
                .subscribe(v -> printThreadName("sub " + v));

        for (int i = 0; i < 5; i++) { //5 threads
            new Thread(runnable).start();
        }*/

        //Segunda prueba
        flux.subscribeOn(Schedulers.boundedElastic())
                .subscribe(v -> printThreadName("sub " + v));
        //Ninguno toma precedencia - son independientes
        flux.subscribeOn(Schedulers.parallel())
                .subscribe(v -> printThreadName("sub " + v));

        Util.sleepSeconds(5);

    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
    }


}
