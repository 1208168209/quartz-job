package com.hongguaninfo.hgdf.core.utils.logging.commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JakartaCommonsLoggingImpl implements com.hongguaninfo.hgdf.core.utils.logging.Log {

    private Log log;

    public JakartaCommonsLoggingImpl(String clazz) {
        log = LogFactory.getLog(clazz);
    }

    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    public boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }

    public void error(String s, Throwable e) {
        log.error(s, e);
    }

    public void error(String s) {
        log.error(s);
    }

    public void debug(String s) {
        log.debug(s);
    }

    public void trace(String s) {
        log.trace(s);
    }

    public void warn(String s) {
        log.warn(s);
    }

    public void info(String s) {
        log.info(s);
    }

}
