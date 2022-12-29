package pe.com.reactive.sec10RepeatAndRetry;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

public class Lec05RetryWhenAdvanced {

    public static void main(String[] args) {

        orderService(Util.faker().business().creditCardNumber())
                .retryWhen(Retry.from(
                        flux -> flux.doOnNext(rs -> {
                            System.out.println(rs.totalRetries());
                            System.out.println(rs.failure());
                        }).handle((retrySignal, synchronousSink) -> {
                            if(retrySignal.failure().getMessage().equals("500")) {
                                synchronousSink.next(1); //Se usa como signal para el retry
                            } else {
                                synchronousSink.error(retrySignal.failure());
                            }
                        }).delayElements(Duration.ofSeconds(1))
                ))
                /*.doOnError(err -> System.out.println(err.getMessage()))
                .retry(5)*/
                .subscribe(Util.subscriber());

                Util.sleepSeconds(60);
    }

    //order service
    private static Mono<String> orderService(String ccNumber){
        return Mono.fromSupplier( () -> {
            processPayment(ccNumber);
            return Util.faker().idNumber().valid();
        });
    }

    //payment service
    private static void processPayment(String ccNumber) {
        int random = Util.faker().random().nextInt(1, 10);
        if(random < 8) {
            throw new RuntimeException("500");
        } else if (random < 10) {
            throw new RuntimeException("404");
        }
    }


}
