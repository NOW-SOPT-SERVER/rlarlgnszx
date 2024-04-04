package server.sopt.banking_oop;

import server.sopt.banking_oop.object.Bank;
import server.sopt.banking_oop.object.Person;

import java.util.List;
import java.util.Scanner;

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
	Person me = Person.builder()
			.name("ME!")
			.haveMoney(1000000.0)
			.build();

	Person otherPerson = Person.builder()
			.name("Other Person")
			.haveMoney(2000000000.0)
			.build();
	List<Person> persons = List.of(me, otherPerson);
	public static void showBank(){
		int i = 0;
		for (Bank bank : banks) {
			System.out.println(i + " : " + bank.getName());i++;
		}
	}
	private static Bank selectBank(int idx){
		return banks.get(idx);
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("==================번호를 입력하세요 (종료하려면 0을 입력)=========================== ");
			System.out.println("1: 예금 , 2: 적금  , 3:출금  4: 송금 , 0: 끝내기 ");
			int number = scanner.nextInt();
			Bank bank;
			switch (number) {
				case 1:
					System.out.println("예금 선택하셨습니다.");
					showBank();
					bank= selectBank(scanner.nextInt());
					System.out.println("DETAIL BANK INFO : ");
					System.out.println(bank);
					break;
				case 2:
					System.out.println("적금 선택하셨습니다.");
					showBank();
					bank= selectBank(scanner.nextInt());
					System.out.println("DETAIL BANK INFO : ");
					System.out.println(bank);
//					selectBank();
					break;
				case 3:
					System.out.println("출금 선택하셨습니다.");
					showBank();
					bank= selectBank(scanner.nextInt());
					System.out.println("DETAIL BANK INFO : ");
					System.out.println(bank);
					break;
				case 4:
					System.out.println("송금 선택하셨습니다.");

//					selectBank();
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


}
