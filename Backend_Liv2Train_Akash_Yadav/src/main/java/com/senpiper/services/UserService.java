package com.senpiper.services;

import com.senpiper.dtos.LoginUserDto;
import com.senpiper.dtos.UserDto;
import com.senpiper.exceptions.UserException;
import com.senpiper.model.User;


public interface UserService {
	
	public User registerUser(UserDto user)throws UserException;

	public User loginUser(LoginUserDto loginDetails) throws UserException;
	

}
