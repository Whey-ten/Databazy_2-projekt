package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import application.ui.MainMenu;
import org.postgresql.ds.PGSimpleDataSource;

//import sk.uniba.fmph.simko.db2.application.ui.MainMenu;
/*addni tam co spravis svoje menucko*/

/**
 *
 * @author Sebastian Jankovic
 */
public class Main {
    public static void main(String[] args) throws SQLException, IOException {

        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        //dataSource.setServerName("SERVER_NAME");
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(5432);
        //dataSource.setDatabaseName("DB_NAME");
        dataSource.setDatabaseName("postgres");
        //dataSource.setUser("USER_NAME");
        dataSource.setUser("postgres");
        //dataSource.setPassword("PASSWORD");
        dataSource.setPassword("123456");

        try (Connection connection = dataSource.getConnection()) {
            DbContext.setConnection(connection);

            MainMenu mainMenu = new MainMenu();
            mainMenu.start();

        } finally {
            DbContext.clear();
        }
    }
}
