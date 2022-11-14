package pe.com.reactive.sec02Flux;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec10FluxFromMono {

    public static void main(String[] args) {

        Mono<String> mono = Mono.just("julio");
        Flux<String> flux = Flux.from(mono); //from() acepta un Publisher
        flux.subscribe(Util.onNext());

        System.out.println("==============");

        /* INICIO Lec11 :: [FLUX] To Mono */
        Flux.range(1, 10)
                .filter(i -> i > 8)
                .next()//Solo retorna un valor así convertimos a MONO
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
        /* FIN Lec11 :: [FLUX] To Mono */

    }
}
