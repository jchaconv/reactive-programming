package pe.com.reactive.sec10RepeatAndRetry;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

/*
* En este ejercicio se usa el repeat para repetir 2 veces(adicionales)
* la subscripción y al final se usa el map con atomicInteger para ver
* que aumenta el valor. Se puede probar sin el repeat y sin el map para
* ver resultados diferentes.
* */

public class Lec01Repeat {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {

        getIntegers()
                .repeat(2)
                .subscribe(Util.subscriber());

    }

    private static Flux<Integer> getIntegers() {
        return Flux.range(1, 3)
                .doOnSubscribe(s -> System.out.println("Subscribed"))
                .doOnComplete( () -> System.out.println("--Completed--"))
                .map(i -> atomicInteger.getAndIncrement());
    }


}
