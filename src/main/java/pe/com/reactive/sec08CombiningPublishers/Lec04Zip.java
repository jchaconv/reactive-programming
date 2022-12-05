package pe.com.reactive.sec08CombiningPublishers;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lec04Zip {

    /*
    * Este ejemplo simula la generación de un carro
    * Cada publisher emite una parte del carro y el subscriber
    * recibe el carro completo mediante el uso de "zip"
    * Cabe mencionar que si no hay items suficientes en una de las partes
    * para completar el carro solo se emitirán los que correspondan.
    * Por ejemplo, si solo tengo 2 motores, solo podré generar 2 carros.
    * */

    public static void main(String[] args) {

        Flux.zip(getBody(), getEngine(), getTires())
                .subscribe(Util.subscriber());

    }

    private static Flux<String> getBody() {
        return Flux.range(1, 5)
                .map(i -> "body");
    }

    private static Flux<String> getEngine() {
        return Flux.range(1, 2)
                .map(i -> "engine");
    }

    private static Flux<String> getTires() {
        return Flux.range(1, 6)
                .map(i -> "tires");
    }


}
