package com.capg.repositroy;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capg.entity.Deposit;

@Repository
public interface DepositRepository extends CrudRepository<Deposit, Integer> {

}
