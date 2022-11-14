package pe.com.reactive.sec02Flux;

import reactor.core.publisher.Flux;

public class Lec02MultipleSubscribers {

    public static void main(String[] args) {

        Flux<Integer> integerFlux = Flux.just(1, 2, 3, 4);
        integerFlux.subscribe( i -> System.out.println("Subscriber1 :: " + i));

        Flux<Integer> evenFlux = integerFlux.filter(x -> x % 2 == 0);
        evenFlux.subscribe( i -> System.out.println("Subscriber2 :: " + i));


    }

}
