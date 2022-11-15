package pe.com.reactive.sec04Operators;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

public class Lec04LimitRate {

    /*
    * Cuando llega al 75% de data consumida por el subscriber se ejecuta
    * automáticamente un request al Publisher(Ejemplo de Home de Facebook)
    * Se puede modificar el porcentaje de data que se quiere extraer en cada request
    * Si se pone 100% reactor pone 75% ya que es el porcentaje por defecto
    * */
    public static void main(String[] args) {

        Flux.range(1, 1000)
                .log()
                //.limitRate(100) //Solo pido 100 por eso hay request en el 75, 150, etc
                /*Con esto indico que quiero 100 pero que el request se ejecute al 99% de data
                * consumida; por eso hay request en la línea 99*/
                .limitRate(100, 99)
                //.limitRate(100, 0) // si se quiere pedir 100 y mandar request al 100% solo poner 0
                .subscribe(Util.subscriber());

    }


}
