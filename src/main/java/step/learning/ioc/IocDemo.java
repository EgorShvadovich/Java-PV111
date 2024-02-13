package step.learning.ioc;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import step.learning.services.hash.HashService;
import step.learning.services.rnd.CodeGenerator;
import step.learning.services.rnd.SeparationNumberCodeGenerator;

public class IocDemo {
    @Inject @Named("hash-128")
    private HashService hashServiceField ;

    private final HashService hashService ;
    private final CodeGenerator codeGenerator ;

    @Inject
    public IocDemo(@Named("hash-160") HashService hashService, CodeGenerator codeGenerator) {
        this.hashService = hashService;
        this.codeGenerator = codeGenerator;
    }

    public void run() {
        System.out.println("Inversion of Control");
        System.out.println( hashService.hash("123") );
        System.out.println( hashServiceField.hash("123") );
        System.out.println( codeGenerator.newCode(20) ) ;
        //System.out.println( SeparationNumberCodeGenerator.newCode(20) ) ;
        // System.out.println( hashService.hashCode() + " " + hashServiceField.hashCode() );
    }
}
