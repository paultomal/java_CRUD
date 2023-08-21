package com.example.demo.services;

import com.example.demo.DTO.AddressDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Repository.AddressRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.Address;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    private final UserRepository repository;

    private final AddressRepository addressRepository;

    public UserServices(UserRepository repository, AddressRepository addressRepository) {
        this.repository = repository;
        this.addressRepository = addressRepository;
    }

    public User save(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setAge(dto.getAge());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword());

        AddressDTO presentAddress = dto.getPresentAddress();
        Address preAddress = formAddress(presentAddress);

        preAddress = addressRepository.save(preAddress);

        user.setPresentAddress(preAddress);

        AddressDTO permanentAddress = dto.getPremanentAddress();

        Address parAddress = formAddress(permanentAddress);

        parAddress = addressRepository.save(parAddress);

        user.setPremanentAddress(parAddress);

        return repository.save(user);
    }

    private Address formAddress(AddressDTO dto) {
        Address parAddress = new Address();
        parAddress.setThana(dto.getThana());
        parAddress.setPostoffice(dto.getPostoffice());
        parAddress.setDistrict(dto.getDistrict());
        parAddress.setDivision(dto.getDivision());
        parAddress.setCountry(dto.getCountry());

        return parAddress;
    }

    public User getUserById(Long id) {
        Optional<User> user = repository.findById(id);
        if(user.isPresent()) {
            return  user.get();
        }
        return new User();
    }

    public User updateUser(Long id, UserDTO dto) {
        Optional<User> user = repository.findById(id);
        if(user.isPresent()){
            User user1 = user.get();
            user1.setName(dto.getName());
            user1.setAge(dto.getAge());
            user1.setEmail(dto.getEmail());
            user1.setPhone(dto.getPhone());
            return repository.save(user1);
        }
        return new User();
    }

    public List<User> getAllUser() {
        return repository.findAll();
    }
}
