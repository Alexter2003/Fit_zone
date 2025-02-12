package fit_zone.data;

import fit_zone.domain.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static fit_zone.connection.ConnectionDB.getConnection;

public class DAOClient implements IDaoClient {

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();

        //to make SQL queries
        PreparedStatement ps;
        //to receive the response of the query
        ResultSet rs;

        //get connection with static import
        Connection conection = getConnection();

        var query = "SELECT * FROM clientes ORDER BY id";

        try {
            ps = conection.prepareStatement(query);
            rs = ps.executeQuery();
            //iterate the result set
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("nombre"));
                client.setLastName(rs.getString("apellidos"));
                client.setMemebership(rs.getInt("membresia"));
                clients.add(client);
            }

        } catch (Exception e) {
            System.out.println("Error to get all clients: " + e.getMessage());
        } finally {
            try {
                conection.close();
            } catch (Exception e) {
                System.out.println("Error to close connection: " + e.getMessage());
            }
        }
        return clients;
    }

    @Override
    public boolean searchClient(Client client) {
        PreparedStatement ps;
        ResultSet rs;

        var connection = getConnection();
        var sql = "SELECT * FROM clientes WHERE id = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, client.getId());
            rs = ps.executeQuery();

            if (rs.next()) {
                client.setName(rs.getString("nombre"));
                client.setLastName(rs.getString("apellidos"));
                client.setMemebership(rs.getInt("membresia"));
                return true;
            }

        } catch (Exception e) {
            System.out.println("Error to search client: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                System.out.println("Error to close connection: " + e.getMessage());
            }

        }
        return false;
    }

    @Override
    public boolean addClient(Client client) {
        PreparedStatement ps;
        var connection = getConnection();

        var sql = "INSERT INTO clientes (nombre, apellidos, membresia)  VALUES (?, ?, ?)";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, client.getName());
            ps.setString(2, client.getLastName());
            ps.setInt(3, client.getMemebership());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error to add client: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                System.out.println("Error to close connection: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean updateClient(Client client) {
        PreparedStatement ps;
        var connection = getConnection();
        var sql = "UPDATE clientes SET nombre = ?, apellidos = ?, membresia = ? WHERE id = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, client.getName());
            ps.setString(2, client.getLastName());
            ps.setInt(3, client.getMemebership());
            ps.setInt(4, client.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error to update client: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                System.out.println("Error to close connection: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean deleteClient(Client client) {
        PreparedStatement ps;
        Connection connection = getConnection();

        var sql = "DELETE FROM clientes WHERE id = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, client.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error to delete client: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                System.out.println("Error to close connection: " + e.getMessage());
            }
        }
        return false;
    }

    public static void main(String[] args) {
        var daoClient = new DAOClient();
//        System.out.println("*** Clients  ***");
//        var clients = daoClient.getAllClients();
//        clients.forEach(System.out::println);


//        var client = new Client(5);
//        System.out.println("*** Search client ***");
//        System.out.println(client + "\n");
//
//        var clientFound = daoClient.searchClient(client);
//        if(clientFound){
//            System.out.println("Client found: " + client);
//        }else{
//            System.out.println("Client not found: " + client.getId());
//        }

//        var client = new Client("Mishell", "Estrada", 600);
//        System.out.println("*** Add client ***");
//        System.out.println(client + "\n");
//
//        var clientAdded = daoClient.addClient(client);
//        if(clientAdded){
//            System.out.println("Client added: " + client);
//        }else{
//            System.out.println("Client not added: " + client);
//        }

//        var client = new Client(2, "Catherine", "Gonzales", 700);
//        System.out.println("*** Update client ***");
//        var updated = daoClient.updateClient(client);
//        if(updated){
//            System.out.println("Client: " + client.getId() + " has been updated");
//            System.out.println(client);
//        }else{
//            System.out.println("Client not updated: " + client);
//        }

        var client = new Client(3);
        System.out.println("*** Delete Client ***");
        var deleted = daoClient.deleteClient(client);
        if(deleted){
            System.out.println("Client: " + client.getId() + " has been deleted");
        }else{
            System.out.println("Client not deleted: " + client);
        }
    }
}
