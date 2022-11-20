package pe.com.reactive.sec04Operators.helper;

import lombok.Data;
import lombok.ToString;
import pe.com.reactive.util.Util;

@Data
@ToString
public class User {

    private int userId;
    private String name;

    public User(int userId) {
        this.userId = userId;
        this.name = Util.faker().name().fullName();
    }

}
