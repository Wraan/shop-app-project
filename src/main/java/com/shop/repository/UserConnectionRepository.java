package com.shop.repository;

import com.shop.model.UserConnection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConnectionRepository extends CrudRepository<UserConnection, Long> {

}
