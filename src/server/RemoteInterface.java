package server;

import common.UserInterface;
import common.model.User;
import common.model.Task;
import server.database.ConnectionDB;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RemoteInterface extends UnicastRemoteObject implements UserInterface{
    public RemoteInterface() throws RemoteException {
        super();
    }

    public int authorisation(String login, String password) throws RemoteException {
        System.out.println("sdsdsd");
        ConnectionDB databaseConnector = new ConnectionDB();
        return  databaseConnector.checkAuthorisation(login, password);
    }

    public void addNewUser(String login, String password, int rights) throws RemoteException {
        ConnectionDB connectionDB = new ConnectionDB();
        connectionDB.addNewUser(login, password, rights);
    }

    public ArrayList<User> getAllUsers() throws RemoteException {
        ConnectionDB connectionDB = new ConnectionDB();
        return connectionDB.getAllUsers();
    }

    public boolean deleteFromDB(String tableName, String lineId) throws RemoteException{
        ConnectionDB connectionDB = new ConnectionDB();
        return connectionDB.deleteFromDB(tableName, lineId);
    }

    public ArrayList<Task> getAllCurrentTasks() throws RemoteException {
        ConnectionDB connectionDB = new ConnectionDB();
        return connectionDB.getAllCurrentTasks();
    }

    public boolean updateTaskRow(String field, String value,int id) throws RemoteException {
        ConnectionDB connectionDB = new ConnectionDB();
        return connectionDB.updateTaskRow(field, value, id);
    }
}