package pe.com.reactive.sec03FluxEmittingItems;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lec08FluxGenerateExercise {

    //El ejemplo de emitir hasta que el item sea Canada pero con Flux.generate
    public static void main(String[] args) {

        Flux.generate(synchronousSink -> {
                    String country = Util.faker().country().name();
                    System.out.println("emitting : " + country);
                    synchronousSink.next(country);
                    if(country.equalsIgnoreCase("canada"))
                        synchronousSink.complete();
                })
                .subscribe(Util.subscriber());

    }


}
