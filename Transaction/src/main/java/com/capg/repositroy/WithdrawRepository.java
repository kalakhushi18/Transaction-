package com.capg.repositroy;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capg.entity.Withdraw;


@Repository
public interface WithdrawRepository extends CrudRepository<Withdraw, Integer> {

}
