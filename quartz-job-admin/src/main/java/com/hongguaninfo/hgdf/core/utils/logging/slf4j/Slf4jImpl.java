package com.hongguaninfo.hgdf.core.utils.logging.slf4j;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.spi.LocationAwareLogger;

public class Slf4jImpl implements Log {
    private Log log;

    public Slf4jImpl(String clazz) {
        Logger logger = LoggerFactory.getLogger(clazz);

        if ((logger instanceof LocationAwareLogger)) {
            try {
                logger.getClass().getMethod(
                        "log",
                        new Class[] { Marker.class, String.class, Integer.TYPE, String.class, Object.class,
                                Throwable.class });
                this.log = new Slf4jLocationAwareLoggerImpl((LocationAwareLogger) logger);
                return;
            } catch (SecurityException e) {
            } catch (NoSuchMethodException e) {
            }
        }
        this.log = new Slf4jLoggerImpl(logger);
    }

    public boolean isDebugEnabled() {
        return this.log.isDebugEnabled();
    }

    public boolean isTraceEnabled() {
        return this.log.isTraceEnabled();
    }

    public void error(String s, Throwable e) {
        this.log.error(s, e);
    }

    public void error(String s) {
        this.log.error(s);
    }

    public void debug(String s) {
        this.log.debug(s);
    }

    public void trace(String s) {
        this.log.trace(s);
    }

    public void warn(String s) {
        this.log.warn(s);
    }

    public void info(String s) {
        this.log.info(s);
    }
}