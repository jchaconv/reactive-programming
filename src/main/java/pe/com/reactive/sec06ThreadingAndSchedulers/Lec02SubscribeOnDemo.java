package pe.com.reactive.sec06ThreadingAndSchedulers;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec02SubscribeOnDemo {

    /* Primer ejemplo:
    En este ejemplo se puede ver que first1 es ejecutado por el thread main
    y luego el pipeline ejecuta lo demás mediante el subscribeOn en otro thread

    Segundo ejemplo:
    Se realiza con runnable para ver la creación de otro thread boundedElastic
    */

    public static void main(String[] args) {

        Flux<Object> flux = Flux.create(fluxSink -> {
            printThreadName("create");
            fluxSink.next(1);
        }) //Vemos que el subscribeOn más cerca al publisher(Ln23) toma precedencia
        .subscribeOn(Schedulers.newParallel("jchaconv")) //Se agrega para ver cuál toma (Ln23 o Ln34)
        .doOnNext(i -> printThreadName("next " + i));

        /* Primer ejemplo:
        flux.doFirst( () -> printThreadName("first2") )
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst( () -> printThreadName("first1") )
                .subscribe(v -> printThreadName("sub " + v)); */

        //2do ejemplo
        Runnable runnable = () -> flux.doFirst( () -> printThreadName("first2") )
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst( () -> printThreadName("first1") )
                .subscribe(v -> printThreadName("sub " + v));

        for (int i = 0; i < 2; i++) {
            new Thread(runnable).start();
        }

        //Se agrega para ver rpta completa del 2do ejemplo
        Util.sleepSeconds(5);

    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
    }

}
