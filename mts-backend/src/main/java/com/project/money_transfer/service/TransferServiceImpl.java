package com.project.money_transfer.service;

import com.project.money_transfer.entity.Account;
import com.project.money_transfer.entity.Transaction;
import com.project.money_transfer.entity.TxnType;
import com.project.money_transfer.exception.BalanceException;
import com.project.money_transfer.exception.NoAccountException;
import com.project.money_transfer.repository.AccountRepository;
import com.project.money_transfer.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class TransferServiceImpl implements TransferService {

    private AccountRepository accountRepository;

    private TransactionRepository transactionRepository;

    @Autowired
    public TransferServiceImpl(AccountRepository accountRepository,TransactionRepository transactionRepository) {
        this.transactionRepository=transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    @Override
    public TransferStatus transfer(double amount, int toAccNum, int fromAccNum) {
        Optional<Account> optionalFromAccount= accountRepository.findById(fromAccNum);
        Account fromAccount= optionalFromAccount.orElseThrow(()->new NoAccountException(""+fromAccNum));

        Optional<Account> optionalToAccount= accountRepository.findById(toAccNum);
        Account toAccount= optionalToAccount.orElseThrow(()->new NoAccountException(""+toAccNum));

        if(fromAccount.getBalance()<amount)
            throw new BalanceException("balance : "+fromAccount.getBalance());

        fromAccount.setBalance(fromAccount.getBalance()-amount);
        toAccount.setBalance(toAccount.getBalance()+amount);

        // insert rows into TXN_HISTORY table
        transactionRepository.save(new Transaction(TxnType.CREDIT,amount,new Date(),toAccount));

        transactionRepository.save(new Transaction(TxnType.DEBIT,amount,new Date(),fromAccount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        return new TransferStatus(0,Status.SUCESS);
    }
}
