package pe.com.reactive.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/*
* StepVerifier simula la subscripción y con expectNext
* validamos los items en el orden en que fueron emitidos.
* Estos dos tests prueban lo mismo.
* También se vio que en lugar de expectComplete() y verify() se
* puede usar verifyComplete() y ganamos un código más legible.
* */

public class Lec01SVDemoTest {

    @Test
    public void test1() {
        Flux<Integer> just = Flux.just(1, 2, 3);
        StepVerifier.create(just)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .verifyComplete();
                /*.expectComplete()
                .verify();*/
    }

    @Test
    public void test2() {
        Flux<Integer> just = Flux.just(1, 2, 3);
        StepVerifier.create(just)
                .expectNext(1, 2, 3)
                .verifyComplete();
    }


}
