package com.bookmyconsultation.bookmyconsultation.repository;

import com.bookmyconsultation.bookmyconsultation.entity.UserAuthToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface UserAuthTokenRepository extends CrudRepository<UserAuthToken, String> {

    UserAuthToken findByUserEmailId(@NotNull String userId);

    UserAuthToken findByAccessToken(String token);

}
