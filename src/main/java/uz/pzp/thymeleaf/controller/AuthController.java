package uz.pzp.thymeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pzp.thymeleaf.dto.entity.CardEntity;
import uz.pzp.thymeleaf.dto.request.LoginDto;
import uz.pzp.thymeleaf.dto.request.UserCreateDto;
import uz.pzp.thymeleaf.dto.response.UserResponseDTO;
import uz.pzp.thymeleaf.service.CardService;
import uz.pzp.thymeleaf.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final CardService cardService;

    @GetMapping("/sign-up")
    public String signUpPage() {
        return "index";
    }
    @PostMapping("/sign-up")
    public String signUp(
            @ModelAttribute UserCreateDto userCreateDto
            ) {
        userService.addUser(userCreateDto);
        return "index";
    }


    @GetMapping("/sign-in")
    public String signInPage() {
        return "index";
    }
    @PostMapping("/sign-in")
    public String signIn(
            @ModelAttribute LoginDto loginDto,
            Model model
    ) {

        UserResponseDTO userResponseDTO = userService.signIn(loginDto);
        List<CardEntity> cardEntities = cardService.myCards(userResponseDTO.getId());
        model.addAttribute("cardsBalance", cardService.getUserCardBalance(userResponseDTO.getId()));
        model.addAttribute("cardEntities", cardEntities);
        model.addAttribute("userId", userResponseDTO.getId());
        return "card/manage-cards";
    }
}
