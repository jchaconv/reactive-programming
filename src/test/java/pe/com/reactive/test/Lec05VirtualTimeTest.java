package pe.com.reactive.test;

import org.junit.jupiter.api.Test;
import pe.com.reactive.sec09Batching.helper.BookOrder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec05VirtualTimeTest {

    @Test
    public void test1() {
        Mono<BookOrder> mono = Mono.fromSupplier(() -> new BookOrder());

        StepVerifier.withVirtualTime( () -> timeConsumingFlux())
                .thenAwait(Duration.ofSeconds(30))
                .expectNext("1a", "2a", "3a", "4a")
                .verifyComplete();
    }

    @Test
    public void test2() {
        Mono<BookOrder> mono = Mono.fromSupplier(() -> new BookOrder());

        StepVerifier.withVirtualTime( () -> timeConsumingFlux())
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(4))
                .thenAwait(Duration.ofSeconds(20))
                .expectNext("1a", "2a", "3a", "4a")
                .verifyComplete();
    }

    private Flux<String> timeConsumingFlux() {
        return Flux.range(1, 4)
                .delayElements(Duration.ofSeconds(5))
                .map(i -> i + "a");
    }


}
