package com.example.demo.Controller;

import com.example.demo.DTO.BankDetailsDTO;
import com.example.demo.entity.BankDetails;
import com.example.demo.services.BankDetailsServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
public class BankDetailsController {
    private final BankDetailsServices bankDetailsServices;

    public BankDetailsController(BankDetailsServices bankDetailsServices) {
        this.bankDetailsServices = bankDetailsServices;
    }

    @PostMapping("/saveBankDetails")
    public ResponseEntity<?> save(@RequestBody BankDetailsDTO detailsDTO){
        BankDetailsDTO detailsDTO1 = BankDetailsDTO.form(bankDetailsServices.save(detailsDTO));
       return new ResponseEntity<>(detailsDTO1, HttpStatus.OK);
    }
    @GetMapping("/GetBankAccount/{id}")
    public ResponseEntity<?> getBankDetailsById(@PathVariable Long id){
        BankDetailsDTO detailsDTO = BankDetailsDTO.form(bankDetailsServices.getBankDetailsbyId(id));
        return new ResponseEntity<>(detailsDTO, HttpStatus.OK);
    }
    @GetMapping("/listOfAllBankAccount")
    public ResponseEntity<?> getAllBankAccount(){
        List<BankDetails> bankDetails = bankDetailsServices.getAllBankAccount();
        List<BankDetailsDTO> bankDetailsDTOList = bankDetails.stream()
                .map(BankDetailsDTO::form)
                .toList();
        return new ResponseEntity<>(bankDetailsDTOList, HttpStatus.OK);
    }
}
