package com.example.demo.services;

import com.example.demo.DTO.BankDetailsDTO;
import com.example.demo.Repository.BankDetailsRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.BankDetails;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankDetailsServices {
    private final BankDetailsRepository detailsRepository;
    private final UserRepository userRepository;

    public BankDetailsServices(BankDetailsRepository detailsRepository, UserRepository userRepository) {
        this.detailsRepository = detailsRepository;
        this.userRepository = userRepository;
    }
    public BankDetails save(BankDetailsDTO detailsDTO){
        BankDetails bankDetails = new BankDetails();
        bankDetails.setCustomerName(detailsDTO.getCustomerName());
        bankDetails.setAccountNumber(detailsDTO.getAccountNumber());
        bankDetails.setBankName(detailsDTO.getBankName());
        bankDetails.setBranchName(detailsDTO.getBranchName());
        Optional<User> user = userRepository.findById(detailsDTO.getUserId());
        user.ifPresent(bankDetails::setUser);
        return detailsRepository.save(bankDetails);
    }


    public BankDetails getBankDetailsbyId(Long id) {
        Optional<BankDetails> bankDetails = detailsRepository.findById(id);
        if (bankDetails.isPresent()){
            return bankDetails.get();
        }
        return new BankDetails();
    }

    public List<BankDetails> getAllBankAccount() {
        return detailsRepository.findAll();
    }
}
