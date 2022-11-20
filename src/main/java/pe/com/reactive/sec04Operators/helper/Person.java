package pe.com.reactive.sec04Operators.helper;

import lombok.Data;
import lombok.ToString;
import pe.com.reactive.util.Util;

@Data
@ToString
public class Person {

    private String name;
    private int age;

    public Person() {
        this.name = Util.faker().name().firstName();
        this.age = Util.faker().random().nextInt(1, 89);
    }

}
