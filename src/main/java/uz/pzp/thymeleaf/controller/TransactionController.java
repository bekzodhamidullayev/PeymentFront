package uz.pzp.thymeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pzp.thymeleaf.dto.entity.CardEntity;
import uz.pzp.thymeleaf.dto.request.TransactionCreateDto;
import uz.pzp.thymeleaf.dto.response.TransactionResponseDto;
import uz.pzp.thymeleaf.service.CardService;
import uz.pzp.thymeleaf.service.TransactionService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final CardService cardService;
    private final TransactionService transactionService;
    @GetMapping("/peyment/{userId}")
    public String addTransactionPath(
            @PathVariable UUID userId,
            Model model
    ) {
        model.addAttribute("userId", userId);
        return "/transaction/peyment";
    }
    @PostMapping("/peyment/{userId}")
    public String addTransaction(
            @PathVariable UUID userId,
            @ModelAttribute TransactionCreateDto transactionCreateDto,
            Model model
    ) {

        transactionService.addTr(transactionCreateDto);
        List<CardEntity> cardEntities = cardService.myCards(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("cardEntities", cardEntities);
        model.addAttribute("userId", userId);
        return "/card/manage-cards";
    }

    @GetMapping("/p2p/{userId}")
    public String p2p(@PathVariable UUID userId, Model model) {
        model.addAttribute("userId", userId);
        return "/transaction/p2p";
    }
    @GetMapping("/history/{userId}")
    public String history(
            @PathVariable UUID userId,
            Model model
    ) {
        System.out.println();
        List<TransactionResponseDto> trList = transactionService.getAll(userId);
        model.addAttribute("trList", trList);
        model.addAttribute("userId", userId);
        return "/transaction/history";
    }
}
