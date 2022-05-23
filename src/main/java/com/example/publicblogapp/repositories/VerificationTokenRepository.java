package com.example.publicblogapp.repositories;

import com.example.publicblogapp.model.entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends
        JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);

    @Query(value = "select v.token from verification_token v " +
            "where v.user_id = ?",nativeQuery = true)
    String findTokenByUserId(Long userId);
}
