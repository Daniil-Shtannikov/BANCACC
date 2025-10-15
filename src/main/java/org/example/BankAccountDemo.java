package org.example;

public class BankAccountDemo {
        public static void main(String[] args) {
            Account account = new Account(1155, 300000);
            account.setAnnualInterestRate(6.5);

            System.out.println("Создан новый счет: " +
                    "\nID=" + account.getId() +
                    ", начальный баланс=" + account.getBalance());

            account.withdraw(16500);
            account.deposit(50000);

            account.deposit(15000);
            account.withdraw(25000);

            account.displayAccountSummary();

            System.out.println("\nСтатистика:");
            System.out.println("Текущий баланс: " + account.getBalance() + " руб.");
            System.out.println("Ежемесячный процент: " + account.getMonthlyInterest() + " руб.");
        }

}
