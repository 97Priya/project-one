package com.project.money_transfer.api;

import com.project.money_transfer.entity.Account;
import com.project.money_transfer.entity.Transaction;
import com.project.money_transfer.exception.MyResourceNotFoundException;
import com.project.money_transfer.exception.NoAccountException;
import com.project.money_transfer.repository.AccountRepository;
import com.project.money_transfer.repository.TransactionRepository;
import com.project.money_transfer.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@CrossOrigin(origins = {"*"})
@RestController
public class AccountController {

    private AccountRepository accountRepository;

    private TransactionRepository transactionRepository;

    private TransactionService transactionService;

    public AccountController(AccountRepository accountRepository,TransactionRepository transactionRepository,TransactionService transactionService) {
        this.transactionRepository=transactionRepository;
        this.accountRepository = accountRepository;
        this.transactionService=transactionService;
    }

    @GetMapping("/api/accounts")
    public List<Account> getAll(){
        return accountRepository.findAll();
    }

    @RequestMapping(value = "api/accounts/new",method = RequestMethod.POST)
    public Account  createAcc(@RequestBody Account request){
        Account account=new Account(request.getAccountHolderName(),request.getBalance());
        accountRepository.save(account);
        return  account;
    }

    @GetMapping("/api/accounts/{number}")
    public Account getOne(@PathVariable String number){
        return accountRepository.findById(Integer.parseInt(number)).orElseThrow(()-> new NoAccountException(number));
    }


    @GetMapping("/api/accounts/{number}/txns")
    public List<Transaction> getTxns(@PathVariable String number){
        return accountRepository.findById(Integer.parseInt(number)).get().getTxns();

    }


    @GetMapping("/api/accounts/{number}/txns/{txnId}")
    public Transaction getTxn(@PathVariable int number,@PathVariable int txnId){

       return transactionRepository.findById(txnId).get();

    }

    @GetMapping("/api/{number}/transactions")
    public List<Transaction> findPaginated(@RequestParam("page") int page,
                                           @RequestParam("size") int size,
                                            @PathVariable String number) {
        Account account=accountRepository.findById(Integer.parseInt(number)).get();
        Page<Transaction> resultPage = transactionService.findnTransaction(page,size,account);

        return resultPage.getContent();
    }

    @GetMapping("/api/accounts/{acc_num}/txns/date/{date}")
    public List<Transaction> findTxrByDate(@RequestParam("page") int page,
                                           @RequestParam("size") int size,
                                           @PathVariable String acc_num,@PathVariable String date) throws ParseException {
       Account account=accountRepository.findById(Integer.parseInt(acc_num)).get();
        String startDate=date+" 00:00:01";
        String endDate=date+" 23:59:59";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date1 = format.parse(startDate);
        Date date2=format.parse(endDate);
        Page<Transaction> resultPage = transactionService.getTransactionOfDate(account,date1,date2,page,size);

       // Page<Transaction> resultPage = transactionService.getTransactionOfDate(account,txrDate,page,size);

        resultPage.getTotalPages();

        return resultPage.getContent();
    }
}
