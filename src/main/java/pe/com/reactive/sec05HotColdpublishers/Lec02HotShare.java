package pe.com.reactive.sec05HotColdpublishers;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

/*
* Parecido a una peli de cine, el que llega tarde se pierde el contenido.
* El publisher Emite contenido para varios subscribers
* Share convierte un cold publisher en hot publisher
* En este ejemplo se hace un multicasting para que ambos subscribers compartan la data
* Internamente funciona así: share() = publish().refCount(1) :: Ver sgte ejercicio
* */

public class Lec02HotShare {

    public static void main(String[] args) {

        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(2))
                .share();

        movieStream
                .subscribe(Util.subscriber("Julio"));

        Util.sleepSeconds(5);

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
