package com.example.demo.DTO;

import com.example.demo.entity.BankDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankDetailsDTO {
    private Long id;
    private String customerName;
    private Integer accountNumber;
    private String bankName;
    private String branchName;
    private Long userId ;

    public static BankDetailsDTO form(BankDetails bankDetails){
        BankDetailsDTO bankDetailsDTO = new BankDetailsDTO();
        bankDetailsDTO.setId(bankDetails.getId());
        bankDetailsDTO.setCustomerName(bankDetails.getCustomerName());
        bankDetailsDTO.setAccountNumber(bankDetails.getAccountNumber());
        bankDetailsDTO.setBankName(bankDetails.getBankName());
        bankDetailsDTO.setBranchName(bankDetails.getBranchName());
        bankDetailsDTO.setUserId(bankDetails.getId());
        return bankDetailsDTO;
    }
}
