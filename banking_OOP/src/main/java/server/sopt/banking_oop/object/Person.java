package server.sopt.banking_oop.object;


import lombok.*;
import org.springframework.validation.annotation.Validated;


import java.util.List;
import java.util.Map;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Person {
    //bank, account 연결
    private String name;
    private List<PersonPerBankJoiner> banks;
    @NonNull
    private Double haveMoney;
}
