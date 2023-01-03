package pe.com.reactive.sec11Sinks;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Lec05SinkThreadSafety {

    public static void main(String[] args) {

        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Object> flux = sink.asFlux();
        List<Object> list = new ArrayList<>();

        flux.subscribe(list::add);

        for (int i = 0; i < 1000; i++) {
            final int j = i;
            CompletableFuture.runAsync( () -> {
                sink.emitNext(j, ((signalType, emitResult) -> true) );
            });
        }

        Util.sleepSeconds(3);
        System.out.println(list.size());
    }


}
