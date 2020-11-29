package com.lzw.flowablespringboot.holder;

import org.flowable.common.engine.impl.cfg.multitenant.TenantInfoHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author LZW
 * @date 2020/5/25 16:14
 */
@Component
public class HifmTenantInfoHolder implements TenantInfoHolder {

    @Override
    public Collection<String> getAllTenants() {
        return null;
    }

    @Override
    public void setCurrentTenantId(String s) {

    }

    @Override
    public String getCurrentTenantId() {
        return null;
    }

    @Override
    public void clearCurrentTenantId() {

    }
}
