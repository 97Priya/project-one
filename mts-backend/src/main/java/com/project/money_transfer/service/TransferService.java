package com.project.money_transfer.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public interface TransferService {

   public TransferStatus transfer(double amt,int toAcc,int fromAcc);
}
