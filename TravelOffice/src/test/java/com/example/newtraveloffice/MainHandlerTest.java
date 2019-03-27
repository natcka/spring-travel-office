//package com.example.newtraveloffice;
//
//import com.example.newtraveloffice.exceptions.NoSuchCustomerException;
//import com.example.newtraveloffice.exceptions.NoSuchTripException;
//import com.example.newtraveloffice.models.Customer;
//import com.example.newtraveloffice.models.TravelOffice;
//import com.example.newtraveloffice.models.Trip;
//import com.example.newtraveloffice.services.TravelOfficeService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
//
//import java.util.HashSet;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class MainHandlerTest {
//
////    private TravelOffice travelOffice = new TravelOffice();
////
////    private TravelOfficeService travelOfficeService = new TravelOfficeService(travelOffice);
//
//    @Mock
//    TravelOffice travelOffice;
//
//    @InjectMocks
//    TravelOfficeService travelOfficeService;
//
//    @Test(expected = NoSuchCustomerException.class)
//    public void shouldThrowNoSuchCustomerExceptionIfCustomerFoundByNameIsNull() throws NoSuchCustomerException {
//        when(travelOffice.findCustomerByName("Nowak")).thenThrow(new NoSuchCustomerException());
//        assertEquals(new NoSuchCustomerException(), travelOfficeService.findCustomerByName("Nowak"));
////        String name = "Nowak";
////
////        travelOfficeService.findCustomerByName(name);
//    }
//
//    @Test
//    public void shouldReturnCustomerIfCustomerExists() throws NoSuchCustomerException {
//        //given
//        Customer customer1 = new Customer("Adam", "Nowak");
//        Customer customer2 = new Customer("Adam", "Kowal");
//        HashSet<Customer> setOfCustomers = travelOffice.getSetOfCustomers();
//        setOfCustomers.add(customer1);
//        setOfCustomers.add(customer2);
//        travelOffice.setSetOfCustomers(setOfCustomers);
//
//        String name = "Kowal";
//
//        //when
//        Customer customer = mock(Customer.class);
//        when(travelOffice.findCustomerByName(name)).thenReturn(customer);
//        assertEquals(customer, travelOfficeService.findCustomerByName(name));
//    }
//
//    @Test(expected = NoSuchTripException.class)
//    public void shouldThrowNoSuchTripExceptionIfTripFoundByDestinationIsNull() throws NoSuchTripException {
//        when(travelOffice.findTripByDestination("Malibu")).thenThrow(new NoSuchTripException());
//        assertEquals(new NoSuchTripException(), travelOfficeService.findTripByDestination("Malibu"));
//    }
//
//    @Test
//    public void shouldReturnTripIfTripExists() throws NoSuchTripException {
//        //given
////        Trip trip1 = new Trip(LocalDate.now(), LocalDate.now(), "Malibu", 1234L, false);
////        Trip trip2 = new Trip(LocalDate.now(), LocalDate.now(), "Pcim", 1234L, true);
////        HashMap<String, Trip> mapOfTrips = travelOffice.getMapOfTrips();
////        mapOfTrips.put(trip1.getDestination(), trip1);
////        mapOfTrips.put(trip2.getDestination(), trip2);
////        travelOffice.setMapOfTrips(mapOfTrips);
//
//        String destination = "Malibu";
//
//        //when
//        Trip trip = mock(Trip.class);
//        when(travelOffice.findTripByDestination(destination)).thenReturn(trip);
//        assertEquals(trip, travelOfficeService.findTripByDestination(destination));
//    }
//
//    @Test
//    public void shouldReturnTrueIfTripIsAssignToCustomer() throws NoSuchTripException, NoSuchCustomerException {
//        when(travelOffice.assign("Nowak", "Malibu")).thenReturn(true);
//        assertEquals(true, travelOfficeService.assign("Nowak", "Malibu"));
//    }
//
//    @Test
//    public void shouldReturnTrueWhenCustomerHasBeenRemoved() throws NoSuchCustomerException {
//        when(travelOffice.removeCustomer("Nowak", "Adam")).thenReturn(true);
//        assertEquals(true, travelOfficeService.removeCustomer("Nowak", "Adam"));
//    }
//
//}