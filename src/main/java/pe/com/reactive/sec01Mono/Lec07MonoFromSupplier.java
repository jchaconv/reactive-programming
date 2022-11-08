package pe.com.reactive.sec01Mono;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class Lec07MonoFromSupplier {

    public static void main(String[] args) {

        //Usar just solo cuando se tiene la data lista
        //porque esta línea por si sola solo llega hasta el mensaje
        //y no muestra el nombre

        Mono<String> mono = Mono.just(getName());

        //por eso usamos fromSupplier
        Mono<String> stringMono = Mono.fromSupplier(() -> getName());
        mono.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );

        System.out.println("========================");

        /* Inicio - Leccion08 :: [Mono] From Callable */

        //Aquí se ve que el uso de Supplier y Callable es idéntico
        Supplier<String> stringSupplier = () -> getName();
        Mono<String> monoFromSupplier = Mono.fromSupplier(stringSupplier);
        monoFromSupplier.subscribe(
                Util.onNext()
        );

        System.out.println(" :: From Callable :: ");

        Callable<String> stringCallable = () -> getName();
        Mono.fromCallable(stringCallable)
                .subscribe(
                        Util.onNext()
                );

        /* Fin - Leccion08 :: [Mono] From Callable */

    }
    private static String getName() {
        System.out.println("Generating name ...");
        return Util.faker().artist().name();
    }

}
