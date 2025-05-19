package com.learning.planr.Repositories;

import com.learning.planr.Entities.User;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);


    @Override
    <S extends User> List<S> findAll(Example<S> example);

    @Override
    <S extends User> Optional<S> findOne(Example<S> example);
}
