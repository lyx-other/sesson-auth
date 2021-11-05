package com.lyx.servide;

import com.lyx.model.AuthenticationRequest;
import com.lyx.model.UserDto;

public interface AuthenticationService
{
    UserDto authentication(AuthenticationRequest authenticationRequest);
}
