package com.nnk.springboot.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class is the DTO class for the User Entity
 * 
 * @author Rémi Deronzier
 */
@Data
@AllArgsConstructor
public class UserDto {

    private Integer id;

    @NotBlank(message = "Username is mandatory")
    private String userName;

    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain at least one upercase letter, one lowercase letter at least one number and one symbol and must be at least 8 characters")
    private String password;

    @NotBlank(message = "FullName is mandatory")
    private String fullName;

    @NotBlank(message = "Role is mandatory")
    private String role;

}
