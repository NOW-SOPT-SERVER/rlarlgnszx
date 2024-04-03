package server.sopt.banking_oop.object;

import lombok.*;
import server.sopt.banking_oop.service.MoneyService;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public abstract class Bank {
    //은행에 고객들
    public List<Person> customers ;
    //은행이름?
    public String name;
    //이자율
    public Double interestRate;
    //적금율?
    public Double savingInterestRate ;
    public MoneyService BankService;
    //    private MoneyDto moneyDto;
    public abstract Double deposit(Double moneyDto, Bank bank);

    public abstract Double savings();

}
