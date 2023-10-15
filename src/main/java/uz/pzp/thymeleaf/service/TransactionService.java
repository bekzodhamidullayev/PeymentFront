package uz.pzp.thymeleaf.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.pzp.thymeleaf.dto.entity.CardEntity;
import uz.pzp.thymeleaf.dto.request.TransactionCreateDto;
import uz.pzp.thymeleaf.dto.response.CardResponse;
import uz.pzp.thymeleaf.dto.response.TransactionResponseDto;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${backend-url}")
    private String backendUrl;
    public void addTr(TransactionCreateDto transactionCreateDto) {
        restTemplate.exchange(backendUrl + "/transaction/add-transaction",
                HttpMethod.POST,
                new HttpEntity<>(transactionCreateDto),
                CardResponse.class
        );
    }

    public List<TransactionResponseDto> getAll(UUID ownerId) {
        ResponseEntity<List<TransactionResponseDto>> responseEntity = restTemplate.exchange(
                backendUrl + "/transaction/history/{ownerId}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TransactionResponseDto>>() {},
                ownerId
        );
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }
}
