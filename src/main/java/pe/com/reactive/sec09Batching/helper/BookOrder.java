package pe.com.reactive.sec09Batching.helper;

import com.github.javafaker.Book;
import lombok.Getter;
import lombok.ToString;
import pe.com.reactive.util.Util;

@Getter
@ToString
public class BookOrder {

    private String title;
    private String author;
    private String category;
    private double price;

    public BookOrder() {
        Book book = Util.faker().book();
        this.title = book.title();
        this.author = book.author();
        this.category = book.genre();
        this.price = Double.parseDouble(Util.faker().commerce().price());
    }

}
