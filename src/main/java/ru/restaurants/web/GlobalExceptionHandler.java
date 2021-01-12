package ru.restaurants.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.restaurants.util.ValidationUtil;
import ru.restaurants.util.execption.ApplicationException;
import ru.restaurants.util.execption.ErrorType;


import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final MessageSourceAccessor messageSourceAccessor;

    public GlobalExceptionHandler(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e){
        LOG.error("Exception at request " + req.getRequestURL(), e);
        return logAndGetExceptionView(req, e, true, ru.restaurants.util.execption.ErrorType.APP_ERROR, null);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView wrongRequest(HttpServletRequest req, NoHandlerFoundException e) {
        return logAndGetExceptionView(req, e, false, ErrorType.WRONG_REQUEST, "exception.noHandlerFoundException");
    }

    @ExceptionHandler(ApplicationException.class)
    public ModelAndView updateRestrictionException(HttpServletRequest req, ApplicationException appEx) {
        return logAndGetExceptionView(req, appEx, false, appEx.getType(), appEx.getMsgCode());
    }

    private ModelAndView logAndGetExceptionView(HttpServletRequest req, Exception e, boolean logException, ErrorType errorType, String code) {
        Throwable rootCause = ValidationUtil.logAndGetRootCause(LOG, req, e, logException, errorType);

        ModelAndView mav = new ModelAndView("/exception");
        mav.addObject("exception", rootCause);
        mav.addObject("message", code != null ? messageSourceAccessor.getMessage(code) : ValidationUtil.getMessage(rootCause));
        mav.addObject("typeMessage", messageSourceAccessor.getMessage(errorType.getErrorCode()));
        mav.addObject("status", errorType.getStatus());

        mav.setStatus(errorType.getStatus());
        return mav;
    }
}