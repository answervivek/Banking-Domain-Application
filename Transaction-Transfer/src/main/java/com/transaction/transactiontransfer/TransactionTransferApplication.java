package com.transaction.transactiontransfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com"})
public class TransactionTransferApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionTransferApplication.class, args);
	}

}
