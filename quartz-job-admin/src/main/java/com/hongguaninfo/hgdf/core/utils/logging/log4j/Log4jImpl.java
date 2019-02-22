package com.hongguaninfo.hgdf.core.utils.logging.log4j;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log4jImpl implements Log {
    private static final String FQCN = Log4jImpl.class.getName();
    private Logger log;

    public Log4jImpl(String clazz) {
        this.log = Logger.getLogger(clazz);
    }

    public boolean isDebugEnabled() {
        return this.log.isDebugEnabled();
    }

    public boolean isTraceEnabled() {
        return this.log.isTraceEnabled();
    }

    public void error(String s, Throwable e) {
        this.log.log(FQCN, Level.ERROR, s, e);
    }

    public void error(String s) {
        this.log.log(FQCN, Level.ERROR, s, null);
    }

    public void debug(String s) {
        this.log.log(FQCN, Level.DEBUG, s, null);
    }

    public void trace(String s) {
        this.log.log(FQCN, Level.TRACE, s, null);
    }

    public void warn(String s) {
        this.log.log(FQCN, Level.WARN, s, null);
    }

    public void info(String s) {
        this.log.log(FQCN, Level.INFO, s, null);
    }
}