package org.example;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Account {
    private int id;
    private double balance;
    private double annualInterestRate;
    private final Date dateCreated;

    public Account() {
        this.id = 0;
        this.balance = 0;
        this.annualInterestRate = 0;
        this.dateCreated = new Date();
    }
    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
        this.annualInterestRate = 0;
        this.dateCreated = new Date();}

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

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public double getMonthlyInterest(){
        double month = annualInterestRate/12/100;
        return month * balance;
    }

    public void withdraw(double sum){
        if (sum > 0 && sum <= balance) {
            balance -= sum;
            System.out.println("Успешно снято " + sum + " рублей");
        } else {
            System.out.println("Недостаточно средств или неверная сумма");
        }
    }

    public void deposit(double sum){
        if (sum > 0) {
            balance += sum;
            System.out.println("Успешно зачислено " + sum + " рублей");
        } else {
            System.out.println("Сумма должна быть положительной");
        }
    }
    @Override
    public String toString() {return "";}

}

