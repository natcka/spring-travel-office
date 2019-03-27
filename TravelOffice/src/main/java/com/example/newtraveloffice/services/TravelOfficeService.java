package com.example.newtraveloffice.services;



import com.example.newtraveloffice.exceptions.NoSuchCustomerException;
import com.example.newtraveloffice.exceptions.NoSuchTripException;
import com.example.newtraveloffice.models.Customer;
import com.example.newtraveloffice.models.TravelOffice;
import com.example.newtraveloffice.models.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;

@Service
@ComponentScan(basePackages = {"com.example.newtraveloffice.models", "com.example.newtraveloffice.services"})
public class TravelOfficeService {

    @Autowired
    private TravelOffice travelOffice;

    public Customer addCustomer(Customer customer) {
        return travelOffice.addCustomer(customer);
    }

    public Customer findCustomerByName(String name) throws NoSuchCustomerException {
        return travelOffice.findCustomerByName(name);
    }

    public boolean removeCustomer(String surname, String name) throws NoSuchCustomerException {
        return travelOffice.removeCustomer(surname, name);
    }

    public void getAllCustomers() {
        travelOffice.getAllCustomers();
    }

    public Trip findTripByDestination(String destination) throws NoSuchTripException {
        return travelOffice.findTripByDestination(destination);
    }

    public void removeTrip(String key) throws NoSuchTripException {
        travelOffice.removeTrip(key);
    }

    public void getAllTrips() {
        travelOffice.getAllTrips();
    }

    public HashSet<Customer> getSetOfCustomers() {
        return travelOffice.getSetOfCustomers();
    }

    public HashMap<String, Trip> getMapOfTrips() {
        return travelOffice.getMapOfTrips();
    }

    public boolean assign(String surname, String description) throws NoSuchTripException, NoSuchCustomerException {
        return travelOffice.assign(surname, description);
    }
}
