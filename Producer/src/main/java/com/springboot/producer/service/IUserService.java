package com.springboot.producer.service;

import com.springboot.producer.entity.User;
import com.springboot.producer.entity.param.UserQueryParam;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface IUserService {
    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    @Cacheable(value="#id")
    User get(long id);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    long add(User user);

    /**
     * 查询用户
     *
     * @return
     */
    List<User> query(UserQueryParam userQueryParam);

    /**
     * 更新用户信息
     *
     * @param user
     */
    void update(User user);

    /**
     * 根据id删除用户
     *
     * @param id
     */
    void delete(long id);
}