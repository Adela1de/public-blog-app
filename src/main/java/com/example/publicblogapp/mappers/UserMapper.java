package com.example.publicblogapp.mappers;

import com.example.publicblogapp.dtos.user.UserDTO;
import com.example.publicblogapp.model.entities.User;
import com.example.publicblogapp.requests.user.UserPostRequestBody;
import com.example.publicblogapp.requests.user.UserRegisterRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract UserDTO toUserDTO(User user);

    public abstract User toUser(UserPostRequestBody userPostRequestBody);

    public abstract User toUser(UserRegisterRequestBody userRegisterRequestBody);

}
