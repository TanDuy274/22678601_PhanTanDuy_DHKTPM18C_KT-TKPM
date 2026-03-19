package iuh.fit.se.concreteComponent;

import iuh.fit.se.interfaces.Payment;

public class CreditCardPayment implements Payment {
    private double amount;
    public CreditCardPayment(double amount) { this.amount = amount; }
    public double getAmount() { return amount; }
    public void process() { System.out.println("Thanh toán thẻ tín dụng: " + getAmount()); }
}
