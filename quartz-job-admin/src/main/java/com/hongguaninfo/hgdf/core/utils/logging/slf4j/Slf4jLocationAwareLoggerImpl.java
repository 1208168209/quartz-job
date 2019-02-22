package com.hongguaninfo.hgdf.core.utils.logging.slf4j;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.spi.LocationAwareLogger;

class Slf4jLocationAwareLoggerImpl implements Log {
    private static Marker MARKER = MarkerFactory.getMarker("HGDF");

    private static final String FQCN = Slf4jImpl.class.getName();
    private LocationAwareLogger logger;

    Slf4jLocationAwareLoggerImpl(LocationAwareLogger logger) {
        this.logger = logger;
    }

    public boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }

    public boolean isTraceEnabled() {
        return this.logger.isTraceEnabled();
    }

    public void error(String s, Throwable e) {
        this.logger.log(MARKER, FQCN, 40, s, null, e);
    }

    public void error(String s) {
        this.logger.log(MARKER, FQCN, 40, s, null, null);
    }

    public void debug(String s) {
        this.logger.log(MARKER, FQCN, 10, s, null, null);
    }

    public void trace(String s) {
        this.logger.log(MARKER, FQCN, 0, s, null, null);
    }

    public void warn(String s) {
        this.logger.log(MARKER, FQCN, 30, s, null, null);
    }

    public void info(String s) {
        this.logger.log(MARKER, FQCN, 20, s, null, null);
    }
}