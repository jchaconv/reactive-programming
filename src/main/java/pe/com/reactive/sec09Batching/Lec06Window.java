package pe.com.reactive.sec09Batching;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec06Window {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {

        //window genera Flux de Flux por eso retorna UnicastProcessor
        //se hacen conversiones para obtener los items, me parece poco práctico
        eventStream()
                /* Inicio Lec07 - Batching with window - Duration */
                //Abrir el flux por 2 secs y cerrar
                .window(Duration.ofSeconds(2))
                //.window(5)
                /* Fin Lec07 - Batching with window - Duration */
                .flatMap(flux -> saveEvents(flux))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> "event: " + i);
    }

    private static Mono<Integer> saveEvents(Flux<String> flux) {
        return flux.doOnNext(event -> System.out.println("saving " + event))
                .doOnComplete(() -> {
                    System.out.println("saved this batch");
                    System.out.println("-----------------");
                })
                .then(Mono.just(atomicInteger.getAndIncrement())); //Si el pipeline se completó envía un signal
    }

}
