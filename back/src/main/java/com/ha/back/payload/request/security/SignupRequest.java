package com.ha.back.payload.request.security;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Map;
import java.util.Set;

@Data
public class SignupRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    public SignupRequest() {
    }

    @Builder(builderMethodName = "buildSignup")
    public SignupRequest(String email, String password, String username, Set<String> role){
        setEmail(email);
        setUsername(username);
        setPassword(password);
        setRole(role);
    }


    public SignupRequest(Map<String, String> build){
        if(build.containsKey("name")) setUsername(build.get("name"));
    }

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

}
