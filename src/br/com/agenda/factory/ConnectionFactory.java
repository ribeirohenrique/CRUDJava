package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    //Nome de usuario do MariaDB
    private static final String USERNAME = "developer";

    //Senha do usuario do MariaDB
    private static final String PASSWORD = "RaspberryDatabaseMaria";

    //Caminho do banco de dados', porta, nome do banco de dados
    private static final String DATABASE_URL = "jdbc:mariadb://192.168.15.197:3306/agenda";

    //Conexão com o banco de dados

    public static Connection createConnectionToMariaDB() throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");

        Connection connect = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

        return connect;
    }

    public static void main(String[] args) throws Exception {
        //Recuperar a conexão com o BD
        Connection conn = createConnectionToMariaDB();

        //Testar se a conexão é nula
        if (conn != null) {
            System.out.println("Conexão obtida com sucesso!");
            conn.close();
        }

    }
}
