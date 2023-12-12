package fast.bank.api.domain.account.dto;

public record AccountTransactionResponseData(Long senderAccNumber, Long receiverAccNumber, Double transferAmount, Double senderActualBalance) {
}
