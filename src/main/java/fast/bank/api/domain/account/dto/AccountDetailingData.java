package fast.bank.api.domain.account.dto;

import fast.bank.api.domain.account.model.Account;

public record AccountDetailingData(Long user, Long number, Integer agency, Double balance, Double creditLimit) {
    public AccountDetailingData (Account account){
        this(account.getUser().getRegistry(), account.getNumber(), account.getAgency(), account.getBalance(), account.getCreditLimit());
    }
}
