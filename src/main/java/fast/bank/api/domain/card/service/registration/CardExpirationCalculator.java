package fast.bank.api.domain.card.service.registration;

import java.time.LocalDate;

public class CardExpirationCalculator {
    public static LocalDate calculateExpirationDate(int monthsDuration) {
        var today = LocalDate.now();
        return today.plusMonths(12);
    }
}
