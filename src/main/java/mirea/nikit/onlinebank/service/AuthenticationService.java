package mirea.nikit.onlinebank.service;


import mirea.nikit.onlinebank.model.dao.request.SignUpRequest;
import mirea.nikit.onlinebank.model.dao.request.SigninRequest;
import mirea.nikit.onlinebank.model.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
