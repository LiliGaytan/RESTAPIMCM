package com.oracle.communications.brm.cc.custom.modules.pcm.workers;

import com.oracle.communications.brm.cc.common.BRMUtility;
import com.oracle.communications.brm.cc.custom.model.CustomProfileMCM;
import com.oracle.communications.brm.cc.custom.model.MCMProfileInfo;
import com.oracle.communications.brm.cc.custom.utils.CalendarUtil;
import com.oracle.communications.brm.cc.util.CCLogger;
import com.portal.pcm.EBufException;
import com.portal.pcm.FList;
import com.portal.pcm.Poid;
import com.portal.pcm.PortalContext;
import com.portal.pcm.PortalOp;
import com.portal.pcm.fields.FldAccountObj;
import com.portal.pcm.fields.FldCreatedT;
import com.portal.pcm.fields.FldDay;
import com.portal.pcm.fields.FldEffectiveT;
import com.portal.pcm.fields.FldEmailStatus;
import com.portal.pcm.fields.FldInvFlags;
import com.portal.pcm.fields.FldName;
import com.portal.pcm.fields.FldObjectCacheType;
import com.portal.pcm.fields.FldOpenItemPositionId;
import com.portal.pcm.fields.FldPayMethod;
import com.portal.pcm.fields.FldPayProvCode;
import com.portal.pcm.fields.FldPoOrderNo;
import com.portal.pcm.fields.FldPoid;
import com.portal.pcm.fields.FldServiceObj;
import com.portal.pcm.fields.FldTaxData;
import com.portal.pcm.fields.FldUserName;
import java.util.Calendar;

public class CustomProfileWorker {
    
    private static final CCLogger logger = CCLogger.getCCLogger(CustomProfileWorker.class);
    
    public CustomProfileMCM getProfileDetails(final String id, PortalContext ctx) throws EBufException {
        
        CustomProfileMCM customProfileMCM = new CustomProfileMCM();
        CalendarUtil calendarUtil = new CalendarUtil();
        Poid profilePoid = BRMUtility.poidFromRestId(id);
        FList readMCMProfileObjInFList = new FList();
        
        readMCMProfileObjInFList.set(FldPoid.getInst(), profilePoid);
        
        logger.info("\nMCM Profile Details - Input Flist:\n" + readMCMProfileObjInFList.asString());
        FList readMCMProfileObjOutFList = ctx.opcode(PortalOp.READ_OBJ, readMCMProfileObjInFList);
        logger.info("\nMCM Profile Details - Output Flist:\n" + readMCMProfileObjOutFList.asString());
        
        MCMProfileInfo mcmProfileInfo = customProfileMCM.getProfileInfo();

        String poidMCMProfile = BRMUtility.restIdFromPoid(readMCMProfileObjOutFList.get(FldPoid.getInst()));
        mcmProfileInfo.setId(poidMCMProfile);

        Calendar creationDate = calendarUtil.DateToCalendar(readMCMProfileObjOutFList.get(FldCreatedT.getInst()));
        mcmProfileInfo.setCreationDate(creationDate);
        
        String accountPoid = BRMUtility.restIdFromPoid(readMCMProfileObjOutFList.get(FldAccountObj.getInst()));
        mcmProfileInfo.setId(accountPoid);

        Calendar effectiveDate = calendarUtil.DateToCalendar(readMCMProfileObjOutFList.get(FldEffectiveT.getInst()));
        mcmProfileInfo.setCreationDate(effectiveDate);
        
        String profileName = readMCMProfileObjOutFList.get(FldName.getInst());
        mcmProfileInfo.setName(profileName);
        
        Integer objectCacheType = readMCMProfileObjOutFList.get(FldObjectCacheType.getInst());
        mcmProfileInfo.setObjectCacheType(objectCacheType);
        
        String servicePoid = BRMUtility.restIdFromPoid(readMCMProfileObjOutFList.get(FldServiceObj.getInst()));
        mcmProfileInfo.setId(servicePoid);
        
        MCMProfileInfo.MCMProfileAccounting accounting = mcmProfileInfo.getMCMProfileAccounting();
        /*
        
0 PIN_FLD_ACCOUNT_OBJ               POID [0] 0.0.0.1 /account 193239 0
0 PIN_FLD_POID                      POID [0] 0.0.0.1 /profile/mcm/billing_account 194103 0
0 PIN_FLD_EFFECTIVE_T             TSTAMP [0] (1721949770) 25/07/2024 17:22:50:000 PM
0 PIN_FLD_WRITE_ACCESS               STR [0] "S"
0 PIN_FLD_READ_ACCESS                STR [0] "S"
0 PIN_FLD_NAME                       STR [0] "mcm_profile"
0 PIN_FLD_ACCOUNTING           SUBSTRUCT [0] allocated 9, used 9
1     PIN_FLD_OPEN_ITEM_POSITION_ID  STR [0] "Identificador de la oportunidad"
1     PIN_FLD_TAX_DATA               STR [0] "Id de impuesto del extranjero."
1     PIN_FLD_PAY_PROV_CODE          STR [0] "Personas Físicas con Actividades Empresariales y Profesionales"
1     PIN_FLD_PO_ORDER_NO            STR [0] "Orden de compra a nivel oportunidad"
1     PIN_FLD_EMAIL_STATUS           STR [0] "notifClienteColl@mail.com"
1     PIN_FLD_USER_NAME              STR [0] "G03"
1     PIN_FLD_DAY                   ENUM [0] 5
1     PIN_FLD_PAY_METHOD             STR [0] "PUE"
1     PIN_FLD_INV_FLAGS              INT [0] 1
0 PIN_FLD_MOD_T                   TSTAMP [0] (1721949770) 25/07/2024 17:22:50:000 PM
0 PIN_FLD_CREATED_T               TSTAMP [0] (1721949770) 25/07/2024 17:22:50:000 PM
0 PIN_FLD_OBJECT_CACHE_TYPE         ENUM [0] 0
0 PIN_FLD_SERVICE_OBJ               POID [0] 0.0.0.0  0 0

        */
        String openItemPositionId;
        openItemPositionId = readMCMProfileObjOutFList.get(FldOpenItemPositionId.getInst());
        accounting.setOpenItemPositionId(openItemPositionId);     
        String taxData = readMCMProfileObjOutFList.get(FldTaxData.getInst());
        accounting.setPoOrderNo(taxData);
        String payProvCode = readMCMProfileObjOutFList.get(FldPayProvCode.getInst());
        accounting.setPayProvCode(payProvCode);        
        String poOrderNo = readMCMProfileObjOutFList.get(FldPoOrderNo.getInst());
        accounting.setPoOrderNo(poOrderNo);
        String emailStatus = readMCMProfileObjOutFList.get(FldEmailStatus.getInst());
        accounting.setEmailStatus(emailStatus);
        String username = readMCMProfileObjOutFList.get(FldUserName.getInst());
        accounting.setUsername(username);      
        Integer day = readMCMProfileObjOutFList.get(FldDay.getInst());
        accounting.setDay(day);
        String payMethod = readMCMProfileObjOutFList.get(FldPayMethod.getInst());
        accounting.setPayMethod(payMethod);        
        Integer invFlags = readMCMProfileObjOutFList.get(FldInvFlags.getInst());
        accounting.setInvoiceFlags(invFlags);
        customProfileMCM.setProfileInfo(mcmProfileInfo);
        return customProfileMCM;
    }   
}
