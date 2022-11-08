package pe.com.reactive.sec01Mono;

import reactor.core.publisher.Mono;

public class Lec02MonoJust {

    public static void main(String[] args) {

        //publisher
        Mono<Integer> mono = Mono.just(1);

        //Nada pasa hasta que se haga una subscripción
        System.out.println(mono);

        mono.subscribe(i -> System.out.println("Received :: " + i));

    }


}
