package com.mover.payloads.apirequests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransporterRequest {
    private String name;
    private String email;
    private String password;
    private String phone;

}
