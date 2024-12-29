package com.haohmaru.IDaaS.mapper;

import com.haohmaru.IDaaS.dto.request.UserCreationRequest;
import com.haohmaru.IDaaS.dto.request.UserUpdateRequest;
import com.haohmaru.IDaaS.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
