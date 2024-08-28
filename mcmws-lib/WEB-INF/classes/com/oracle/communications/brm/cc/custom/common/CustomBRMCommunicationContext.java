package com.oracle.communications.brm.cc.custom.common;

import com.oracle.communications.brm.cc.util.CCLogger;
import java.lang.reflect.InvocationTargetException;

public class CustomBRMCommunicationContext {
    
    public static final String FACTORY_IMPL_KEY = "brm.comms.factory.class";
    private CustomBRMCommunicationFactory CustomBRMCommunicationFactory;
    
    private static final CCLogger logger = CCLogger.getCCLogger(CustomBRMCommunicationContext.class);
    
    public CustomBRMCommunicationContext() {
        String property = System.getProperty("brm.comms.factory.class");
        if (property == null || property.length() == 0) {
            property = "com.oracle.communications.brm.cc.custom.modules.pcm.PCMCustomCommunicationFactoryImpl";
        }
        try {
            this.CustomBRMCommunicationFactory = (CustomBRMCommunicationFactory)Class.forName(property).getDeclaredConstructor().newInstance();
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | NoSuchMethodException | InvocationTargetException ex) {
            CustomBRMCommunicationContext.logger.severe("Error while getting instance for communication factory", (Throwable)ex);
        }
    }
    
    public CustomBRMCommunicationFactory getCustomCommunicationFactory() {
        return this.CustomBRMCommunicationFactory;
    }
}
