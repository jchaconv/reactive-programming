package pe.com.reactive.sec10RepeatAndRetry;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/*
* RetryWhen recibe un "Retry" y en este ejemplo el fixedDelay
* es para reintentar 2 veces máximo luego de 3 segundos.*
* */

public class Lec04RetryWhen {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {

        getIntegers()
                .retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(3)))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);

    }

    private static Flux<Integer> getIntegers() {
        return Flux.range(1, 3)
                .doOnSubscribe(s -> System.out.println("Subscribed"))
                .doOnComplete( () -> System.out.println("--Completed--"))
                .map(i -> i / (Util.faker().random().nextInt(1, 5) > 3 ? 0 : 1))
                .doOnError(err -> System.out.println("--error--"));
    }


}
