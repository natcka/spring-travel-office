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

    public int getCustomerCount() {
        return setOfCustomers.size();
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

    public Trip addTrip(String key, Trip newTrip) {
        mapOfTrips.put(key, newTrip);
        return newTrip;
    }

    public Customer findCustomerByName(String name) throws NoSuchCustomerException {
        Customer result = null;
        for (Iterator<Customer> iterator = setOfCustomers.iterator(); iterator.hasNext();) {
            Customer customer = iterator.next();
            if (customer.getSurname().equals(name)) {
                result = customer;
            }
        }
        if (result != null) {
            return result;
        } else {
            throw new NoSuchCustomerException();
        }
    }

    public boolean removeCustomer(String surname, String name) throws NoSuchCustomerException {
        if (!setOfCustomers.removeIf(c -> c.getName().equals(name) && c.getSurname().equals(surname))) {
            throw new NoSuchCustomerException();
        }
        return true;
    }

    public void getAllCustomers() {
        setOfCustomers.forEach(c -> System.out.println(c.toString()));
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

    public boolean removeTrip(String key) throws NoSuchTripException {
        if (mapOfTrips.containsKey(key)) {
            mapOfTrips.remove(key);
            return true;
        } else {
            throw new NoSuchTripException();
        }
    }

    public void getAllTrips() {
        mapOfTrips.forEach((k, v) -> System.out.println(mapOfTrips.get(k).toString()));
    }

    public boolean assign(String surname, String destination) throws NoSuchCustomerException, NoSuchTripException {
        Customer customerByName = findCustomerByName(surname);
        Trip tripByDestination = findTripByDestination(destination);

        try {
            customerByName.assignTrip(tripByDestination);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
