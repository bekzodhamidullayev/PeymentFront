package uz.pzp.thymeleaf.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionCreateDto {
    private String receiverCardNum;
    private String senderCardNum;
    private Double amount;

}
