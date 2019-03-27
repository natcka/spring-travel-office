//package com.example.newtraveloffice;
//
//
//import com.example.newtraveloffice.exceptions.NoSuchCustomerException;
//import com.example.newtraveloffice.exceptions.NoSuchTripException;
//import com.example.newtraveloffice.models.*;
//import com.example.newtraveloffice.services.TravelOfficeService;
//
//import javax.xml.bind.ValidationException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Scanner;
//import java.util.logging.Logger;
//
//public class MainHandler implements UserInterface {
//
//    private TravelOfficeService travelOfficeService;
//
//    private Scanner scanner = new Scanner(System.in);
//
//    private static Logger logger = Logger.getLogger("com.company.TravelOffice");
//
//    public MainHandler(TravelOfficeService travelOfficeService) {
//        this.travelOfficeService = travelOfficeService;
//    }
//
//    @Override
//    public Customer addCustomer() {
//        logger.info("adding new customer [tu_nazwa_usera]");
//
//        System.out.println("Podaj imię: ");
//        String name = scanner.nextLine();
//
//        System.out.println("Podaj nazwisko: ");
//        String surname = scanner.nextLine();
//
//        System.out.println("Adres\nPodaj ulicę: ");
//        String street = scanner.nextLine();
//
//        System.out.println("Podaj kod pocztowy: ");
//        String zip = scanner.nextLine();
//        try {
//            validateZipCode(zip);
//            System.out.println("Podaj miasto: ");
//            String city = scanner.nextLine();
//            Address address = new Address(street, zip, city);
//            Customer newCustomer = new Customer(name, surname);
//            newCustomer.setAddress(address);
//            travelOfficeService.addCustomer(newCustomer);
//            return newCustomer;
//        } catch (ValidationException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        return null;
//    }
//
//    @Override
//    public Trip addTrip() throws IllegalArgumentException {
//        logger.info("adding new trip [tu_nazwa_usera]");
//
//        Trip newTrip = null;
//        System.out.println("Wycieczka krajowa czy zagraniczna? (K/Z)");
//        String s = scanner.nextLine();
//
//        if (!s.toLowerCase().equals("k") && !s.toLowerCase().equals("z")) {
//            throw new IllegalArgumentException("Błędny typ wycieczki.\nDostępne opcje: K - krajowa, Z - zagraniczna");
//        }
//
//        System.out.println("Podaj destynację: ");
//        String destionation = scanner.nextLine();
//        System.out.println("Podaj cenę: ");
//        String price = scanner.nextLine();
//        System.out.println("Podaj datę od (w formacie dd-MM-yyyy): ");
//        String dateFrom = scanner.nextLine();
//
//        try {
//            validateDateString(dateFrom);
//        } catch (ValidationException ex) {
//            logger.warning("checked exception");
//            System.out.println(ex.getMessage());
//        }
//
//        System.out.println("Podaj datę do (w formacie dd-MM-yyyy): ");
//        String dateTo = scanner.nextLine();
//
//        try {
//            validateDateString(dateTo);
//        } catch (ValidationException ex) {
//            logger.warning("checked exception");
//            System.out.println(ex.getMessage());
//        }
//
//        if (s.equalsIgnoreCase("k")) {
//            System.out.println("Podaj rabat: ");
//            String discount = scanner.nextLine();
//            newTrip = new DomesticTrip(LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("dd-MM-yyyy")), LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("dd-MM-yyyy")), destionation, Long.valueOf(price), Long.valueOf(discount), true);
//        } else if (s.equalsIgnoreCase("z")) {
//            System.out.println("Podaj ubezpieczenie: ");
//            String insurance = scanner.nextLine();
//            newTrip = new AbroadTrip(LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("dd-MM-yyyy")), LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("dd-MM-yyyy")), destionation, Long.valueOf(price), Long.valueOf(insurance), false);
//        }
//
//        return newTrip;
//    }
//
//    @Override
//    public void assign() {
//        logger.info("assigning new customer [tu_nazwa_usera]");
//
//        String name = null;
//        String destination = null;
//
//        try {
//            System.out.println("Podaj nazwisko klienta: ");
//            name = scanner.nextLine();
//
//            System.out.println("Podaj destynację: ");
//            destination = scanner.nextLine();
//
//            travelOfficeService.assign(name, destination);
//        } catch (NoSuchCustomerException ex) {
//            logger.warning("checked exception");
//            System.out.println(ex.getMessage() + name);
//        } catch (NoSuchTripException ex) {
//            logger.warning("checked exception");
//            System.out.println(ex.getMessage() + destination);
//        }
//    }
//
//    @Override
//    public void removeCustomer() {
//        logger.info("removing customer [tu_nazwa_usera]");
//
//        System.out.println("Podaj nazwisko: ");
//        String surname = scanner.nextLine();
//        System.out.println("Podaj imię: ");
//        String name = scanner.nextLine();
//        try {
//            travelOfficeService.removeCustomer(surname, name);
//        } catch (NoSuchCustomerException ex) {
//            logger.warning("checked exception");
//            System.out.println(ex.getMessage() + surname + " " + name);
//        }
//    }
//
//    @Override
//    public void removeTrip() {
//        logger.info("removing trip [tu_nazwa_usera]");
//
//        System.out.println("Podaj destynację: ");
//        String destination = scanner.nextLine();
//        try {
//            travelOfficeService.removeTrip(destination);
//        } catch (NoSuchTripException ex) {
//            logger.warning("checked exception");
//            System.out.println(ex.getMessage() + destination);
//        }
//    }
//
//    @Override
//    public void showCustomers() {
//        logger.info("showing all customers [tu_nazwa_usera]");
//
//        travelOfficeService.getAllCustomers();
//    }
//
//    @Override
//    public void showTrips() {
//        logger.info("showing all trips [tu_nazwa_usera]");
//
//        travelOfficeService.getAllTrips();
//    }
//
//    private void validateZipCode(String zip) throws ValidationException {
//        char[] chars = zip.toCharArray();
//        for (int i = 0; i < chars.length; i++) {
//            if (i == 2) {
//                if (chars[i] != '-') {
//                    throw new ValidationException("Błędny kod pocztowy");
//                }
//            } else if (!Character.isDigit(chars[i])) {
//                throw new ValidationException("Błędny kod pocztowy");
//            }
//        }
//
//    }
//
//    private void validateDateString(String date) throws ValidationException {
//        if (date.charAt(2) != date.charAt(5) && date.charAt(2) != '-') {
//            logger.warning("checked exception");
//            throw new ValidationException("Błedny format daty");
//        }
//
//        LocalDate now = LocalDate.now();
//        LocalDate parseDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
////        if (parseDate.getYear() < now.getYear() || parseDate.getMonthValue() < now.getMonthValue() || parseDate.getDayOfMonth() < now.getDayOfMonth()) {
////            throw new ValidationException("Błędna data - data nie może być wcześniejsza niż aktualna");
////        }
////
////        if (!(parseDate.getMonthValue() < 12 && parseDate.getMonthValue() > 1)) {
////            throw new ValidationException("Błędna data - miesiąc poza zakresem");
////        }
////
////        if (parseDate.getMonth().equals(Month.FEBRUARY) && parseDate.getDayOfMonth() > 28) {
////            throw new ValidationException("Błędna data - podany miesiąc nie ma tyle dni");
////        }
////
////        List<Month> evenNoOfDays = Arrays.asList(Month.APRIL, Month.JUNE, Month.SEPTEMBER, Month.NOVEMBER);
////        List<Month> oddNoOfDays = Arrays.asList(Month.JANUARY, Month.MARCH, Month.MAY, Month.JULY, Month.AUGUST, Month.OCTOBER, Month.DECEMBER);
////
////        if (evenNoOfDays.contains(parseDate.getMonth())) {
////            for (Month m : evenNoOfDays) {
////                if (parseDate.getDayOfMonth() > 30) {
////                    throw new ValidationException("Błędna data - podany miesiąc nie ma tyle dni");
////                }
////            }
////        } else if (oddNoOfDays.contains(parseDate.getMonth())) {
////            for (Month m : oddNoOfDays) {
////                if (parseDate.getDayOfMonth() > 31) {
////                    throw new ValidationException("Błędna data - podany miesiąc nie ma tyle dni");
////                }
////            }
////        } else {
////            throw new ValidationException("Wystąpił nieznany błąd - sprawdź poprawność daty");
////        }
//    }
//}
