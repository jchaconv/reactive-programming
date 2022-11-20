package pe.com.reactive.sec04Operators;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lec08DefaultIfEmpty {

    public static void main(String[] args) {

        getOrderNumbers()
                .filter(i -> i > 10) //NO obtengo nada porque los números llegan hasta 10
                //Acepta una constante
                .defaultIfEmpty(-100) //Envío -100 por defecto si no recibo items
                .subscribe(Util.subscriber());

    }

    public static Flux<Integer> getOrderNumbers() {
        return Flux.range(1, 10);
    }

}
