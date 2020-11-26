package com.pe.shopathome.repository;

import com.pe.shopathome.entity.PaymentAccount;
import com.pe.shopathome.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentAccountRepository  extends JpaRepository<PaymentAccount, Long> {
}
