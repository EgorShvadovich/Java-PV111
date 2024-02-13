package step.learning.db;

import com.google.inject.Inject;
import com.google.inject.Provides;
import step.learning.services.rnd.CodeGenerator;

import java.sql.*;
import java.util.Random;
import java.util.UUID;

public class DbDemo {
   private final Connection connection;
   private final CodeGenerator codeGenerator;
   private final Random random;

   @Inject
    public DbDemo(Connection connection, CodeGenerator codeGenerator, Random random) {
        this.connection = connection;
       this.codeGenerator = codeGenerator;
       this.random = random;
   }

    private void createTable(){
       String sql = "CREATE TABLE  IF NOT EXISTS  homework (" +
               "id BIGINT UNSIGNED  PRIMARY KEY  DEFAULT (UUID_SHORT()),"
               + "rand_int  INT," +
               "rand_str  VARCHAR(64)," +
               "rand_uuid  VARCHAR(64)," +
               "val1 VARCHAR(64)," +
               "val2 VARCHAR(64)" +
               ") ENGINE = InnoDB, DEFAULT CHARSET = utf8mb4";

       try(Statement statement = connection.createStatement() ) {
           statement.executeUpdate(sql);
           System.out.println("create ok");
       } catch (SQLException e) {
           System.err.println(e.getMessage());
       }
    }
    private void addRandom(){
       String sql = "INSERT INTO homework(rand_int,rand_str,rand_uuid, val1, val2) VALUES (?, ?, ?, ?, ?)";
       try(PreparedStatement prep = connection.prepareStatement(sql)) {
           prep.setInt(1,random.nextInt());
           prep.setString(2,codeGenerator.newCode(10));
           prep.setString(3,UUID.randomUUID().toString());
           prep.setString(4,"value1");
           prep.setString(5,"value2");
           prep.executeUpdate();

           System.out.println("Insert OK");
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }
    private void printRandoms(){
       String sql = "SELECT * FROM homework";
       try( Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery(sql);
            while (res.next()){
                System.out.printf("%d\t%d\t%s\t%s\t%s\t%s\n",
                res.getLong("id"),
                res.getInt("rand_int"),
                res.getString("rand_str"),
                res.getString("rand_uuid"),
                res.getString("val1"),
                res.getString("val2"));

            }
       } catch (SQLException e) {
           System.err.println(e.getMessage());
       }
    }

    public void run(){
    if(connection != null){
        System.out.println("Connection ok");
            //createTable();
            addRandom();

            printRandoms();

        }
    }
}
