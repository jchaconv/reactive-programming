package pe.com.reactive.sec03FluxEmittingItems;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lec09FluxGenerateCounter {

    //Agregar un contador al generate debido a que no se puede de la manera tradicional
    public static void main(String[] args) {

        Flux.generate(
                        () -> 1, //paso un state inicial
                        (counter, sink) -> { //puse counter pero puede ser "state"
                            String country = Util.faker().country().name();
                            sink.next(country);
                            if (counter >= 10 || country.equalsIgnoreCase("canada"))
                                sink.complete();
                            return counter + 1; //retorno un nuevo state
                        }
                )
                //.take(5)
                .subscribe(Util.subscriber());


    }


}
