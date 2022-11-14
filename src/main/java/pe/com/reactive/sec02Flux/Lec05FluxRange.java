package pe.com.reactive.sec02Flux;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxRange {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .subscribe(Util.onNext());

        System.out.println("=================");

        //Si quiero mostrar nombres puedo hacerlo reemplazando el indice
        Flux.range(1, 10)
                /*Inicio Lec06 :: [FLUX] Log */
                .log()
                /*Fin Lec06 :: [FLUX] Log */
                .map(i -> Util.faker().artist().name())
                .subscribe(Util.onNext());

    }
}
