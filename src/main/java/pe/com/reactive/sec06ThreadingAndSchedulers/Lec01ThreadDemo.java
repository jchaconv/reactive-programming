package pe.com.reactive.sec06ThreadingAndSchedulers;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lec01ThreadDemo {

    /*De esta manera vemos la config por defecto, all se ejecuta
    por el thread "main"*/
    /*public static void main(String[] args) {

        Flux<Object> flux = Flux.create(fluxSink -> {
            printThreadName("create");
            fluxSink.next(1);
        }).doOnNext(i -> printThreadName("next " + i));

        flux.subscribe(v -> printThreadName("sub " + v));

    }*/

    //En este ejemplo podemos ver que se crean dos threads para los procesos
    public static void main(String[] args) {

        Flux<Object> flux = Flux.create(fluxSink -> {
            printThreadName("create");
            fluxSink.next(1);
        }).doOnNext(i -> printThreadName("next " + i));

        Runnable runnable = () -> flux.subscribe(v -> printThreadName("sub " + v));

        for (int i = 0; i < 2; i++) {
            new Thread(runnable).start();
        }

        Util.sleepSeconds(5);

    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
    }


}
