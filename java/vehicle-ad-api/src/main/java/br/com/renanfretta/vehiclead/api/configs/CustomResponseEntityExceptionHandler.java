package br.com.renanfretta.vehiclead.api.configs;

import br.com.renanfretta.vehiclead.api.commons.BusinessException;
import br.com.renanfretta.vehiclead.api.commons.MessagesProperty;
import br.com.renanfretta.vehiclead.api.enums.MessagesPropertyEnum;
import br.com.renanfretta.vehiclead.api.exceptions.entity.ResourceNotFoundException;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessagesProperty messagesProperty;

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(BusinessException e, WebRequest request) {
        String userMessage = e.getMessage();
        String developerMessage = e.getCause() != null ? e.getCause().toString() : e.toString();
        log.info(developerMessage);
        List<ErrorOutputDTO> errors = Arrays.asList(ErrorOutputDTO.builder().userMessage(userMessage).developerMessage(developerMessage).build());
        return handleExceptionInternal(e, errors, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(ResourceNotFoundException e, WebRequest request) {
        String userMessage = messagesProperty.getMessage(MessagesPropertyEnum.ERROR_RESOURCE_NOT_FOUND);
        String developerMessage = e.getCause() != null ? e.getCause().toString() : e.toString();
        log.info(developerMessage);
        List<ErrorOutputDTO> errors = Arrays.asList(ErrorOutputDTO.builder().userMessage(userMessage).developerMessage(developerMessage).build());
        return handleExceptionInternal(e, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Getter
    @Builder
    private static class ErrorOutputDTO {
        private String userMessage;
        private String developerMessage;
    }

}
