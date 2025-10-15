package org.example;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {
    private int id = 0;
    private String name = "";
    private double balance = 0;
    private double annualInterestRate = 0;
    private Date dateCreated = new Date();
    private List<Transaction> transactions = new ArrayList<>();

    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
        this.dateCreated = new Date();
        this.transactions.add(new Transaction('O', balance, balance, "Открытие счета"));
    }

    public Account(String name, int id, double balance) {
        this.name = name;
        this.id = id;
        this.balance = balance;
        this.dateCreated = new Date();
        this.transactions.add(new Transaction('O', balance, balance,
                "Открытие счета для " + name));
    }

    public Account(String name, int id, double balance, double annualInterestRate) {
        this.name = name;
        this.id = id;
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
        this.dateCreated = new Date();
        this.transactions.add(new Transaction('O', balance, balance,
                "Открытие счета для " + name + " с процентной ставкой " + annualInterestRate + "%"));
    }

    public double getMonthlyInterest() {
        double monthlyInterestRate = annualInterestRate / 12 / 100;
        double monthlyInterest = balance * monthlyInterestRate;
        return Double.parseDouble(String.format("%.2f", monthlyInterest));
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add(new Transaction('W', amount, balance,
                    "Снятие наличных"));
            System.out.printf("✅ Снято: %.2f руб. Остаток: %.2f руб.\n", amount, balance);
        } else if (amount > balance) {
            System.out.println("❌ Недостаточно средств на счете!");
            transactions.add(new Transaction('E', amount, balance,
                    "Попытка снятия при недостаточном балансе"));
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction('D', amount, balance,
                    "Пополнение счета"));
            System.out.printf("✅ Пополнено: %.2f руб. Новый баланс: %.2f руб.\n", amount, balance);
        } else {
            System.out.println("❌ Сумма должна быть положительной!");
        }
    }

    public void displayAccountSummary() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("СВОДКА ПО СЧЕТУ");
        System.out.println("=".repeat(80));
        System.out.printf("Владелец счета: %s\n", name);
        System.out.printf("ID счета: %d\n", id);
        System.out.printf("Годовая процентная ставка: %.1f%%\n", annualInterestRate);
        System.out.printf("Текущий баланс: %.2f руб.\n", balance);
        System.out.printf("Ежемесячный процент: %.2f руб.\n", getMonthlyInterest());
        System.out.printf("Дата создания: %s\n", dateCreated);
        System.out.println("=".repeat(80));

        if (!transactions.isEmpty()) {
            System.out.println("\nИСТОРИЯ ТРАНЗАКЦИЙ:");
            System.out.println("-".repeat(80));
            System.out.printf("%-16s | %-2s | %-10s | %-10s | %s\n",
                    "Дата", "Тип", "Сумма", "Баланс", "Описание");
            System.out.println("-".repeat(80));

            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("Транзакции отсутствуют.");
        }
        System.out.println("=".repeat(80));
    }

    public int getTransactionCount() {
        return transactions.size();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}