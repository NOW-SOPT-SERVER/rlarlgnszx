package server.sopt.banking_oop.object;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
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
        this.saving = .0;
    }
    @Override
    public String toString() {
        return
                "person=" + person +
                ", bank=" + bank +
                ", account=" + account +
                ", saving=" + saving
                ;
    }
}
