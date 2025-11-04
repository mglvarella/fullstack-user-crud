package com.user_crud.user_api.repository;
import com.user_crud.user_api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
