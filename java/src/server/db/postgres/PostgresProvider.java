package server.db.postgres;

import server.db.IGameDAO;
import server.db.IPersistenceProvider;
import server.db.IUserDAO;

import java.sql.*;

/**
 * Created by elija on 12/2/2016.
 */
public class PostgresProvider implements IPersistenceProvider {

    private PostgresUserDAO userDAO;
    private PostgresGameDAO gameDAO;
    private Connection db;

    public PostgresProvider() {
        try {
            Class.forName("org.postgresql.Driver");
            db = DriverManager.getConnection("jdbc:postgresql://localhost/template1", "postgres", "family7");
            Statement stmt = db.createStatement();
            stmt.execute("DROP DATABASE CATANDB;");
            ResultSet rs = stmt.executeQuery("SELECT 1 FROM pg_database WHERE datname = 'catandb';");
            if (!rs.next()) {
                rs = stmt.executeQuery("SELECT 1 FROM pg_roles WHERE rolname='player';");
                if (!rs.next()) {
                    stmt.execute("CREATE USER PLAYER WITH PASSWORD 'catan';");
                }
                stmt.execute("CREATE DATABASE CATANDB;");
                stmt.execute("GRANT ALL PRIVILEGES ON DATABASE CATANDB TO PLAYER;");
                stmt.execute("ALTER USER PLAYER WITH SUPERUSER;");
            }
            rs.close();
            stmt.close();
            db = DriverManager.getConnection("jdbc:postgresql://localhost/catandb", "player", "catan");


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        userDAO = new PostgresUserDAO(db);
        gameDAO = new PostgresGameDAO(db);
        createDB();
    }

    @Override
    public boolean createDB() {
        try {
            Statement stmt = db.createStatement();
            stmt.execute("DO $do$ " +
                    "BEGIN " +
                    " CREATE TABLE IF NOT EXISTS USERINFO(ID INT PRIMARY KEY NOT NULL, " +
                    "USERNAME TEXT NOT NULL, " +
                    "PASSWORD TEXT NOT NULL); " +
                    "END; " +
                    "$do$");
            stmt.execute("DO $do$ " +
                    "BEGIN " +
                    " CREATE TABLE IF NOT EXISTS GAMES(ID INT PRIMARY KEY NOT NULL, " +
                    "MODEL BYTEA NOT NULL); " +
                    "END; " +
                    "$do$");
            stmt.execute("DO $do$ " +
                    "BEGIN " +
                    " CREATE TABLE IF NOT EXISTS COMMANDS(ID INT NOT NULL, " +
                    "COMMAND_ORDER INT NOT NULL, " +
                    "COMMAND_TYPE TEXT NOT NULL, " +
                    "COMMAND BYTEA NOT NULL); " +
                    "END; " +
                    "$do$");
            stmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean startTransaction() {
        try {
            Statement stmt = db.createStatement();
            stmt.execute("BEGIN TRANSACTION");
            stmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean finishTransaction() {
        try {
            Statement stmt = db.createStatement();
            stmt.execute("END TRANSACTION");
            stmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean clearDB() {
        try {
            Statement stmt = db.createStatement();
            stmt.execute("DELETE FROM USERINFO");
            stmt.execute("DELETE FROM GAMES");
            stmt.execute("DELETE FROM COMMANDS");
            stmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public IUserDAO getUserDAO() {
        return userDAO;
    }

    @Override
    public IGameDAO getGameDAO() {
        return gameDAO;
    }
}
