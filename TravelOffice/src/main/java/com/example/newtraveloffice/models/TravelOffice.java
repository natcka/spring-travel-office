package com.example.newtraveloffice.models;

import com.example.newtraveloffice.exceptions.NoSuchCustomerException;
import com.example.newtraveloffice.exceptions.NoSuchTripException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

@Repository
public class TravelOffice {

    public HashSet<Customer> setOfCustomers = new HashSet<Customer>();
    public HashMap<String, Trip> mapOfTrips = new HashMap<String, Trip>();

    public Customer addCustomer(Customer newCustomer) {
        try {
            setOfCustomers.add(newCustomer);
            return newCustomer;
        } catch (Exception ex) {
            return null;
        }
    }

    public void removeCustomer(String name, String surname) throws NoSuchCustomerException {
        if (!setOfCustomers.removeIf(c -> c.getName().equals(name) && c.getSurname().equals(surname))) {
            throw new NoSuchCustomerException();
        }
    }

    public Customer findCustomerBySurname(String surname) throws NoSuchCustomerException {
        Customer result = null;
        for (Iterator<Customer> iterator = setOfCustomers.iterator(); iterator.hasNext();) {
            Customer customer = iterator.next();
            if (customer.getSurname().equals(surname)) {
                result = customer;
            }
        }
        if (result != null) {
            return result;
        } else {
            throw new NoSuchCustomerException();
        }
    }

    public void getAllCustomers() {
        setOfCustomers.forEach(c -> System.out.println(c.toString()));
    }

    public void assign(String surname, String destination) throws NoSuchCustomerException, NoSuchTripException {
        Customer customerByName = findCustomerBySurname(surname);
        Trip tripByDestination = findTripByDestination(destination);

        try {
            customerByName.assignTrip(tripByDestination);
        } catch (Exception ex) {

        }
    }

    public Trip addTrip(String key, Trip newTrip) {
        mapOfTrips.put(key, newTrip);
        return newTrip;
    }

    public void removeTrip(String key) throws NoSuchTripException {
        if (mapOfTrips.containsKey(key)) {
            mapOfTrips.remove(key);
        } else {
            throw new NoSuchTripException();
        }
    }

    public Trip findTripByDestination(String destination) throws NoSuchTripException {
        Trip result = null;
        if (mapOfTrips.get(destination) != null) {
            result = mapOfTrips.get(destination);
        } else {
            throw new NoSuchTripException();
        }
        return result;
    }

    public void getAllTrips() {
        mapOfTrips.forEach((k, v) -> System.out.println(mapOfTrips.get(k).toString()));
    }

    public HashSet<Customer> getSetOfCustomers() {
        return setOfCustomers;
    }

    public void setSetOfCustomers(HashSet<Customer> setOfCustomers) {
        this.setOfCustomers = setOfCustomers;
    }

    public HashMap<String, Trip> getMapOfTrips() {
        return mapOfTrips;
    }

    public void setMapOfTrips(HashMap<String, Trip> mapOfTrips) {
        this.mapOfTrips = mapOfTrips;
    }
}
