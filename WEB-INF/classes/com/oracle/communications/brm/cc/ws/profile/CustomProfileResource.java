package com.oracle.communications.brm.cc.ws.profile;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import javax.ws.rs.Produces;
import com.oracle.communications.brm.cc.exceptions.ApplicationException;
import com.oracle.communications.brm.cc.util.ExceptionHelper;
import com.oracle.communications.brm.cc.custom.common.CustomBRMCommunicationContext;
import com.oracle.communications.brm.cc.custom.model.CustomProfileMCM;
import javax.servlet.http.HttpServletRequest;
import com.oracle.communications.brm.cc.util.CCLogger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.ws.rs.Path;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import javax.ws.rs.GET;
import com.oracle.communications.brm.cc.custom.modules.CustomProfileModule;
import io.swagger.v3.oas.annotations.Parameter;
import javax.ws.rs.PathParam;

@OpenAPIDefinition(info = @Info(title = "REST API Reference for Billing Care", version = "v1", description = "Oracle Communications Billing Care provides REST APIs that can be used to perform various operations like payments, collections, customer account management, bill history, accounts receivables, and others."))
@Path("mcm/profiles")
@Tag(name = "Profiles", description = "The REST web service for all profile-related operations.")
public class CustomProfileResource {
    
    @Context
    UriInfo uriInfo;
    
    private static final CCLogger logger = CCLogger.getCCLogger(CustomProfileResource.class);
    
    @Context
    HttpServletRequest servletRequest;

    @Path("/info/{id}")
    @GET
    @Produces({ "application/json" })
    @Operation(summary = "Get Profile Details ", description = "Gets profile info that matches the specified profile ID.")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "The profile info were retrieved successfully.", content = { @Content(schema = @Schema(implementation = CustomProfileMCM.class)) }), @ApiResponse(responseCode = "500", description = "An internal server error occurred.") })
    public CustomProfileMCM getProfileInfo(@Parameter(description = "The profile ID, such as 0.0.0.1+-profile+123123.") @PathParam("id") final String id) {
        
        final CustomProfileModule mcmProfileModule = new CustomBRMCommunicationContext().getCustomCommunicationFactory().getCustomProfileModule();
        mcmProfileModule.setURIContext(this.uriInfo);
        CustomProfileMCM mcmProfileInfo = null;
        
        try {
            mcmProfileInfo = mcmProfileModule.getProfileInfo(id);
        }
        catch (ApplicationException ex) {
            logger.severe("\nUnable to retrieve profile info:\n", (Throwable)ex);
            ExceptionHelper.handleException(ex);
        }
        
        return mcmProfileInfo;
    }
}