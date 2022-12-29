package pe.com.reactive.sec10RepeatAndRetry;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

/*
* Retry sirve para reintentar la subscripción en caso de error.
* Se hace un comportamiento aleatorio en el map pero también se puede
* forzar con la división entre cero.
* */

public class Lec03Retry {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {

        getIntegers()
                .retry(2)
                .subscribe(Util.subscriber());

    }

    private static Flux<Integer> getIntegers() {
        return Flux.range(1, 3)
                .doOnSubscribe(s -> System.out.println("Subscribed"))
                .doOnComplete( () -> System.out.println("--Completed--"))
                //.map(i -> i / 0) Se agregó para forzar error
                .map(i -> i / (Util.faker().random().nextInt(1, 5) > 3 ? 0 : 1))
                .doOnError(err -> System.out.println("--error--"));
    }


}
