package fit_zone.presentation;

import fit_zone.data.DAOClient;
import fit_zone.domain.Client;

import java.util.Scanner;

public class Menu {

    public static void menu(){
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit){
            System.out.println("\n1. List all clients");
            System.out.println("2. Add new client");
            System.out.println("3. Update client");
            System.out.println("4. Delete client");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            var option = Integer.parseInt(sc.nextLine());

            if(option == 5){
                exit = true;
            }else{
                executeOption(option);
            }
        }
    }

    private static void executeOption(int option){
        DAOClient daoClient = new DAOClient();

        switch(option){
            case 1:
                listAllClients(daoClient);
                break;
            case 2:
                addNewClient(daoClient);
                break;
            case 3:
                updateClient(daoClient);
                break;
            case 4:
                deleteClient(daoClient);
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    private static void listAllClients(DAOClient daoClient){
        System.out.println("*** Clients  ***");
        var clients = daoClient.getAllClients();
        clients.forEach(System.out::println);
    }

    private static void addNewClient(DAOClient daoClient){
        Scanner sc = new Scanner(System.in);
        String name;
        String lastName;
        int membership;

        System.out.println("Enter name: ");
        name = sc.nextLine();
        System.out.println("Enter last name: ");
        lastName = sc.nextLine();
        System.out.println("Enter membership: ");
        membership = Integer.parseInt(sc.nextLine());

        var client = new Client(name, lastName, membership);

        daoClient.addClient(client);
    }

    private static void updateClient(DAOClient daoClient){
        Scanner sc = new Scanner(System.in);
        int id;
        String name;
        String lastName;
        int membership;

        System.out.println("Enter id: ");
        id = Integer.parseInt(sc.nextLine());
        System.out.println("Enter or modify name: ");
        name = sc.nextLine();
        System.out.println("Enter or modify last name: ");
        lastName = sc.nextLine();
        System.out.println("Enter or modify membership: ");
        membership = Integer.parseInt(sc.nextLine());

        var client = new Client(id, name, lastName, membership);

        daoClient.updateClient(client);
    }

    private static void deleteClient(DAOClient daoClient){
        Scanner sc = new Scanner(System.in);
        int id;

        System.out.println("Enter id: ");
        id = Integer.parseInt(sc.nextLine());

        var client = new Client(id);

        daoClient.deleteClient(client);
    }

    public static void main(String[] args) {
        Menu.menu();
    }
}
