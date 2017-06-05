package org.mybatisstudy.mapper;

import org.mybatisstudy.vo.User;

import java.util.List;

public interface UserMapper {
    User findUserById(int id);
    List<User> selectUserByArray(Object[] userList);
}

