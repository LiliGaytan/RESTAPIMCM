package com.oracle.communications.brm.cc.custom.modules;

import com.oracle.communications.brm.cc.custom.model.CustomProfileMCM;
import com.oracle.communications.brm.cc.modules.BaseModule;

public interface CustomProfileModule extends BaseModule
{  
    CustomProfileMCM getProfileInfo(final String id);
}
