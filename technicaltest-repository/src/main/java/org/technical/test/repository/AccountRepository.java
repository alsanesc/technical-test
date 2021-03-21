package org.technical.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.technical.test.model.AccountDTO;

public interface AccountRepository extends JpaRepository<AccountDTO, Integer> {
}
