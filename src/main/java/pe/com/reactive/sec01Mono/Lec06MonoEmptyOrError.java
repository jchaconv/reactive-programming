package pe.com.reactive.sec01Mono;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Mono;

public class Lec06MonoEmptyOrError {

    public static void main(String[] args) {

        //Probar con 1, 2 y otro número para validar los mensajes
        userRepository(44)
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        Util.onComplete()
                );

    }

    private static Mono<String> userRepository(int userId) {

        if(userId == 1){
            return Mono.just(Util.faker().name().firstName());
        } else if (userId == 2) {
            return Mono.empty(); //para no tener que usar return null
        } else {
            return Mono.error(new RuntimeException("Not in an allowed range"));
        }

    }


}
