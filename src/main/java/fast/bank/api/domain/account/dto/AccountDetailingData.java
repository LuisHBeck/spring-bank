package fast.bank.api.domain.account.dto;

public record AccountDetailingData(Long user, Long number, Integer agency, Double balance, Double creditLimit) {
}
