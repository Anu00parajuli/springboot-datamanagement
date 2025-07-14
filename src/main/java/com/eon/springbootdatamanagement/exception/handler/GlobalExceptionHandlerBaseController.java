package com.eon.springbootdatamanagement.exception.handler;

import com.eon.springbootdatamanagement.builder.ServiceResponseBuilder;
import com.eon.springbootdatamanagement.component.MessageBundle;
import com.eon.springbootdatamanagement.enums.ApiStatusEnum;
import com.eon.springbootdatamanagement.exception.GlobalException;
import com.eon.springbootdatamanagement.payload.response.GlobalResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.ParameterErrors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandlerBaseController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerBaseController.class);

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<GlobalResponse> handleRemitsException(GlobalException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(ServiceResponseBuilder.buildFailResponse(e));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponse> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ServiceResponseBuilder.buildUnknownFailResponse(e));
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<GlobalResponse> handleException(HandlerMethodValidationException e) {
        List<ParameterErrors> bindingResult = e.getBeanResults();
        Map<String, List<String>> data = bindingResult.stream()
                .flatMap(result -> result.getFieldErrors().stream())
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(DefaultMessageSourceResolvable::getDefaultMessage, Collectors.toList())
                ));
        GlobalResponse globalResponse = new GlobalResponse();
        globalResponse.setStatus(ApiStatusEnum.FAILED);
        globalResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        globalResponse.setData(data);
        if (logger.isInfoEnabled())
            logger.info("METHOD ARGUMENT NOT VALID EXCEPTION: {}", new Gson().toJson(globalResponse));
        return ResponseEntity.badRequest()
                .body(globalResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponse> handleException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        Map<String, List<String>> errors = bindingResult.getAllErrors().stream().collect(Collectors.groupingBy(
                error -> (error instanceof FieldError fieldError) ? fieldError.getField() : error.getObjectName(),
                Collectors.mapping(DefaultMessageSourceResolvable::getDefaultMessage, Collectors.toList())));
        GlobalResponse globalResponse = new GlobalResponse();
        globalResponse.setStatus(ApiStatusEnum.FAILED);
        globalResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        globalResponse.setData(errors);
        return ResponseEntity.badRequest()
                .body(globalResponse);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<GlobalResponse> handleResourceNotFoundException(NoResourceFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ServiceResponseBuilder.buildUnknownFailResponse(e));
    }

//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<Response> s(final AuthenticationException e, final HttpServletResponse response) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body(ServiceResponseBuilder.buildUnknownFailResponse(e));
//    }

//    @ExceptionHandler(WebClientResponseException.Forbidden.class)
//    public ResponseEntity<Response> handleNotAuthorizedException(final WebClientResponseException.Forbidden e) {
//        return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                .body(ServiceResponseBuilder.buildUnknownFailResponse(e));
//    }

//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<Response> handleAccessDeniedException(final AccessDeniedException e) {
//        GlobalResponse globalResponse = new GlobalResponse();
//        globalResponse.setSuccess(false);
//        globalResponse.setMessage(MessageBundle.getMessageByCode("PER004"));
//        return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                .body(globalResponse);
//    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<GlobalResponse> handleMissingRequestHeaderException(final MissingRequestHeaderException e) {
        GlobalResponse globalResponse = new GlobalResponse();
        globalResponse.setStatus(ApiStatusEnum.FAILED);
        globalResponse.setMessage(MessageBundle.getMessageByCode("HED001"));
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(globalResponse);
    }
}
