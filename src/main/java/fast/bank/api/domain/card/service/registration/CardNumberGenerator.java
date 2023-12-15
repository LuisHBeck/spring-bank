package fast.bank.api.domain.card.service.registration;

import java.util.Random;

public class CardNumberGenerator {
    public static Long generate(int numberLength) {
        Random random = new Random();
        Long number = 0l;

        for (int i =0; i < numberLength; i++) {
            int digit = random.nextInt(10);
            number = number * 10 + digit;
        }
        return number;
    }
}
