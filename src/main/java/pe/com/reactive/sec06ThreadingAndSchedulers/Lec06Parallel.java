package pe.com.reactive.sec06ThreadingAndSchedulers;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class Lec06Parallel {

    public static void main(String[] args) {

        /*
        * 1) Se generan varios hilos paralelos
        *
        Flux.range(1, 10)
                .parallel()
                .runOn(Schedulers.parallel())
                .doOnNext(i -> printThreadName("next " + i))
                .subscribe(v -> printThreadName("sub " + v));

        Util.sleepSeconds(5); */

        /*
         * 2) Se puede ver que no se almacenan todos los valores en la lista porque esta
         * es una práctica insegura.
         *
        List<Integer> list = new ArrayList<>(); //Arraylist is Not thread safe
        Flux.range(1, 1000)
                .parallel()
                .runOn(Schedulers.parallel())
                .subscribe(v -> list.add(v));

        Util.sleepSeconds(5);
        System.out.println(list.size()); //Llega cualquier valor menos 1K
        //No es seguro tomar un objeto e intentar guardar valores desde varios hilos
        //reactor no se responsabiliza por eso porque es una ejecución asíncrona
        */

        Flux.range(1, 10)
                .parallel(2)//le agrego el parallelism para que solo maneje 2 hilos
                .runOn(Schedulers.boundedElastic())
                .doOnNext(i -> printThreadName("next " + i))
                .subscribe(v -> printThreadName("sub " + v));

        Util.sleepSeconds(5);

    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
    }

}
