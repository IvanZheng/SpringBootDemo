package com.demo.application.dao;

import com.demo.domain.models.Card;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardMapper {
    List<Card> findCardsByUserId(String userId);
}
