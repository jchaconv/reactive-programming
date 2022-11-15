package pe.com.reactive.sec04Operators;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lec02HandleAssignment {

    public static void main(String[] args) {

        /* Dejar de emitir items cuando el país sea Canada */

        Flux.generate(synchronousSink -> synchronousSink.next(Util.faker().country().name()))
                .map(Object::toString)
                .handle( (country, synchronousSink) -> {
                    synchronousSink.next(country);
                    if (country.equalsIgnoreCase("canada"))
                        synchronousSink.complete();
                })
                .subscribe(Util.subscriber());

    }
}
