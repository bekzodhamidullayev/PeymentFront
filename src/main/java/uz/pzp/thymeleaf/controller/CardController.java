package uz.pzp.thymeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pzp.thymeleaf.dto.entity.CardEntity;
import uz.pzp.thymeleaf.dto.request.CardCreateDto;
import uz.pzp.thymeleaf.dto.request.UpdateCardDto;
import uz.pzp.thymeleaf.service.CardService;


import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping("/add-card/{userId}")
    public String addCardPage(
            @PathVariable UUID userId,
            Model model
    ) {
        model.addAttribute("userId", userId);
        return "card/add-card";
    }
    @PostMapping("/add-card/{userId}")
    public String addCard(
            @PathVariable UUID userId,
            @ModelAttribute CardCreateDto cardDto,
            Model model
    ) {
        cardService.addCard(cardDto);
        List<CardEntity> cardEntities = cardService.myCards(userId);
        model.addAttribute("cardsBalance", cardService.getUserCardBalance(userId));
        model.addAttribute("userId", userId);
        model.addAttribute("cardEntities", cardEntities);
        return "card/manage-cards";
    }

    @GetMapping("/my-cards/{userId}")
    public String myCards(
            @PathVariable UUID userId,
            Model model
    ) {
        List<CardEntity> cardEntities = cardService.myCards(userId);
        model.addAttribute("cardsBalance", cardService.getUserCardBalance(userId));
        model.addAttribute("userId", userId);
        model.addAttribute("cardEntities", cardEntities);
        return "card/manage-cards";
    }

    @GetMapping("/manage-cards/{userId}")
    public String manageCardPage(
            @PathVariable UUID userId,
            Model model
    ) {
        List<CardEntity> cardEntities = cardService.myCards(userId);
        model.addAttribute("cardsBalance", cardService.getUserCardBalance(userId));
        model.addAttribute("userId", userId);
        model.addAttribute("cardEntities", cardEntities);
        return "card/manage-cards";
    }

    @PostMapping("/update-card-balance")
    public String updateCardBalance(
            @ModelAttribute UpdateCardDto updateCardDto,
            Model model
    ) {
        cardService.updateCardBalance(updateCardDto);
        List<CardEntity> cardEntities = cardService.myCards(updateCardDto.getOwnerId());
        model.addAttribute("cardsBalance", cardService.getUserCardBalance(updateCardDto.getOwnerId()));
        model.addAttribute("userId", updateCardDto.getOwnerId());
        model.addAttribute("cardEntities", cardEntities);
        return "card/manage-cards";
    }

    @GetMapping("/delete-by-card-number/{userId}/{cardNumber}")
    public String deleteByCardNumber(
            @PathVariable UUID userId,
            @PathVariable String cardNumber,
            Model model
    ) {

        cardService.deleteByCardNumber(cardNumber);
        System.out.println();
        List<CardEntity> cardEntities = cardService.myCards(userId);

        model.addAttribute("cardsBalance", cardService.getUserCardBalance(userId));
        model.addAttribute("userId", userId);
        model.addAttribute("cardEntities", cardEntities);
        return "card/manage-cards";
    }


//    @GetMapping("/find/{id}")
//    public CardResponse findById(@PathVariable UUID id) {
//        return cardService.findById(id);
//    }
//
//    @PutMapping("/update/{cardId}")
//    public CardResponse updateCard(
//            @PathVariable UUID cardId,
//            @RequestParam Double amount
//    ) {
//        return cardService.update(cardId, amount);
//    }
//    @GetMapping("/delete/{id}")
//    public void deleteById(@PathVariable UUID id) {
//        System.out.println();
//        cardService.deleteById(id);
//    }


}
