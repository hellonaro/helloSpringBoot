package com.study.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.study.springboot.dao.ITransaction1Dao;
import com.study.springboot.dao.ITransaction2Dao;

@Service
public class BuyTicketService implements IBuyTicketService{
	
	@Autowired
	ITransaction1Dao transaction1;
	@Autowired
	ITransaction2Dao transaction2;
	
//	@Autowired
//	PlatformTransactionManager transactionManager;
//	@Autowired
//	TransactionDefinition definition;
	@Autowired
	TransactionTemplate transactionTemplate;
	
	@Override
	public int buy(String consumerId, int amount, String error) {
		
		//TransactionStatus status = transactionManager.getTransaction(definition);
		
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				// 커밋, 롤백 자동으로 처리됨
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					transaction1.pay(consumerId, amount);
					if(error.equals("1")) { int n = 10 / 0; } // 의도적 에러 발생
					transaction2.pay(consumerId, amount);
				}
			});
			
			//transactionManager.commit(status);
			return 1;
		} 
		catch(Exception e) {
			System.out.println("[error] " + e);
			System.out.println("[PlatfromTransactionManager] Rollback");
			//transactionManager.rollback(status);
			return 0;
		}
	}
}
