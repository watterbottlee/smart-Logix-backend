package com.mover.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long userId;

    @NotEmpty(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email is required")
    private String email;

    @Size(min = 8, max = 24, message = "Password must be between 8 and 24 characters")
    private String password;

    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 digits")
    private String phone;

    private String role;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // Only for response
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // Only for response
    private LocalDateTime updatedAt;
}