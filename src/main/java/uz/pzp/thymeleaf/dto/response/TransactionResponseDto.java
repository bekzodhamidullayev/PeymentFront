package uz.pzp.thymeleaf.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionResponseDto {
    private Double balance;
    private String  receiverCardNum;
    private String senderCardNum;
}
