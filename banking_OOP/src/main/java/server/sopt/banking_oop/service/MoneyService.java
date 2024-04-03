package server.sopt.banking_oop.service;

import lombok.*;
import server.sopt.banking_oop.object.Bank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoneyService extends Bank {

    //    private MoneyDto moneyDto;

    @Override
    public Double deposit(Double moneyDto, Bank bank) {
        return null;
    }

    @Override
    public Double savings() {

        return null;
    }
}
