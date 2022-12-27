package pe.com.reactive.sec09Batching;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec01Buffer {

    //Probar con y sin el buffer

    public static void main(String[] args) {

        eventStream()
                .buffer(5)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(300))
                /* Inicio Lec02 - When complete signal is emmitted */
                //El buffer solo agrupará 3 elementos y terminará el proceso
                //.take(3)
                /* Fin Lec02 - When complete signal is emmitted */
                .map(i -> "event: " + i);
    }


}
