package step.learning.oop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class OopDemo {
    private List<Literature> catalog = new ArrayList<>();
    public void run(){
        System.out.println("OOP");


        try {
            catalog.add(new Book("D. Knuth", "Art of Programming"));
            catalog.add(new OldBook("M. Twen", "Tom Soyer"));
            catalog.add(new Newspaper("Daily Mail", "2024-01-29"));
            catalog.add(new OldNewspaper("Old Daily Mail", "2024-01-29"));
            catalog.add(new Journal("R&D World", "123"));
            //catalog.add(new Map("Odessa", "1:50000"));
        }
        catch (Exception ex) {
            System.err.println("Literature add error: " + ex.getMessage() ) ;
            return ;
        }
        for(Literature literature : catalog){
            System.out.println(literature.getCard());
        }
        printCopyable();
        printNonCopyable();
        printPeriodic();
        printOld();

    }

    private void printCopyable() {
        System.out.println("COPYABLE");
        for(Literature literature : catalog){
            if(literature instanceof Copyable){
                System.out.println(literature.getCard());
            }
        }
    }
    private void printNonCopyable() {
        System.out.println("NON COPYABLE");
        for(Literature literature : catalog){
            if(!(literature instanceof Copyable)){
                System.out.println(literature.getCard());
            }
        }
    }

    private void printPeriodic(){
        System.out.println("PERIODIC");
        for(Literature literature : catalog){
            if(literature instanceof Periodic){
                System.out.println(((Periodic) literature).getPeriod() + ": " + literature.getCard());
            }
        }
    }

    private void printOld(){
        System.out.println("OLD");
        for(Literature literature : catalog) {
            Class<?> type = literature.getClass();   //-GetType(c#)
            if(type.isAnnotationPresent(Used.class)){
                //System.out.println(literature.getCard());
                for( Method method :  type.getDeclaredMethods()){
                    if(method.isAnnotationPresent(CardMethod.class)){

                        try{
                            method.setAccessible(true);
                            System.out.println( method.invoke(literature));
                        }catch(IllegalAccessException | InvocationTargetException ex){
                            System.out.println(ex.getMessage());
                        }
                    }
                }
            }
        }
    }

}
