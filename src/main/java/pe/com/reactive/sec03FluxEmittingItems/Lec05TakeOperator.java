package pe.com.reactive.sec03FluxEmittingItems;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lec05TakeOperator {

    public static void main(String[] args) {

        //Al llegar a 3 ya no se emiten más eventos porque se ejecuta un cancel()
        Flux.range(1, 10)
                .log()
                .take(3)
                .log()
                .subscribe(Util.onNext());

    }
}
