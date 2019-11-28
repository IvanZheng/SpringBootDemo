package com.demo.application.dao;

import com.demo.domain.models.Card;
import com.demo.domain.models.User;
import com.demo.infrastructure.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserMapper {
    User selectUser(String id);
    List<User> selectUsers(Page page);

    void insertUser(User user);
    void insertCards(@Param("cards") List<Card> cards, @Param("userId") String userId);

    @Transactional
    default void addUser(User user){
        insertUser(user);
        insertCards(user.getCards(), user.getId());
    }

    boolean updateUser(User user);
}
