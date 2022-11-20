package pe.com.reactive.sec05HotColdpublishers;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec03HotPublish {

    //Si dejo el delay en 1, refcount en 1 y sleep en 10
    //se convierte en cold porque esperará a terminar con el primer subscriber
    //y luego el 2do leerá toda la data desde el comienzo

    public static void main(String[] args) {

        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(2))
                //.delayElements(Duration.ofSeconds(1))
                .publish()
                //.refCount(1); //El 2do publisher empieza a leer tarde
                .refCount(2); //Inicia con 2 publisher y ambos leen desde el comienzo

        movieStream
                .subscribe(Util.subscriber("Julio"));

        Util.sleepSeconds(5);
        //Util.sleepSeconds(10);

        movieStream
                .subscribe(Util.subscriber("César"));

        Util.sleepSeconds(60);

    }

    private static Stream<String> getMovie() {
        System.out.println("Got the movie streaming req");
        return Stream.of(
                "Scene 1",
                "Scene 2",
                "Scene 3",
                "Scene 4",
                "Scene 5",
                "Scene 6",
                "Scene 7"
        );
    }


}
