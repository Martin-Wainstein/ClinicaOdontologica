package com.dh.integrador.exceptions;

import org.apache.log4j.Logger;

public class ResourceNotFoundException extends Exception{

    private static final Logger logger = Logger.getLogger(ResourceNotFoundException.class);

    public ResourceNotFoundException(String mensaje){
        super(mensaje);
        logger.info("Not Found Exception");
    }

}
