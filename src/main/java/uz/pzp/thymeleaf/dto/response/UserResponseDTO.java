package uz.pzp.thymeleaf.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserResponseDTO {
    private UUID id;
    private String name;
    private String username;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
