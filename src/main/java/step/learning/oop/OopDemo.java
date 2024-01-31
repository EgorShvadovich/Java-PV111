package step.learning.oop;

import java.util.ArrayList;
import java.util.List;

public class OopDemo {
    public void run(){
        System.out.println("OOP");
        List<Literature> catalog = new ArrayList<>();

        try {
            catalog.add(new Book("D. Knuth", "Art of Programming"));
            catalog.add(new Newspaper("Daily Mail", "2024-01-29"));
            catalog.add(new Journal("R&D World", "123"));
            catalog.add(new Map("Odessa", "1:50000"));
        }
        catch (Exception ex) {
            System.err.println("Literature add error: " + ex.getMessage() ) ;
            return ;
        }
        for(Literature literature : catalog){
            System.out.println(literature.getCard());
        }
    }
}
