package uz.pzp.thymeleaf.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.pzp.thymeleaf.dto.request.LoginDto;
import uz.pzp.thymeleaf.dto.request.UserCreateDto;
import uz.pzp.thymeleaf.dto.response.UserResponseDTO;

import java.net.http.HttpRequest;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${backend-url}")
    private String backendUrl;

    public UserResponseDTO addUser(UserCreateDto userCreateDto) {
        ResponseEntity<UserResponseDTO> exchange = restTemplate.exchange(backendUrl + "/auth/sign-up",
                HttpMethod.POST,
                new HttpEntity<>(userCreateDto),
                UserResponseDTO.class
        );

        return exchange.getBody();
    }

    public UserResponseDTO signIn(LoginDto loginDto) {
        ResponseEntity<UserResponseDTO> response = restTemplate.exchange(
                backendUrl + "/auth/sign-in",
                HttpMethod.POST,
                new HttpEntity<>(loginDto),
                UserResponseDTO.class
        );

        return response.getBody();
    }
}
