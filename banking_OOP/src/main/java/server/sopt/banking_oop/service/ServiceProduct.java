package server.sopt.banking_oop.service;

import lombok.*;
import server.sopt.banking_oop.object.Bank;
import server.sopt.banking_oop.object.Person;

import java.io.IOException;

@Getter
@Setter
//@NoArgsConstructor
public abstract  class ServiceProduct {
    public abstract void deposit(Person person,Double money) throws IOException;

    public abstract void savings(Person person, Double money) throws IOException;
//    public abstract
    public abstract void withDraw(Person person, Double money) throws IOException;

    public abstract void bankTransfer(Person person, Double money) throws IOException;

}
