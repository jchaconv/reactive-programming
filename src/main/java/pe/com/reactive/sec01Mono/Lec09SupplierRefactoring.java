package pe.com.reactive.sec01Mono;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Lec09SupplierRefactoring {

    /* Pipeline Build vs Execution */

    public static void main(String[] args) {

        /*Lo que se ve en esta clase es mayormente para TEST*/

        /*Building the pipeline != executing the pipeline*/
        /*Hasta aquí vemos que solo se muestra el primer print*/
        getName();
        getName();

        getName()
                /*Inicio Leccion 10 :: Where is ASYNC? */
                //Con esta línea se evita el block. Se nota el ASYNC.
                .subscribeOn(Schedulers.boundedElastic())
                /*Fin Leccion 10 :: Where is ASYNC? */
                /*Con subscribe Si ejecutamos el pipeline y se hace el block.
                Lo cual es una mala práctica porque nadie quiere que se bloquee la ejecución*/
                .subscribe(Util.onNext());

        getName();

        /*Inicio Leccion 11 [MONO] :: Block */
        String name = getName().subscribeOn(Schedulers.boundedElastic())
                .block(); //Es como hacer un subscribe y un sleep
        System.out.println("This is the name with block :: " + name);
        /*Fin Leccion 11 [MONO] :: Block */

        /*Inicio Leccion 10 :: Where is ASYNC? */
        Util.sleepSeconds(4); //Para esperar el retorno del nombre
        /*Fin Leccion 10 :: Where is ASYNC? */

    }

    //Obtiene el nombre luego de esperar 3 secs y lo convierte a mayus
    private static Mono<String> getName() {
        System.out.println("Entered getName method");
        return Mono.fromSupplier( () -> {
            System.out.println("Generating name ...");
            Util.sleepSeconds(3);
            return Util.faker().artist().name();
        }).map(String::toUpperCase);
    }

}
