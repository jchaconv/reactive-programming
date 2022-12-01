package pe.com.reactive.sec07Backpressure;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

public class Lec06BufferWithSize {

    /*
    * onBackpressureBuffer() sirve para cambiar el tamaño de la queue
    * y hacerla más flexible. Hace mayor la capacidad de almacenar en
    * memoria y retorna el error cuando es superada.
    *
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
                //}, FluxSink.OverflowStrategy.BUFFER) También se puede añadir así como un parámetro adicional
                //.onBackpressureBuffer(50) //Probar con varios valores
                .onBackpressureBuffer(50, o -> {
                    System.out.println("Dropped :: " + o);
                }) //Añadiendo el consumer se puede acceder al valor que ha sido dropeado
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    Util.sleepMillis(10);
                })
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);

    }



}
