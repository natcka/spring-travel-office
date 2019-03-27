//package com.example.newtraveloffice;
//
//import com.example.newtraveloffice.models.AbroadTrip;
//import com.example.newtraveloffice.models.Customer;
//import com.example.newtraveloffice.models.TravelOffice;
//import com.example.newtraveloffice.models.Trip;
//import com.example.newtraveloffice.services.TravelOfficeService;
//
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Scanner;
//import java.util.logging.FileHandler;
//import java.util.logging.Logger;
//import java.util.logging.SimpleFormatter;
//
//public class Main {
//
//    private static Scanner scanner = new Scanner(System.in);
//
//    private static TravelOfficeService travelOfficeService = new TravelOfficeService(new TravelOffice());
//
//    private static UserInterface ui = new MainHandler(travelOfficeService);
//
//    private static Logger logger = Logger.getLogger("com.company.TravelOffice");
//
//    private static final String menu = "\nMENU: \n" +
//            "\t1. Dodaj klienta\n" +
//            "\t2. Dodaj wycieczkę\n" +
//            "\t3. Przypisz wycieczkę do klienta\n" +
//            "\t4. Usuń klienta\n" +
//            "\t5. Usuń wycieczkę\n" +
//            "\t6. Pokaż klientów\n" +
//            "\t7. Pokaż wycieczki\n" +
//            "\t8. Wyjdź";
//
//    public static void main(String[] args) throws IOException {
//        logger.setUseParentHandlers(false);
//        FileHandler fileHandler = new FileHandler("log.txt");
//        fileHandler.setFormatter(new SimpleFormatter());
//        logger.addHandler(fileHandler);
//
//        logger.info("Running application [tu_user_login]");
//        userMenu();
//    }
//
//    private static void userMenu() {
//        int i = 0;
//
//        do {
//            System.out.println(menu);
//            i = scanner.nextInt();
//
//            switch (i) {
//                case 1:
//                    Customer customer = ui.addCustomer();
//                    HashSet<Customer> setOfCustomers = travelOfficeService.getSetOfCustomers();
//                    if (customer != null) {
//                        setOfCustomers.add(customer);
//                        System.out.println("Dodano klienta [nazwisko= " + customer.getSurname() + ", imię= " + customer.getName() + "]");
//                        break;
//                    } else {
//                        System.out.println("Nie udało się dodać klienta - sprawdź logi");
//                        break;
//                    }
//
//                case 2:
//                    Trip trip;
//                    try {
//                        trip = ui.addTrip();
//                    } catch (IllegalArgumentException ex) {
//                        System.out.println(ex.getLocalizedMessage());
//                        break;
//                    }
//                    HashMap<String, Trip> mapOfTrips = travelOfficeService.getMapOfTrips();
//                    mapOfTrips.put(trip.getDestination(), trip);
//                    System.out.println("Dodano wycieczkę " + (trip instanceof AbroadTrip ? "zagraniczną " : "krajową ")
//                            + "[destynacja= " + trip.getDestination() + "]");
//                    break;
//
//                case 3:
//                    ui.assign();
//                    break;
//
//                case 4:
//                    ui.removeCustomer();
//                    break;
//
//                case 5:
//                    ui.removeTrip();
//                    break;
//
//                case 6:
//                    System.out.println("\nLISTA KLIENTÓW");
//                    ui.showCustomers();
//                    break;
//
//                case 7:
//                    System.out.println("\nLISTA WYCIECZEK");
//                    ui.showTrips();
//                    break;
//
//                case 8:
//                    logger.info("Shutting down the app");
//                    System.exit(0);
//                    break;
//
//                default:
//                    System.out.println(menu);
//            }
//        } while (i <= 8);
//    }
//}
