package pe.com.reactive.sec01Mono;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Lec12MonoFromFuture {

    //Otra manera de crear MONO
    public static void main(String[] args) {
        Mono.fromFuture(getName())
                .subscribe(Util.onNext());
        Util.sleepSeconds(1);
    }
    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync( () -> Util.faker().name().fullName());
    }

}
