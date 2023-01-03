package pe.com.reactive.sec12Context;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/*
* Simulamos el ingreso de usuario
* Se puede crear más de un contexto
* Se puede actualizar el contexto en el orden mostrado(downstream)
* Si se intenta el ctx.put en upstream sale error: "Context does not contain key"
* */
public class Lec01Context {

    public static void main(String[] args) {

        getWelcomeMessage()
                .contextWrite(Context.of("users", "Cesar"))
                .contextWrite(ctx -> ctx.put("user", ctx.get("user").toString().toUpperCase()))
                .contextWrite(Context.of("user", "Julio"))
                .subscribe(Util.subscriber());

    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
            if(ctx.hasKey("user")) {
                return Mono.just("Welcome " + ctx.get("users"));
            } else {
                return Mono.error(new RuntimeException("Unauthenticated"));
            }
        });
    }


}
