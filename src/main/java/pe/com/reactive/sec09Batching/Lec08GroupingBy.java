package pe.com.reactive.sec09Batching;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec08GroupingBy {

    public static void main(String[] args) {

        Flux.range(1, 30)
                .delayElements(Duration.ofSeconds(1))
                .groupBy(i -> i % 2) //key 0, 1(porque retorna V o F, 0 o 1)
                .subscribe(groupedFlux -> process(groupedFlux, groupedFlux.key()));

        Util.sleepSeconds(60);

    }

    private static void process(Flux<Integer> flux, int key) {
        System.out.println("Called");
        flux.subscribe(i -> System.out.println("Key :: " + key + ", Item :: " + i));
    }


}
