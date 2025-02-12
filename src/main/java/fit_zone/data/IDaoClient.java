package fit_zone.data;

import fit_zone.domain.Client;

import java.util.List;

public interface IDaoClient {
    List<Client> getAllClients();
    boolean searchClient(Client client);
    boolean addClient(Client client);
    boolean updateClient(Client client);
    boolean deleteClient(Client client);


}