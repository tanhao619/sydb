package com.cdyoue.oddJobs.error;

import com.cdyoue.oddJobs.exception.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller advice to translate the server side exceptions to client-friendly json structures.
 */
@RestController
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @Autowired
    private ResponseHolder responseHolder;


    /**
     * ======================
     * BAD　REQUEST
     * ======================
     */

    @ExceptionHandler(PersistenceEntityException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MessageErrorVM processParameterizedValidationError(PersistenceEntityException pee) {
        MessageErrorVM vm = new MessageErrorVM(30, responseHolder.getBehavior(), pee.getMessage());
        return vm;
    }

    @ExceptionHandler(DataException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MessageErrorVM processParameterizedValidationError(DataException de) {
        MessageErrorVM vm = new MessageErrorVM(31, responseHolder.getBehavior(), de.getMessage());
        return vm;
    }


    @ExceptionHandler(LogicException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MessageErrorVM processParameterizedValidationError(LogicException le) {
        MessageErrorVM vm = new MessageErrorVM(32, responseHolder.getBehavior(), le.getMessage());
        return vm;
    }

    @ExceptionHandler(LogicalException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MessageErrorVM processParameterizedValidationError(LogicalException le) {
        MessageErrorVM vm = new MessageErrorVM(32,le.getMessage(),responseHolder.getBehavior());
        return vm;
    }

    @ExceptionHandler(InvalidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MessageErrorVM processInvalidExceptionError(InvalidException ie) {
        MessageErrorVM vm = new MessageErrorVM(33, responseHolder.getBehavior(), ie.getMessage());
        return vm;
    }


    @ExceptionHandler(BadRequestMessageException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MessageErrorVM invalidTokenExceptionError(BadRequestMessageException bme) {
        return bme.getErrorVM();
    }

    /**
     * ======================
     * NOT　FOUND
     * ======================
     */
    @ExceptionHandler(NotFoundEntityException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MessageErrorVM processParameterizedValidationError(NotFoundEntityException efee) {
        MessageErrorVM vm = new MessageErrorVM(40, responseHolder.getBehavior(), efee.getMessage());
        return vm;
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MessageErrorVM processParameterizedValidationError(EmptyResultDataAccessException erae) {
        MessageErrorVM vm = new MessageErrorVM(41, responseHolder.getBehavior(), erae.getMessage());
        return vm;
    }


    /**
     * ======================
     * UNAUTHORIZED
     * ======================
     */

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MessageErrorVM invalidTokenExceptionError(InvalidTokenException ite) {
        MessageErrorVM vm = new MessageErrorVM(50, responseHolder.getBehavior(), ite.getMessage());
        return vm;
    }

    @ExceptionHandler(UnAuthenticationMessageException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MessageErrorVM unAuhExceptionError(UnAuthenticationMessageException ite) {

        return ite.getErrorVM();
    }


    /**
     * ======================
     * INTERNAL_SERVER_ERROR
     * ======================
     */

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ParameterizedErrorVM customExceptionExceptionError(CustomException ite) {
        return ite.getErrorVM();
    }


    /**
     * ==============================================
     * validate exception
     * ==============================================
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MessageErrorVM methodArgumentNotValidExceptionError(MethodArgumentNotValidException ite) {
        BindingResult result = ite.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<String> msg = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            msg.add(fieldError.getDefaultMessage());
        }
        MessageErrorVM vm = new MessageErrorVM(30, responseHolder.getBehavior(), StringUtils.join(msg, ";"));
        return vm;
    }




}
