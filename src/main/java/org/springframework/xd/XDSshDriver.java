package org.springframework.xd;

import brooklyn.entity.basic.AbstractSoftwareProcessSshDriver;

/**
 * Created by vcarvalho on 3/2/15.
 */
public class XDSshDriver extends AbstractSoftwareProcessSshDriver implements XDDriver {
    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void stop() {

    }

    @Override
    public void install() {

    }

    @Override
    public void customize() {

    }

    @Override
    public void launch() {

    }
}
