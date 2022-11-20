package pe.com.reactive.sec04Operators;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lec09SwitchIfEmpty {

    public static void main(String[] args) {

        getOrderNumbers()
                .filter(i -> i > 10)
                //Acepta un publisher
                .switchIfEmpty(fallback())
                .subscribe(Util.subscriber());

    }

    //ejemplo de redis : hay caché?
    public static Flux<Integer> getOrderNumbers() {
        return Flux.range(1, 10);
    }

    //no hay : voy a la bd
    public static Flux<Integer> fallback() {
        return Flux.range(20, 5);
    }

}
