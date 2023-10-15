package uz.pzp.thymeleaf.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pzp.thymeleaf.dto.entity.UserEntity;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardResponse {

    private UUID ownerId;
    private UUID id;
    private String cardNumber;
    private Double balance;
    private String type;
}
