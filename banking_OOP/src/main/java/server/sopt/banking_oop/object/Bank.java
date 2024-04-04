package server.sopt.banking_oop.object;

import lombok.*;
import server.sopt.banking_oop.service.ServiceProduct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bank extends ServiceProduct{
    //은행에 고객들,
    private Set<Person> customers;
    //은행이름?
    private Map<Person,List<PersonPerBankJoiner>> db;

    private String name;
    //이자율
    private Double interestRate;
    //적금율?
    private Double savingInterestRate ;

    @Override
//    예금
    public void deposit(Person person,Double money) throws IOException {
        registerPerson(person);
        if(checkMoney(person,money)){
            return;
        }
        PersonPerBankJoiner personPerBankJoiner = findPersonforAccount(person);
        personPerBankJoiner.setSaving(personPerBankJoiner.getSaving() + money);
    }
    @Override
    public void savings(Person person,Double money) {
        registerPerson(person);
        if(checkMoney(person,money)){
            return;
        }
    }
// 출금
    @Override
    public void withDraw(Person person, Double money) throws IOException {
        registerPerson(person);
        PersonPerBankJoiner personPerBankJoiner = findPersonforAccount(person);
        if(checkMoneyFromAccount(person,money,personPerBankJoiner)){
            return;
        }
        personPerBankJoiner.setSaving(personPerBankJoiner.getSaving() - money);
        return ;
    }
    @Override
    public void bankTransfer(Person person, Double money) throws IOException {

    }
    private boolean checkMoneyFromAccount(Person person, Double money, PersonPerBankJoiner personPerBankJoiner) throws IOException {
        return personPerBankJoiner.getSaving() < person.getHaveMoney();
    }

    public void registePerson2Bank(Person person) {
        customers.add(person);
        PersonPerBankJoiner personPerBankJoiner = new PersonPerBankJoiner(person, this);
        personPerBankJoiner.setSaving(.0);
        List<PersonPerBankJoiner> personPerBankJoiners = person.getBanks();
        personPerBankJoiners.add(personPerBankJoiner);
        person.setBanks(personPerBankJoiners);
        db.put(person, List.of(personPerBankJoiner));
    }

        private boolean checkMoney(Person person, Double money) {
        if(person.getHaveMoney()< money) {
            System.out.println("Not enough Money....");
            return true;
        }
        return false;
    }

    private void registerPerson(Person person) {
        if(!customers.contains(person))
            registePerson2Bank(person);
    }

    private PersonPerBankJoiner findPersonforAccount(Person person) throws IOException {
        List<PersonPerBankJoiner> personPerBankJoiners = db.get(person);
        if (showAccount(personPerBankJoiners)) return personPerBankJoiners.get(0);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int idx = Integer.parseInt(br.readLine());
        return personPerBankJoiners.get(idx);
    }
    private static boolean showAccount(List<PersonPerBankJoiner> personPerBankJoiners) {
        if(personPerBankJoiners.size()==1) {
            System.out.println(personPerBankJoiners.get(0).toString());
            return true;
        }
        int i=0;
        System.out.println("===============계좌 선택 ===============");
        for (PersonPerBankJoiner account : personPerBankJoiners) {
            System.out.println(i+" : "+ account.getAccount());i++;
        }
        return false;
    }
}
