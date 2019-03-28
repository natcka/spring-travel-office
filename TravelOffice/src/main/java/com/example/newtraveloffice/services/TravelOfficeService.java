package com.example.newtraveloffice.services;



import com.example.newtraveloffice.exceptions.NoSuchCustomerException;
import com.example.newtraveloffice.exceptions.NoSuchTripException;
import com.example.newtraveloffice.models.AbroadTrip;
import com.example.newtraveloffice.models.Customer;
import com.example.newtraveloffice.models.TravelOffice;
import com.example.newtraveloffice.models.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
@ComponentScan(basePackages = {"com.example.newtraveloffice.models", "com.example.newtraveloffice.services"})
public class TravelOfficeService {

    @Autowired
    private TravelOffice travelOffice;

    public Customer addCustomer(Customer customer) {
        return travelOffice.addCustomer(customer);
    }

    public Customer findCustomerBySurname(String surname) throws NoSuchCustomerException {
        return travelOffice.findCustomerBySurname(surname);
    }

    public void removeCustomer(String surname, String name) throws NoSuchCustomerException {
        travelOffice.removeCustomer(surname, name);
    }

    public Trip findTripByDestination(String destination) throws NoSuchTripException {
        return travelOffice.findTripByDestination(destination);
    }

    public Trip addTrip(Trip trip) {
        return travelOffice.addTrip(trip.getDestination(), trip);
    }


    public boolean removeTrip(String key) throws NoSuchTripException {
        return travelOffice.removeTrip(key);
    }

    public void getAllTrips() {
        travelOffice.getAllTrips();
    }

    public List<Customer> getAllCustomers() {
        HashSet<Customer> setOfCustomers = travelOffice.getSetOfCustomers();
        List<Customer> result = new ArrayList<>();
        for (Customer c : setOfCustomers) {
            result.add(c);
        }
        return result;
    }

    public HashMap<String, Trip> getMapOfTrips() {
        return travelOffice.getMapOfTrips();
    }

    public void assign(String surname, String description) throws NoSuchTripException, NoSuchCustomerException {
        travelOffice.assign(surname, description);
    }
}
