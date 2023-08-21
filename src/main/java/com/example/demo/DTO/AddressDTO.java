package com.example.demo.DTO;

import com.example.demo.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private Long Id;
    private String thana;
    private String postoffice;
    private String district;
    private String division;
    private String country;

    public static AddressDTO formAddress(Address address) {
        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setThana(address.getThana());
        dto.setPostoffice(address.getPostoffice());
        dto.setDistrict(address.getDistrict());
        dto.setDivision(address.getDivision());
        dto.setCountry(address.getCountry());

        return dto;
    }
}
