package step.learning.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import step.learning.services.hash.HashService;
import step.learning.services.hash.MdHashService;
import step.learning.services.hash.Sha1HashService;
import step.learning.services.rnd.CodeGenerator;
import step.learning.services.rnd.DigitCodeGenerator;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;

public class ServiceModule extends AbstractModule implements AutoCloseable{
    private Driver mySqlDriver;
    private Connection connection;
    @Provides
    private Connection getConnection(){
        if(connection == null){
            try {
                mySqlDriver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(mySqlDriver);
                String connectionString = "jdbc:mysql://localhost:3306/java_111" +
                        "?useUnicode=true&characterEncoding=UTF-8";
                connection = DriverManager.getConnection(connectionString,"user_111","pass_111");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
    @Override
    protected void configure() {
        // bind(HashService.class).to(Sha1HashService.class);

        bind(HashService.class)
                .annotatedWith( Names.named("hash-160") )
                .to(Sha1HashService.class);

        bind(HashService.class)
                .annotatedWith( Names.named("hash-128") )
                .to(MdHashService.class);

        bind( Random.class ).toInstance( new Random() );

        bind( CodeGenerator.class ).to( DigitCodeGenerator.class ) ;
    }


    @Override
    public void close() throws Exception {
        if(connection != null){
            connection.close();
        }
        if(mySqlDriver != null){
            DriverManager.deregisterDriver(mySqlDriver);
        }
    }
}
