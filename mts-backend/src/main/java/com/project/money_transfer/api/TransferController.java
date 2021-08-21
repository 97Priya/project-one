package com.project.money_transfer.api;

import com.project.money_transfer.exception.BalanceException;
import com.project.money_transfer.service.TransferService;
import com.project.money_transfer.service.TransferStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
public class TransferController {
    private TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping(
            value = "/api/txr",
            consumes = {"application/json","application/xml"},
            produces = {"application/json","application/xml"}
    )
    public ResponseEntity<?> doTxr(@RequestBody TxrRequest request){
        TransferStatus txrStatus= transferService.transfer(request.getAmount(),request.getFromAccNum(), request.getToAccNum());
        TxrResponse response=new TxrResponse();
        response.setMessage(txrStatus.getStatus().name());
        return ResponseEntity.ok().body(response); // 200
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)// 400
    @ExceptionHandler(value = {BalanceException.class})
    public TxrResponse noEnoughBalance(Throwable t){
        TxrResponse response=new TxrResponse();
        response.setMessage("no enough balance, balance="+t.getMessage());
        return response;
    }
}
