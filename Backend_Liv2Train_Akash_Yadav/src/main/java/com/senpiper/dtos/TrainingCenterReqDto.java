package com.senpiper.dtos;

import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.senpiper.model.Address;

import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingCenterReqDto {
	
	@NotBlank(message = "Center Name is mandatory")
    @Size(max = 40, message = "Center Name should be less than 40 characters")
    private String centerName;

    @NotBlank(message = "Center Code is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9]{12}$", message = "Center Code should be exactly 12 alphanumeric characters")
    private String centerCode;

    @Valid
    @NotNull(message = "Address is mandatory")
    private Address address;

    @NotNull(message = "Student Capacity cannot be null")
    private Integer studentCapacity;

    @NotNull(message = "Courses Offered list cannot be null")
    private List<String> coursesOffered;

    @Email(message = "Invalid email format")
    private String contactEmail;

    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Phone number should be 10 digits and start with 6, 7, 8, or 9")
    @NotBlank(message = "Contact phone is mandatory")
    private String contactPhone;
}
