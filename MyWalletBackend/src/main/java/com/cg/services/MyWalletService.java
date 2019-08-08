package com.cg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.cg.daos.MyWalletDAO;
import com.cg.entities.MyWallet;



@Service
@Transactional
public class MyWalletService {

    @Autowired private MyWalletDAO dao;
    
   
    @Transactional(readOnly=true)
    public MyWallet byCode(Integer accId) {
    	Optional<MyWallet> wallet = dao.findById(accId);
    	if(wallet.isPresent()) {
            return wallet.get();
        }
        else
            throw new RuntimeException("Account not found!");
    }
   
    @Transactional(propagation = Propagation.REQUIRED)
	public void update(int id, MyWallet account) {
    	MyWallet a = null;
		Optional<MyWallet> p = dao.findById(id);
		if (p.isPresent()) 
			a = p.get();
		else
			System.out.println("Account not found!"); // throwing custom exception if account doesn't exist
		a.setAccNumber(account.getAccNumber());
		a.setAccName(account.getAccName());
		a.setIniBal(account.getIniBal());
	}

       
    public MyWallet create(MyWallet wallet) {
    	
    	dao.save(wallet);
    	return wallet;
    }
    
    public void deleteByCode(MyWallet c) {
    	dao.delete(c);
    }
    
    public List<MyWallet> getAll() {
    	return dao.findAll();
    }
    
    public String deposit(MyWallet ob, double amount) {
		if(amount>0)
		{
			double new_bal=ob.getIniBal()+amount;
			ob.setIniBal(new_bal);
			update(ob.getAccId(),ob);
			return "Account updated Sucessfully";
		}
		else
		{
			return "Account can't be updated";
		}
	}
    
    public String withdraw(MyWallet ob, double amount) {
		if(amount>999)
		{
			double new_bal=ob.getIniBal()-amount;
			ob.setIniBal(new_bal);
			update(ob.getAccId(),ob);
			return "Account updated Sucessfully";
		}
		else
		{
			return "Account can't be updated";
		}
	}
    
    
	@Transactional(propagation = Propagation.REQUIRED)
	public double[] transfer(MyWallet from, MyWallet to, double amount) {
		double[] arr = new double[2];
		if(amount < 0) {
			System.out.println("Invalid withdrawal..try again with positive amount");
			
			arr[0] = from.getIniBal();
			arr[1] = to.getIniBal();
			
		}
		else {
			double new_balance = from.getIniBal() - amount;
			if(new_balance<1000.00) {
				
				System.out.println("Insufficient Balance");
				arr[0] = from.getIniBal();
				arr[1] = to.getIniBal();
				
			}
			else {
				from.setIniBal(new_balance);
				arr[0] = from.getIniBal();
				
				double b2 = to.getIniBal()+amount;
				to.setIniBal(b2);
				
				arr[1] = to.getIniBal();
				
				update(from.getAccId(), from);
				update(to.getAccId(), to);
			}
		}
		return arr;
	}
	
    
}