package step.learning;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import step.learning.db.DbDemo;
import step.learning.ioc.IocDemo;
import step.learning.ioc.ServiceModule;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        //new Basics().run();
        //new FileIO().run();
        //new OopDemo().run();
        //new AsyncDemo().run();
        //new MultiTask().demo();
        //new DbDemo().run();

        //Injector injector = Guice.createInjector( new ServiceModule() ) ;   // модуль IoC
        //IocDemo instance = injector.getInstance( IocDemo.class ) ;  // заміна new - Object Resolve
        //instance.run();
        try(ServiceModule services = new ServiceModule()){
            Guice.createInjector(services ).getInstance( DbDemo.class ).run() ;

        } catch (Exception ignored){

        }

    }
}
