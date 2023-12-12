package fast.bank.api.domain.statement.dto;

import fast.bank.api.domain.account.model.Account;
import fast.bank.api.domain.statement.model.Statement;
import fast.bank.api.domain.statement.model.TransactionType;

public record StatementDetailingData(Long id, Long account, TransactionType transactionType, double amount, double postTransactionBalance) {
    public StatementDetailingData(Statement statement) {
        this(statement.getId(), statement.getAccount().getNumber(), statement.getTransactionType(), statement.getAmount(), statement.getPostTransactionBalance());
    }
}
