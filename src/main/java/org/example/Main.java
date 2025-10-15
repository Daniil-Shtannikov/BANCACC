package org.example;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    private static Account[] accounts = new Account[10];
    private static Scanner scanner = new Scanner(System.in);

    static {
        for (int i = 0; i < 10; i++) {
            accounts[i] = new Account(i, 10000);
        }
    }
    public static void main(String[] args) {
        System.out.println("=== БАНКОВСКОЕ ПРИЛОЖЕНИЕ ===");

        while (true) {
            int accountId = getValidAccountId();
            showMainMenu(accountId);
        }
    }
    private static int getValidAccountId() {
        int id = -1;

        while (true) {
            System.out.print("\nВведите ID счета (0-9): ");

            try {
                id = Integer.parseInt(scanner.nextLine());

                if (id >= 0 && id <= 9) {
                    System.out.println("Добро пожаловать! Счет №" + id);
                    break;
                } else {
                    System.out.println("Ошибка: ID должен быть от 0 до 9");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число от 0 до 9");
            }
        }

        return id;
    }

    private static void showMainMenu(int accountId) {
        Account currentAccount = accounts[accountId];

        while (true) {
            System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
            System.out.println("1. Просмотр баланса");
            System.out.println("2. Снять деньги");
            System.out.println("3. Внести деньги");
            System.out.println("4. Выйти из меню");
            System.out.print("Выберите пункт меню (1-4): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showBalance(currentAccount);
                    break;
                case "2":
                    performWithdrawal(currentAccount);
                    break;
                case "3":
                    performDeposit(currentAccount);
                    break;
                case "4":
                    System.out.println("Выход из меню счета №" + accountId);
                    return;
                default:
                    System.out.println("Ошибка: выберите пункт от 1 до 4");
            }
        }
    }

    private static void showBalance(Account account) {
        System.out.println("\n=== ТЕКУЩИЙ БАЛАНС ===");
        System.out.printf("Баланс счета: %.2f руб.\n", account.getBalance());
        System.out.println("Дата создания: " +
                new SimpleDateFormat("dd.MM.yyyy").format(account.getDateCreated()));
    }


    private static void performWithdrawal(Account account) {
        System.out.println("\n=== СНЯТИЕ ДЕНЕГ ===");
        System.out.printf("Текущий баланс: %.2f руб.\n", account.getBalance());
        System.out.print("Введите сумму для снятия: ");

        try {
            double amount = Double.parseDouble(scanner.nextLine());
            account.withdraw(amount);
            System.out.printf("Новый баланс: %.2f руб.\n", account.getBalance());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите корректную сумму");
        }
    }

    private static void performDeposit(Account account) {
        System.out.println("\n=== ВНЕСЕНИЕ ДЕНЕГ ===");
        System.out.printf("Текущий баланс: %.2f руб.\n", account.getBalance());
        System.out.print("Введите сумму для внесения: ");

        try {
            double amount = Double.parseDouble(scanner.nextLine());
            account.deposit(amount);
            System.out.printf("Новый баланс: %.2f руб.\n", account.getBalance());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите корректную сумму");
        }
    }

}