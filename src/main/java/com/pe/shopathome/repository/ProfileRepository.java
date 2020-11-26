package com.pe.shopathome.repository;

import com.pe.shopathome.entity.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> {
}
