package com.project.money_transfer.service;

import com.project.money_transfer.entity.Account;
import com.project.money_transfer.entity.Transaction;
import com.project.money_transfer.repository.TransactionRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private  TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Page<Transaction> findnTransaction(int page, int size, Account account){

        Pageable pageable = PageRequest.of(page,size);

        return  transactionRepository.findAllByAccount(account,pageable);
    }

    public Page<Transaction> getTransactionOfDate(Account account, Date date,Date endDate, int page, int size){

        Pageable pageable = PageRequest.of(page,size);

        return transactionRepository.findAllByAccountAndDateBetween(account,date,endDate,pageable);

      // return transactionRepository.getTransactionsByDate(account,date,endDate,pageable);

      // return transactionRepository.findAllByAccountAndDate(account,date,pageable);
    }


}
