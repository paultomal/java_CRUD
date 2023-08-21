package com.example.demo.DTO;

import com.example.demo.entity.Address;
import com.example.demo.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
public class UserDTO {
    private Long id;
    @NotEmpty(message = "Name must not be null")
    private String name;
    private Integer age;
    @Email
    private String email;
    @Pattern(regexp = " (^([+]{1}[8]{2}|0088)?(01){1}[3-9]{1}\\d{8})$",message = "Enter a bd phone number")
    private String phone;
    @NotEmpty
    private String password;
    private AddressDTO presentAddress;
    private AddressDTO premanentAddress;




    public static UserDTO form(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setAge(user.getAge());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setPremanentAddress(AddressDTO.formAddress(user.getPremanentAddress()));
        dto.setPresentAddress(AddressDTO.formAddress(user.getPresentAddress()));

        return dto;
    }
}
