package com.blogapp.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.app.entitys.User;
import com.blogapp.app.exceptions.resourceNotFoundException;
import com.blogapp.app.payloads.UserDto;
import com.blogapp.app.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	public UserDto createUser(UserDto userDto) {
		User user=UserDtoToUser(userDto);
		User SavedUser=userRepository.save(user);
		return UserToUserDto(SavedUser);
	}
	
	public UserDto getUserById(Integer userId ) {
		User user=userRepository.findById(userId)
				.orElseThrow(()-> new resourceNotFoundException("user","Id",userId));
		return UserToUserDto(user);
	}
	
	public UserDto updateUser(UserDto userDto,Integer userId ) {
		User user=userRepository.findById(userId).get();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser=userRepository.save(user);
		return UserToUserDto(updatedUser);
	}
	
	public List<UserDto> findAllUsers(){
		List<User> users = userRepository.findAll();
	    List<UserDto> collect=users.stream().map(user->UserToUserDto(user)).collect(Collectors.toList());
		return collect;
	}
	
	public void deleteUser(Integer userId) {
		User user=userRepository.findById(userId).get();
		userRepository.delete(user);
	}
	
	public User UserDtoToUser(UserDto userDto) {
		User user =modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user; 
	}
	
	public UserDto UserToUserDto(User user) {
		UserDto userdto =modelMapper.map(user, UserDto.class);
		return userdto; 
	}
}
