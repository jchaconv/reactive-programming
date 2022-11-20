package pe.com.reactive.sec05HotColdpublishers;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

//El 2do publisher obtiene toda la info automáticamente
//Internamete: cache() = publish().replay()
//Por defecto puede recordar int.max pero se puede modificar

public class Lec05HotPublishCache {

    public static void main(String[] args) {

        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(1))
                //.cache(); int.max
                .cache(2);

        Util.sleepSeconds(2);

        movieStream
                .subscribe(Util.subscriber("Julio"));

        Util.sleepSeconds(10);

        System.out.println("Cesar is about to join");

        movieStream
                .subscribe(Util.subscriber("César"));

        //Util.sleepSeconds(60);

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
