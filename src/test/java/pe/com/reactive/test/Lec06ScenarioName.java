package pe.com.reactive.test;

import org.junit.jupiter.api.Test;
import pe.com.reactive.sec09Batching.helper.BookOrder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

/*
* StepVerifierOptions:
* - scenarioName: Para mostrar info del test(como un título)
*
* as: Para poner nombres a cada expectNext()
* */
public class Lec06ScenarioName {

    @Test
    public void test1() {
        Flux<String> flux = Flux.just("a", "b", "c");

        StepVerifierOptions scenarioName = StepVerifierOptions.create().scenarioName("alphabets-test");

        StepVerifier.create(flux, scenarioName)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    public void test2() {
        Flux<String> flux = Flux.just("a", "b1", "c");

        StepVerifier.create(flux)
                .expectNext("a")
                .as("a-test")
                .expectNext("b")
                .as("b-test")
                .expectNext("c")
                .as("c-test")
                .verifyComplete();
    }


}
