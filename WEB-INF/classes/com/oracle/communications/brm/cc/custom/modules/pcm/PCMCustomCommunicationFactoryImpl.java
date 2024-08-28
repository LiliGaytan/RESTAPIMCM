package com.oracle.communications.brm.cc.custom.modules.pcm;

import java.io.InputStream;
import java.io.IOException;
import com.oracle.communications.brm.cc.modules.pcm.workers.PCMBaseOps;
import java.util.Properties;
import com.oracle.communications.brm.cc.common.BaseOps;
import com.oracle.communications.brm.cc.util.CCLogger;
import com.oracle.communications.brm.cc.custom.common.CustomBRMCommunicationFactory;
import com.oracle.communications.brm.cc.custom.modules.CustomProfileModule;
import java.lang.reflect.InvocationTargetException;

public class PCMCustomCommunicationFactoryImpl implements CustomBRMCommunicationFactory {
    
    private static final CCLogger logger = CCLogger.getCCLogger(PCMCustomCommunicationFactoryImpl.class);
    
    private BaseOps baseOps;
    private CustomProfileModule mcmProfileModule;
    private static final Properties properties;
    
    @Override
    public synchronized CustomProfileModule getCustomProfileModule() {
        final String mcmProfile = this.logger("mcm profile");
        if (this.mcmProfileModule == null) {
            if (mcmProfile == null || mcmProfile.length() == 0) {
                this.mcmProfileModule = (CustomProfileModule)new PCMCustomProfileModule();
            }
            else {
                try {
                    this.mcmProfileModule = (CustomProfileModule)Class.forName(mcmProfile).getDeclaredConstructor().newInstance();
                }
                catch (ClassNotFoundException | InstantiationException | IllegalAccessException 
                        | NoSuchMethodException | InvocationTargetException ex) {
                    PCMCustomCommunicationFactoryImpl.logger.severe("Unable to instantiate custom module for account", (Throwable)ex);
                }
            }
            if (this.baseOps == null) {
                this.baseOps = (BaseOps)new PCMBaseOps();
            }
            this.mcmProfileModule.setBaseOps(this.baseOps);
        }
        return this.mcmProfileModule;
    }
  
    private String logger(final String str) {
        PCMCustomCommunicationFactoryImpl.logger.entering("getCustomModuleClass");
        final String property = PCMCustomCommunicationFactoryImpl.properties.getProperty("billingcare.rest." + str + ".module");
        PCMCustomCommunicationFactoryImpl.logger.exiting("getCustomModuleClass");
        return property;
    }
    
    static {
        properties = new Properties();
        final ClassLoader classLoader = PCMCustomCommunicationFactoryImpl.class.getClassLoader();
        if (classLoader != null) {
            final String name = "custom/customModule.properties";
            try (final InputStream resourceAsStream = classLoader.getResourceAsStream(name)) {
                if (resourceAsStream != null) {
                    PCMCustomCommunicationFactoryImpl.properties.load(resourceAsStream);
                    PCMCustomCommunicationFactoryImpl.logger.fine("Found 'customModule.properties' in classpath");
                }
                else {
                    PCMCustomCommunicationFactoryImpl.logger.fine("File 'customModule.properties' is not found in classpath");
                }
            }
            catch (IOException ex) {
                PCMCustomCommunicationFactoryImpl.logger.severe("Exception in reading custom module properties", (Throwable)ex);
            }
        }
    }
}
