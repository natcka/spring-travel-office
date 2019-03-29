package com.example.newtraveloffice.services;

import com.example.newtraveloffice.exceptions.NoSuchCustomerException;
import com.example.newtraveloffice.exceptions.NoSuchTripException;
import com.example.newtraveloffice.models.Customer;
import com.example.newtraveloffice.models.TravelOffice;
import com.example.newtraveloffice.models.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@ComponentScan(basePackages = {"com.example.newtraveloffice.models", "com.example.newtraveloffice.services"})
public class TravelOfficeService {

    @Autowired
    private TravelOffice travelOffice;

    public Customer addCustomer(Customer customer) {
        return travelOffice.addCustomer(customer);
    }

    public void removeCustomer(String name, String surname) throws NoSuchCustomerException {
        travelOffice.removeCustomer(name, surname);
    }

    public Customer findCustomerBySurname(String surname) throws NoSuchCustomerException {
        return travelOffice.findCustomerBySurname(surname);
    }

    public List<Customer> getAllCustomers() {
        HashSet<Customer> setOfCustomers = travelOffice.getSetOfCustomers();
        List<Customer> result = new ArrayList<>();
        for (Customer c : setOfCustomers) {
            result.add(c);
        }
        return result;
    }

    public void assign(String surname, String description) throws NoSuchTripException, NoSuchCustomerException {
        travelOffice.assign(surname, description);
    }

    public Trip addTrip(Trip trip) {
        return travelOffice.addTrip(trip.getDestination(), trip);
    }

    public void removeTrip(String key) throws NoSuchTripException {
        travelOffice.removeTrip(key);
    }

    public Trip findTripByDestination(String destination) throws NoSuchTripException {
        return travelOffice.findTripByDestination(destination);
    }

    public List<Trip> getAllTrips() {
        HashMap<String, Trip> mapOfTrips = travelOffice.getMapOfTrips();
        List<Trip> result = new ArrayList<>(mapOfTrips.values());
        return result;
    }
}
