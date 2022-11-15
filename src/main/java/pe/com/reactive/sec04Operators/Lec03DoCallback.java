package pe.com.reactive.sec04Operators;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lec03DoCallback {

    public static void main(String[] args) {

        /* La lectura del pipeline es desde el subscribe(lo primero que se ejecuta) hacia arriba
        * Por eso la ejecución muestra: doFirst3, doFirst2 y doFirst1; en ese orden(Desde el subscriber al publisher)
        * Para doOnSubscribe1 y doOnSubscribe2 la ejecución es en ese orden porque se ejecutan desde el publisher al subscriber
        * En doOnRequest se pide all lo que se tiene y se muestra.
        * doOnNext ya es ejecutado dentro del complete
        * doOnComplete y doOnTerminate : Cuando el Publisher llega a onComplete
        * Cuando se prueba el error con doOnError solo se ejecuta doOnTerminate después
        * Al probar doOnDiscard se dispara solo doOnCancel y se muestran los items que quedaron fuera
        * El completed del subscribe
        * doFinally se posiciona al final de toda la ejecución
        * Ver las anotaciones de abajo en doFinally 1 y 2. Ese comportamiento se da porque al ejecutarse
        * el cancel el primer doFinally ya no sabe sobre el final del subscriber.
        * */

        Flux.create(fluxSink -> {
                    System.out.println(" ::: Inside Create ::: ");
                    for (int i = 0; i < 5; i++) {
                        fluxSink.next(i);
                    }
                    //fluxSink.complete();
                    fluxSink.error(new RuntimeException("OOPS")); //Para probar error
                    System.out.println(" --completed --");
                })
                .doOnComplete( () -> System.out.println("doOnComplete") )
                .doFirst( () -> System.out.println("doFirst1") )
                .doOnNext( o -> System.out.println("doOnNext: " + o) )
                .doOnSubscribe( s -> System.out.println("doOnSubscribe1: " + s) )
                .doOnRequest( l -> System.out.println("doOnRequest: " + l) )
                .doFirst( () -> System.out.println("doFirst2") )
                .doOnError( err -> System.out.println("doOnError: " + err.getMessage()) )
                .doOnTerminate( () -> System.out.println("doOnTerminate") )
                .doOnCancel( () -> System.out.println("doOnCancel") )
                .doOnSubscribe( s -> System.out.println("doOnSubscribe2: " + s) )
                .doFinally( signal -> System.out.println("doFinally1: " + signal) )
                .doFirst( () -> System.out.println("doFirst3") )
                .doOnDiscard( Object.class, o -> System.out.println("doOnDiscard: " + o) )
                .take(2) //Para probar doOnDiscard :: se ve que doFinally se ejecuta antes de Completed del subscriber
                .doFinally( signal -> System.out.println("doFinally2: " + signal) ) //Cuando se agrega se puede ver que aparece luego del completed
                .subscribe(Util.subscriber());


    }


}
