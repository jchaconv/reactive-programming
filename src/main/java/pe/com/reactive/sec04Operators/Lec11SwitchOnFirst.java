package pe.com.reactive.sec04Operators;

import pe.com.reactive.sec04Operators.helper.Person;
import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class Lec11SwitchOnFirst {

    public static void main(String[] args) {

        //switchOnFirst : Acepta BiFunction y Evalúa el primer elemento
        /*
        * En este ejercicio se toma el signal(primer elemento) y se evalúa que tenga algún valor con
        * isOnNext y que la edad sea mayor a 20; Si se cumple esta condición se permite el personFlux y se muestran
        * los valores recibidos con el Util.subscriber. En caso contrario va al método que hace conversiones.
        * Ejecutar varias veces hasta que el primer valor cumpla las condiciones.
        * */

        getPerson()
                .switchOnFirst(((signal, personFlux) -> {
                    System.out.println("inside switch-on-first");
                    return signal.isOnNext() && signal.get().getAge() > 20
                            ? personFlux
                            : applyFilterMap().apply(personFlux);
                }))
                .subscribe(Util.subscriber());
    }

    //Genero 10 personas
    public static Flux<Person> getPerson() {
        return Flux.range(1, 10)
                .map(i -> new Person());
    }

    //Obtiene los mayores de 20 años, convierte el nombre a MAYUS y muestra los que quedaron fuera
    //Si no aparecen los descartados ejecutar nuevamente hasta que aparezca uno o más casos
    public static Function<Flux<Person>, Flux<Person>> applyFilterMap() {
        return personFlux -> personFlux
                .filter(person -> person.getAge() > 20)
                .doOnNext(person -> person.setName(person.getName().toUpperCase()))
                .doOnDiscard(Person.class, person -> System.out.println("Not allowed :: " + person));
    }


}
