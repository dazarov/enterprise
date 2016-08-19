package com.daniel.blog.rest;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class DefaultExceptionHandler {
	//private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    /**
 * General purpose exception handler. Returns HTTP 500
 */
@ExceptionHandler(value={Exception.class})
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@ResponseBody
public ErrorResponse handleException(Exception exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
 String errorRef = UUID.randomUUID().toString();
    LOGGER.error("errorRef:{}-{} ",errorRef, exception);
    return new ErrorResponse(exception.getMessage(), errorRef); 
      
}


/**
 * Wrong input parameters. Return Http 422
 */
@ExceptionHandler(value={TransactionServerException.class})
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
@ResponseBody
public ErrorResponse handleIllegalArgumentException(TransactionServerException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
 String errorRef = UUID.randomUUID().toString();
    LOGGER.error("errorRef:{}-{} ",errorRef, exception);
    return new ErrorResponse(exception.getMessage(), errorRef, true); 
}



/**
 * Duplicate transaction. Return Http 409 
 */
@ExceptionHandler(value={DuplicatePaymentException.class})
@ResponseStatus(HttpStatus.CONFLICT)
@ResponseBody
public ErrorResponse handleDuplicateTransactionException(Exception exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
 String errorRef = UUID.randomUUID().toString();
    LOGGER.error("errorRef:{}-{} ",errorRef, exception);
    return new ErrorResponse("Duplicate transaction"); 
}


/**
 * Failured from services. Return Http 400 
 */
@ExceptionHandler(value={PaymentGatewayException.class, ServiceFailedException.class})
@ResponseStatus(HttpStatus.BAD_REQUEST)
@ResponseBody
public ErrorResponse handleProcessorResponseException(PaymentGatewayException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
 String errorRef = UUID.randomUUID().toString();
    LOGGER.error("errorRef:{}-{} ",errorRef, exception);
    return new ErrorResponse(exception.getMessage(), errorRef, true); 
}



/**
 * Wrong input parameters. Return Http 400 
 */
@ExceptionHandler(value={IllegalArgumentException.class})
@ResponseStatus(HttpStatus.BAD_REQUEST)
@ResponseBody
public ErrorResponse handleIllegalArgumentException(IllegalArgumentException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
 String errorRef = UUID.randomUUID().toString();
    LOGGER.error("errorRef:{}-{} ",errorRef, exception);
    return new ErrorResponse(exception.getMessage(), errorRef, true); 
}





}
