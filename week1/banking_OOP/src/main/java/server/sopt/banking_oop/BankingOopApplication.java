package server.sopt.banking_oop;

import server.sopt.banking_oop.object.Bank;
import server.sopt.banking_oop.object.Person;
import server.sopt.banking_oop.object.PersonPerBankJoiner;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.SortedMap;

//@SpringBootApplication
public class BankingOopApplication {
	static Bank bank1 = Bank.builder()
			.name("kakao")
			.savingInterestRate(0.01)
			.interestRate(0.04)
			.build();

	static Bank bank2 = Bank.builder()
			.name("KB")
			.savingInterestRate(0.03)
			.interestRate(0.07)
			.build();

	static Bank bank3 = Bank.builder()
			.name("KIHOON BANK")
			.savingInterestRate(0.00)
			.interestRate(0.4)
			.build();

	static List <Bank> banks = List.of(bank1, bank2, bank3);
	static Person me = Person.builder()
			.name("ME!")
			.haveMoney(1000000.0)
			.build();

	static List<PersonPerBankJoiner> testFor;

//	계좌 이체 시 테스트용 사람
    static Person otherPerson = Person.builder()
			.name("Other Person")
			.haveMoney(2000000000.0)
			.build();

	static List<Person> persons = List.of(me, otherPerson);
	public static void showBank(){
		int i = 0;
		for (Bank bank : banks) {
			System.out.println(i + " : " + bank.getName());i++;
		}
	}
	public static void showPerson(){
		int i = 0;
		for (Person person : persons) {
			System.out.println(i + " : " + person.getName());i++;
		}
	}
	private static Bank selectBank(int idx){
		return banks.get(idx);
	}

//	DTO로 계좌가 있는사람만 뽑아주고 싶은뎅흐음 ...어떻게 사용해야 할지 모르겠넹
	private static Person selectPerson(int idx) {
		return persons.get(idx);
	}
	private static PersonPerBankJoiner selectPerBankJoiner(Person person, int i) {
		return person.getBanks().get(i);
	}

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		List<PersonPerBankJoiner> test = List.of(
				new PersonPerBankJoiner(otherPerson, bank1), new PersonPerBankJoiner(otherPerson, bank2), new PersonPerBankJoiner(otherPerson, bank3));
		otherPerson.setBanks(test);

		while (true) {
			System.out.println("==================번호를 입력하세요 (종료하려면 0을 입력)=========================== ");
			System.out.println("1: 예금 , 2: 적금  , 3:출금  4: 송금 , 5: 내 정보 보기 , 0: 끝내기 ");
			int number = scanner.nextInt();
			Bank bank;
			Person person;
			PersonPerBankJoiner personPerBankJoiner;
			double money ;
			int idx;
			switch (number) {
                case 1://예금
                    System.out.println("은행 선택");
                    showBank();
                    bank = selectBank(scanner.nextInt());
                    System.out.println("DETAIL BANK INFO : ");
                    System.out.println(bank);
                    //bank와 person의 연관 작동
                    System.out.println("얼마를 예금하시겠습니까? : ");
                    money = scanner.nextDouble();
                    bank.deposit(me, money);
                    System.out.println("남은 돈 : " + me.getHaveMoney());
                    break;
                case 2:// 적금
                    System.out.println("은행 선택");
                    showBank();
                    bank = selectBank(scanner.nextInt());
                    System.out.println("DETAIL BANK INFO : ");
                    System.out.println(bank);
                    //bank와 person의 연관 작동
                    System.out.println("얼마를 적금하시겠습니까? : ");
                    money = scanner.nextDouble();
                    bank.savings(me, money);
                    System.out.println(me.getHaveMoney());

//					selectBank();
                    break;
                case 3://출금
                    System.out.println("출금할 계좌 선택");
					me.getPersonforBankInfo();
                    //bank와 person의 연관 작동
					idx = scanner.nextInt();
					personPerBankJoiner = me.getBanks().get(idx);
                    System.out.println("얼마를 출금 하시겠습니까? : ");
                    money = scanner.nextDouble();
					personPerBankJoiner.getBank().withDraw(personPerBankJoiner, money,me);
                    System.out.println("남은 돈 : " + me.getHaveMoney());
                    break;
                case 4://송금
                    System.out.println("누구에게 보낼까요?");
                    showPerson();
                    person = selectPerson(scanner.nextInt());
                    person.getPersonforBankInfo();
                    if (isPersonHaveAccount(person)) {
                        System.out.println("보낼수 있는 사람이 없습니다.");
                        break;
                    }
                    System.out.println("어떤 계좌로 보낼까요?");
                    personPerBankJoiner = selectPerBankJoiner(person, scanner.nextInt());
					Double throwMoney;
					System.out.println("얼마 보내시겠습니까?");
					throwMoney = scanner.nextDouble();
//					selectBank();
					System.out.println("게좌에서 보낼까요? 0 : NO , 1 : yes");
					int scan = scanner.nextInt();
                    if (scan==1) {
						PersonPerBankJoiner mypersonPerBankJoiner;
						if(me.getBanks().size()==1){
							mypersonPerBankJoiner = me.getBanks().get(0);
						}else{
							System.out.println("게좌 선택 : N");
							me.getPersonforBankInfo();
							idx = scanner.nextInt();
							mypersonPerBankJoiner = me.getBanks().get(idx);
						}
						personPerBankJoiner.getBank().bankTransfer(mypersonPerBankJoiner, throwMoney,personPerBankJoiner);
                    } else {
                        personPerBankJoiner.getBank().bankTransfer(me, throwMoney,personPerBankJoiner);
                    }
                    break;
                case 5://송금
                    System.out.println("============MY INFO====================");
                    System.out.println(me.getName());
                    me.getPersonforBankInfo();
                    System.out.println(me.getHaveMoney());
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    return; // while문을 빠져나가 프로그램을 종료
                default:
                    System.out.println("올바른 번호를 입력하세요.");
                    break;
            }
		}
//		System.out.println(kihoon.hashCode());

	}

	private static boolean isPersonHaveAccount(Person person) {
        return person.getBanks().isEmpty();

    }


}
