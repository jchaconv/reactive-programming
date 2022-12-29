package pe.com.reactive.sec10RepeatAndRetry;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

/*
* Se le quita el valor al repeat(dejar vacío) y así nunca llegará el
* complete signal al subscriber, hacer esa prueba(bucle infinito).
* Luego se agrega la condicional para que repita la subscripción hasta
* que el item sea menor que 14. Lo particular de este ejercicio es que llega
* hasta el item 15. Digamos que se va a emitir ese item porque está dentro de
* la subscripción del 14.
* */

public class Lec02RepeatWithCondition {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {

        getIntegers()
                .repeat( () -> atomicInteger.get() < 14)
                .subscribe(Util.subscriber());

    }

    private static Flux<Integer> getIntegers() {
        return Flux.range(1, 3)
                .doOnSubscribe(s -> System.out.println("Subscribed"))
                .doOnComplete( () -> System.out.println("--Completed--"))
                .map(i -> atomicInteger.getAndIncrement());
    }

}
