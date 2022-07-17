package com.ha.back.payload.request.security;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
