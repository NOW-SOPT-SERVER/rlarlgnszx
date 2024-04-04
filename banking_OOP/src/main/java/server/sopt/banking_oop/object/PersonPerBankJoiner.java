package server.sopt.banking_oop.object;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PersonPerBankJoiner {
    private final Person person;
    private final Bank bank;
//    계좌
    private final int account;
    //    계쫘에 얼마있는지
    private Double saving;

    public PersonPerBankJoiner(Person person, Bank bank) {
        this.person = person;
        this.bank = bank;
        this.account = (person.hashCode() + bank.hashCode());
    }
}
