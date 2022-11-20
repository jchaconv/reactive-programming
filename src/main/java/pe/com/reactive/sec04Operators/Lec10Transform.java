package pe.com.reactive.sec04Operators;

import pe.com.reactive.sec04Operators.helper.Person;
import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class Lec10Transform {

    public static void main(String[] args) {

        getPerson()
                .transform(applyFilterMap())
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
