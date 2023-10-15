package uz.pzp.thymeleaf.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import uz.pzp.thymeleaf.dto.entity.CardEntity;
import uz.pzp.thymeleaf.dto.request.CardCreateDto;
import uz.pzp.thymeleaf.dto.request.UpdateCardDto;
import uz.pzp.thymeleaf.dto.response.CardResponse;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${backend-url}")
    private String backendUrl;

    public void addCard(CardCreateDto cardCreateDto) {
        restTemplate.exchange(backendUrl + "/card/add-card",
                HttpMethod.POST,
                new HttpEntity<>(cardCreateDto),
                CardResponse.class
        );
    }

    public Double getUserCardBalance(UUID ownerId) {
        Double newCardBalance = 0D;
        for (CardEntity myCard : myCards(ownerId)) {
            newCardBalance += myCard.getBalance();
        }
        return newCardBalance;
    }
    public List<CardEntity> myCards(UUID ownerId) {

        ResponseEntity<List<CardEntity>> responseEntity = restTemplate.exchange(
                backendUrl + "/card/my-cards/{ownerId}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CardEntity>>() {},
                ownerId
        );
        return responseEntity.getBody();
    }

    public void updateCardBalance(UpdateCardDto updateCardDto) {
        restTemplate.exchange(backendUrl + "/card/update-card-balance",
                HttpMethod.PUT,
                new HttpEntity<>(updateCardDto),
                CardResponse.class
        );
    }

    public void deleteByCardNumber(String cardNumber) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(backendUrl + "/card/delete-by-card-number")
                .queryParam("cardNumber", cardNumber);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.DELETE,
                entity,
                Void.class
        );
    }


}
