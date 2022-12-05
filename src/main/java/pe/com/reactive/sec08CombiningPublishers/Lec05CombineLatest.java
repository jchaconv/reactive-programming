package pe.com.reactive.sec08CombiningPublishers;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05CombineLatest {

    /*
    * Lo que hace combineLatest es tomar el último elemento de cada publisher
    * para emitir un nuevo resultado dependiendo de que en ambos pubs se hayan
    * emitido elementos previamente. Por ejemplo en este ejercicio no aparece A1 porque
    * A ya había sido emitido pero 1 todavía no por el delay que tiene
    *
    * */

    public static void main(String[] args) {
        //s: string, n: number
        Flux.combineLatest(getString(), getNumber(), (s, n) -> s + n)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }

    private static Flux<String> getString() {
        return Flux.just("A", "B", "C", "D")
                .delayElements(Duration.ofSeconds(1));
    }

    private static Flux<Integer> getNumber() {
        return Flux.just(1, 2, 3)
                .delayElements(Duration.ofSeconds(3));
    }

}
