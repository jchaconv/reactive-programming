package pe.com.reactive.sec04Operators;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec07Timeout {

    public static void main(String[] args) {

        getOrderNumbers()
                //.timeout(Duration.ofSeconds(2)) //Hace que retorne un ERROR y nada más
                .timeout(Duration.ofSeconds(2), fallback())
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);

    }

    public static Flux<Integer> getOrderNumbers() {
        return Flux.range(1, 10)
                //Este valor hace que el timeout de 2 de arriba se dispare
                //Si quiero que funcione le pongo 1
                .delayElements(Duration.ofSeconds(5));
    }

    public static Flux<Integer> fallback() {
        return Flux.range(100, 10)
                .delayElements(Duration.ofMillis(200));
    }

}
