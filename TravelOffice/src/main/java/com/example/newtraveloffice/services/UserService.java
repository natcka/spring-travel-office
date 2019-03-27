//package com.example.newtraveloffice.services;
//
//import com.example.newtraveloffice.models.Customer;
//import com.example.newtraveloffice.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//@ComponentScan(basePackages = {"com.example.newtraveloffice.repositories", "com.example.newtraveloffice.models"})
//public class UserService implements IUserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public List<Customer> findAll() {
//        List<Customer> result = new ArrayList<>();
//        List<com.example.newtraveloffice.entities.Customer> all = (List<com.example.newtraveloffice.entities.Customer>) userRepository.findAll();
//        for (com.example.newtraveloffice.entities.Customer c : all) {
//            Customer customer = new Customer();
//            customer.setName(c.getName());
//            customer.setSurname(c.getSurname());
//            result.add(customer);
//        }
//        return result;
//    }
//}
