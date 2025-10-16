package org.example;

import java.util.Scanner;

public class Main {
    private final static Account[] accounts = new Account[10];
    private static void showAccountSummary(Account account) {
        account.displayAccountSummary();
    }
    public static void main(String[] args) {
        demonstrateTransactions();
        initializeAccounts();
        runBankApplication();
    }

    private static void demonstrateTransactions() {
        System.out.println("ДЕМОНСТРАЦИЯ РАБОТЫ С ТРАНЗАКЦИЯМИ");
        System.out.println("=".repeat(60));

        Account account = new Account("Семен", 1233, 1100, 7.5);

        account.deposit(500);
        account.deposit(600);
        account.deposit(700);
        account.withdraw(700);
        account.withdraw(600);
        account.withdraw(100);

        account.displayAccountSummary();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("Всего транзакций: " + account.getTransactionCount());
        System.out.println("=".repeat(60) + "\n\n");
    }

    private static void initializeAccounts() {
        for (int i = 0; i < 10; i++) {
            accounts[i] = new Account("Клиент " + i, i, 10000);
            accounts[i].setAnnualInterestRate(5.0 + i * 0.5);
        }
        System.out.println("Инициализировано " + accounts.length + " банковских счетов с транзакциями");
    }

    private static void runBankApplication() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int accountId = getValidAccountId(scanner);

            if (accountId == -1) {
                System.out.println("Выход из программы.");
                break;
            }

            displayMainMenu(scanner, accountId);
        }
        scanner.close();
    }

    private static int getValidAccountId(Scanner scanner) {
        int accountId;

        while (true) {
            System.out.print("\nВведите ID (0-9) или -1 для выхода: ");

            if (scanner.hasNextInt()) {
                accountId = scanner.nextInt();

                if (accountId == -1) {
                    return -1;
                }

                if (accountId >= 0 && accountId < 10) {
                    return accountId;
                } else {
                    System.out.println("❌ Неверный ID! Пожалуйста, введите число от 0 до 9.");
                }
            } else {
                System.out.println("❌ Неверный ввод! Пожалуйста, введите число.");
                scanner.next();
            }
        }
    }

    private static void displayMainMenu(Scanner scanner, int accountId) {
        Account currentAccount = accounts[accountId];
        boolean inMenu = true;

        System.out.println("Открыто главное меню для счета ID: " + accountId);

        while (inMenu) {
            System.out.println("\n" + "═".repeat(50));
            System.out.println("ОСНОВНОЕ МЕНЮ - (ID: " + accountId + ")");
            System.out.println("═".repeat(50));
            System.out.println("1. Проверить баланс счета");
            System.out.println("2. Снять со счета");
            System.out.println("3. Положить на счет");
            System.out.println("4. Показать историю транзакций");
            System.out.println("5. Показать сводку по счету");
            System.out.println("6. Выйти в главное меню");
            System.out.println("═".repeat(50));
            System.out.print("Введите пункт меню: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        checkBalance(currentAccount);
                        break;
                    case 2:
                        withdrawMoney(scanner, currentAccount);
                        break;
                    case 3:
                        depositMoney(scanner, currentAccount);
                        break;
                    case 4:
                        showTransactionHistory(currentAccount);
                        break;
                    case 5:
                        showAccountSummary(currentAccount);
                        break;
                    case 6:
                        inMenu = false;
                        System.out.println("Возврат в главное меню...");
                        System.out.println("Пользователь вышел из меню счета ID: " + accountId);
                        break;
                    default:
                        System.out.println("❌ Неверный пункт меню! Пожалуйста, выберите 1-6.");
                }
            } else {
                System.out.println("❌ Неверный ввод! Пожалуйста, введите число.");
                scanner.next();
            }
        }
    }

    private static void checkBalance(Account account) {
        System.out.printf("\nБаланс равен: %.2f руб.\n", account.getBalance());
        System.out.printf("Ежемесячный процент: %.2f руб.\n", account.getMonthlyInterest());
        System.out.println("Проверен баланс счета ID: " + account.getId() + " = " + account.getBalance());
    }

    private static void withdrawMoney(Scanner scanner, Account account) {
        System.out.print("Введите сумму для снятия со счета: ");

        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();

            if (amount > 0) {
                account.withdraw(amount);
                System.out.println(String.format("Снятие со счета ID %d: %.2f руб.",
                        account.getId(), amount));
            } else {
                System.out.println("❌ Сумма должна быть положительной!");
            }
        } else {
            System.out.println("❌ Неверный ввод суммы!");
            scanner.next();
        }
    }

    private static void depositMoney(Scanner scanner, Account account) {
        System.out.print("Введите сумму для пополнения счета: ");

        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();

            if (amount > 0) {
                account.deposit(amount);
                System.out.println(String.format("Пополнение счета ID %d: %.2f руб.",
                        account.getId(), amount));
            } else {
                System.out.println("❌ Сумма должна быть положительной!");
            }
        } else {
            System.out.println("❌ Неверный ввод суммы!");
            scanner.next();
        }
    }

    private static void showTransactionHistory(Account account) {
        System.out.println("\nИСТОРИЯ ТРАНЗАКЦИЙ ДЛЯ " + account.getId());
        System.out.println("-".repeat(80));

        if (account.getTransactionCount() == 0) {
            System.out.println("Транзакции отсутствуют.");
        }
        else System.out.println("Всего транзакций: " + account.getTransactionCount());
        }
    }


