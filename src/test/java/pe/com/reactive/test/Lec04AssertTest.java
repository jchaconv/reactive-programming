package pe.com.reactive.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pe.com.reactive.sec09Batching.helper.BookOrder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

/*
* test1: Valida que no sea nulo
* test2: Valida que se complete el pipeline en el tiempo establecido
* Se completa en 2 secs por eso pasa el test
* */

public class Lec04AssertTest {

    @Test
    public void test1() {
        Mono<BookOrder> mono = Mono.fromSupplier(() -> new BookOrder());

        StepVerifier.create(mono)
                .assertNext(b -> Assertions.assertNotNull(b.getAuthor()))
                .verifyComplete();
    }

    @Test
    public void test2() {
        Mono<BookOrder> mono = Mono.fromSupplier(() -> new BookOrder())
                .delayElement(Duration.ofSeconds(2));

        StepVerifier.create(mono)
                .assertNext(b -> Assertions.assertNotNull(b.getAuthor()))
                .expectComplete()
                .verify(Duration.ofSeconds(4));
    }


}
