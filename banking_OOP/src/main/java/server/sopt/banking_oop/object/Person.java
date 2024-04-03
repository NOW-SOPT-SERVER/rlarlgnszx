package server.sopt.banking_oop.object;


import lombok.*;
import org.springframework.validation.annotation.Validated;


import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Person {
    //    private final Bank bank;
    private List<Bank> banks;
    @NonNull
    private Double haveMoney;

}
