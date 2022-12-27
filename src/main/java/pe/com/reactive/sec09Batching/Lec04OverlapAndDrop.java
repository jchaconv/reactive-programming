package pe.com.reactive.sec09Batching;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04OverlapAndDrop {

    public static void main(String[] args) {

        //Para usar algún tipo de pattern
        //.buffer(3) es como usar .buffer(maxSize: 3, skip: 3)
        //Este ejemplo quiere decir, cada 3 items dejar 1
        eventStream()
                .buffer(3, 1)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(300))
                .map(i -> "event: " + i);
    }

}
