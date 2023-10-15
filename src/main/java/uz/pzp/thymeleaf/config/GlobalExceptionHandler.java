package uz.pzp.thymeleaf.config;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import uz.pzp.thymeleaf.dto.response.ErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ModelAndView handlerRestException(HttpClientErrorException e, HttpServletRequest request) {
        ErrorDto errorDto = e.getResponseBodyAs(ErrorDto.class);
        ModelAndView modelAttribute = new ModelAndView("error");
        modelAttribute.addObject("message", errorDto.getMessage());
        modelAttribute.addObject("link", request.getRequestURI());
        return modelAttribute;
    }
}
