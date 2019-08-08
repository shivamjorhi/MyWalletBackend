package com.cg.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entities.MyWallet;


public interface MyWalletDAO extends JpaRepository<MyWallet, Integer>{
	

}
