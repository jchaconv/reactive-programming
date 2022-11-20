package pe.com.reactive.sec04Operators;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06OnError {

    //Highly resilient pipeline
    public static void main(String[] args) {

        //onErrorReturn y onErrorResume detienen el flujo si ocurre un error
        //onErrorContinue permite seguir con la ejecución del pipeline

        Flux.range(1, 10)
                .log()
                .map(i -> 10 / (5 - i)) //Al dividir entre cero aparece ERROR :: / by zero
                //.onErrorReturn(-1) //retorna -1 en caso de error, ya no se muestra el mensaje por defecto
                //.onErrorResume(e -> fallback()) //Invoca al método en caso de error
                .onErrorContinue((err,obj) -> {})
                /*onErrorContinue : Es como hacer un skip, al llegar a 5 no se recibe nada y continúa
                Se puede agregar lógica dentro de {} como un log*/
                /*.onErrorContinue((err, obj) -> {
                    System.out.println(err);
                })*/
                .subscribe(Util.subscriber());

    }

    private static Mono<Integer> fallback() {
        return Mono.fromSupplier(()-> Util.faker().random().nextInt(100, 200));
    }

}
