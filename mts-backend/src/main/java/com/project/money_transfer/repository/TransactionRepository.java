package com.project.money_transfer.repository;

import com.project.money_transfer.entity.Account;
import com.project.money_transfer.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends  JpaRepository<Transaction,Integer> {


     Page<Transaction> findAllByAccount(Account account, Pageable pageable);





//     @Query(value="select * from transactions t where DATE(t.date)=:date and acc_number=:acc_number \n#pageable\n",
//    countQuery = "select count(*) from transactions t where  DATE(t.date)=:date and acc_number=:acc_number",
//    nativeQuery = true)
//     Page<Transaction> getTransactionsByDate(int acc_number,Date date,Pageable pageable);
//.......................................................................................................

     Page<Transaction> findAllByAccountAndDateBetween(Account account,Date startdate,Date endDate,Pageable pageable);

 //////..............................................................................................
 //    Page<Transaction> findAllByAccountAndDate(Account account,Date date,Pageable pageable);

     //...........................................................................................................
//     @Query(value="select * from transactions t where t.date between t.date=:startdate and t.date=:enddate and acc_number=:acc_number \n#pageable\n",
//             countQuery = "select count(*) from transactions t where t.date between t.date=:startdate and t.date=:enddate and acc_number=:acc_number",
//             nativeQuery = true)
//     Page<Transaction> getTransactionsByDate(int acc_number,Date startdate,Date endDate,Pageable pageable);

}
