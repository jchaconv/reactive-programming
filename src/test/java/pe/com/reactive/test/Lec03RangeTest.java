package pe.com.reactive.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/*
* test1: Como no sabemos al detalle los valores para evaluarlos podemos
* usar expectNextCount para contar el total
*
* test2: Se puede agregar alguna validación con thenConsumeWhile
* Si algún valor no la cumple el test es fallido
* */

public class Lec03RangeTest {

    @Test
    public void test1() {
        Flux<Integer> range = Flux.range(1, 50);
        StepVerifier.create(range)
                .expectNextCount(50)
                .verifyComplete();
    }

    @Test
    public void test2() {
        Flux<Integer> range = Flux.range(1, 50);
        StepVerifier.create(range)
                .thenConsumeWhile(i -> i < 100)
                .verifyComplete();
    }

}
