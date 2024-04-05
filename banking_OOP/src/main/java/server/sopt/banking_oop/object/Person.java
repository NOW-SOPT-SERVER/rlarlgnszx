package server.sopt.banking_oop.object;


import lombok.*;
import org.springframework.validation.annotation.Validated;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {
    //bank, account 연결
    private String name;
    @Builder.Default
    private List<PersonPerBankJoiner> banks = new ArrayList<>();
//    @NonNull
    private Double haveMoney ;
    public void getPersonforBankInfo() {
        int i =0;
        for(PersonPerBankJoiner personPerBankJoiner : banks) {
            System.out.println(i + ". " + personPerBankJoiner.getBank().getName() + " : Account:" + personPerBankJoiner.getAccount() + " , In Account Money : "+ personPerBankJoiner.getSaving());
            i++;
        }
    }
    public boolean ispersonHaveAccount() {
        return !banks.isEmpty();

    }
}
