package com.capg.repositroy;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capg.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>
{
  Account findByaccountsNumber(BigInteger id);

}
	