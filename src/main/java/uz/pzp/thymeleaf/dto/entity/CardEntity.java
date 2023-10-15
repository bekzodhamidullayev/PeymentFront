package uz.pzp.thymeleaf.dto.entity;


import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CardEntity{

    private UserEntity owner;
    private boolean isActive = true;
    private String cardNumber;
    private String password;
    private Double balance;
    private String  type;
}
