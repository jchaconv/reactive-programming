package pe.com.reactive.sec02Flux;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxIntro {

    public static void main(String[] args) {

        //Usar just cuando la data está lista. Usar empty para emitir 0 items.
        Flux<Object> flux = Flux.just(95, 27, 6, 30, "Ruti", "te amo mucho", Util.faker().name().fullName());
        flux.subscribe(Util.onNext(),
                Util.onError(),
                Util.onComplete());



    }


}
