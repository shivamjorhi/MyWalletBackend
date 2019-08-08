package com.cg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.entities.MyWallet;
import com.cg.inputFormats.DepositAndWithdraw;
import com.cg.inputFormats.TranserFundFormat;
import com.cg.services.MyWalletService;

@CrossOrigin
@RestController
@RequestMapping("/wallet")
public class MyWalletController {

    @Autowired MyWalletService service;
    
    @GetMapping(value="/")	
    public List<MyWallet> getAll() {
        return service.getAll();
    }
    
    @GetMapping(value="/accid/{accId}")	
    public MyWallet findbycode(@PathVariable Integer accId) {
        return service.byCode(accId);
    }

    
    @PostMapping(value="/new",consumes= {"application/json"})
    public MyWallet save(@RequestBody MyWallet wallet) {
    	return service.create(wallet);
         
    }
    
    @PutMapping(value="/update/{accId}",consumes= {"application/json"})
    public String update(@PathVariable Integer accId,@RequestBody MyWallet wallet) {
        service.update(accId,wallet);
        return "Account updated";
    }  
    
    @DeleteMapping(value="/delete/{accId}", consumes= {"application/json"})
    public String delete(@PathVariable Integer accId) {
    	MyWallet wallet=service.byCode(accId);
    	service.deleteByCode(wallet);   	
    	return "Account deleted";   	
    }
    
    @PutMapping(value="/deposite/{accId}",consumes= {"application/json"})
    public String deposit(@RequestBody DepositAndWithdraw input) {
		MyWallet a=service.byCode(input.getId());
		double amount = input.getAmount();
		return service.deposit(a, amount);
	}
    
    @PutMapping(value="/withdraw/{accId}",consumes= {"application/json"})
    public String withdraw(@RequestBody DepositAndWithdraw input) {
		MyWallet a=service.byCode(input.getId());
		double amount = input.getAmount();
		return service.withdraw(a, amount);
	}
    
    @PutMapping(value="/transfer",consumes= {"application/json"})
public double[] transferMoney(@RequestBody TranserFundFormat input) {
		
		int id1 = input.getId1();
		int id2 = input.getId2();
		double amount = input.getAmount();
		
		MyWallet from = service.byCode(id1);
		MyWallet to = service.byCode(id2);
		
		return service.transfer(from, to, amount);
	}
    
    
    
    
    
}