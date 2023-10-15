package uz.pzp.thymeleaf.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionHistoryInPeriodDto {
    private UUID ownerId;
    private LocalDateTime startData;
    private LocalDateTime endDate;
}
