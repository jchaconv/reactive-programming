package pe.com.reactive.sec01;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscribe {

    public static void main(String[] args) {

        //publisher
        Mono<String> mono = Mono.just("ball"); //ctrl+alt+v

        //#1
        //mono.subscribe();

        //#2
        mono.subscribe(
                item -> System.out.println(item),
                err -> System.out.println(err.getMessage()),
                () -> System.out.println("Completed")
        );

        System.out.println("=======================");

        /* Inicio - Leccion03 [Mono] Subscribe :: onError */
        Mono<Integer> comicsPublisher = Mono.just("comics")
                .map(s -> s.length())
                .map(length -> length / 0); //Esto tiene que dar error

        //Puedo ver que solo muestra el error y no el item
        comicsPublisher.subscribe(
                item -> System.out.println(item),
                err -> System.out.println("ERROR: " + err.getMessage()),
                () -> System.out.println("Completed")
        );
        /* Fin - Leccion03 [Mono] Subscribe :: onError */

        System.out.println("=======================");

        /* Inicio - Leccion04 [Mono] :: Simple Refactor */
        mono.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
        /* Fin - Leccion04 [Mono] :: Simple Refactor */

    }
}
