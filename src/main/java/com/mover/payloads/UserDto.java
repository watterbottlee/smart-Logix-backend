package com.mover.payloads;

import jakarta.validation.Valid;
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
    @NotEmpty
    private String name;
    @Email@NotEmpty
    private String email;
    @NotEmpty
    @Size(min=8,max = 24)
    private String password;

    private String phone;
    private String role;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
