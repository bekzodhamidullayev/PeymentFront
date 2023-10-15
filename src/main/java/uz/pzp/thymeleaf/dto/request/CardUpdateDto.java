package uz.pzp.thymeleaf.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardUpdateDto {
    private UUID ownerId;
    private String cardNumber;
    private Double balance;
}
