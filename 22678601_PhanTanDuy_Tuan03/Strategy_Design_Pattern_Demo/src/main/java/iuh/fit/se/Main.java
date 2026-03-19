package iuh.fit.se;

import iuh.fit.se.concreteStrategies.CreditCardPayment;
import iuh.fit.se.concreteStrategies.PayPalPayment;
import iuh.fit.se.context.ShoppingCart;

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.setPaymentMethod(new CreditCardPayment());
        cart.checkout(500000);

        cart.setPaymentMethod(new PayPalPayment());
        cart.checkout(200000);
    }
}