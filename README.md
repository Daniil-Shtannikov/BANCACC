# Банковское приложение

## UML диаграмма класса Account
```mermaid
classDiagram
    class Account {
        -int id
        -double balance
        -double annualInterestRate
        -Date dateCreated
        +Account()
        +Account(int id, double balance)
        +getId() int
        +setId(int id) void
        +getBalance() double
        +setBalance(double balance) void
        +getAnnualInterestRate() double
        +setAnnualInterestRate(double annualInterestRate) void
        +getDateCreated() Date
        +getMonthlyInterest() double
        +withdraw(double sum) void
        +deposit(double sum) void
        +toString() String
    }
