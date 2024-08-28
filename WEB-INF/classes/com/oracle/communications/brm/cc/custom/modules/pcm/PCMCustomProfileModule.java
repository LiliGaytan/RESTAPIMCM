package com.oracle.communications.brm.cc.custom.modules.pcm;

import com.oracle.communications.brm.cc.common.BRMUtility;
import com.oracle.communications.brm.cc.custom.model.CustomProfileMCM;
import com.portal.pcm.EBufException;
import com.oracle.communications.brm.cc.util.CCLogger;
import com.oracle.communications.brm.cc.modules.pcm.PCMModuleBase;
import com.oracle.communications.brm.cc.util.ExceptionHelper;
import com.oracle.communications.brm.cc.custom.modules.CustomProfileModule;
import com.oracle.communications.brm.cc.custom.modules.pcm.workers.CustomProfileWorker;
import com.oracle.communications.brm.cc.enums.ErrorConstants;
import com.portal.pcm.PortalContext;
import javax.ws.rs.core.Response;

public class PCMCustomProfileModule extends PCMModuleBase implements CustomProfileModule {
    
    private static final CCLogger logger = CCLogger.getCCLogger(PCMCustomProfileModule.class);
    
    @Override
    public CustomProfileMCM getProfileInfo(final String id) {
        
        PortalContext ctx = null;
        CustomProfileWorker customProfileWorker;
        CustomProfileMCM customProfileMCM = null;

        try {
            ctx = BRMUtility.getConnection();
            customProfileWorker = new CustomProfileWorker();
            customProfileMCM = customProfileWorker.getProfileDetails(id, ctx);
        } catch (EBufException ex) {
            
            if (ex.getErrorString().equalsIgnoreCase("ERR_NAP_CONNECT_FAILED")) {
                logger.severe("\nBRM Connection Error:\n", (Throwable)ex);
                ExceptionHelper.buildErrorInfo(ErrorConstants.ERROR_BRM_CONNECTION_FAILED.errorCode(), ErrorConstants.ERROR_BRM_CONNECTION_FAILED.errorMessage(), Response.Status.INTERNAL_SERVER_ERROR, true, null);
            }
            else if (ex.getErrorString().equalsIgnoreCase("ERR_TIMEOUT")) {
                logger.severe("\nBRM Error: The operation has timed out\n", (Throwable)ex);
                ExceptionHelper.buildErrorInfo(ErrorConstants.ERROR_OPCODE_TIMEOUT.errorCode(), ErrorConstants.ERROR_OPCODE_TIMEOUT.errorMessage(), Response.Status.INTERNAL_SERVER_ERROR, true, null);
            }
            else {
                logger.severe("\nUnable to get profile info:\n", (Throwable)ex);
                ExceptionHelper.buildErrorInfo(ErrorConstants.ERROR_MISSING_PROFILE.errorCode(), ErrorConstants.ERROR_MISSING_PROFILE.errorMessage(), Response.Status.INTERNAL_SERVER_ERROR, false, null);
            }
        } finally {
            if (ctx != null) {
                BRMUtility.releaseConnection(ctx);
            }
        }
        
        return customProfileMCM;
    }
}
