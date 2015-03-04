package org.springframework.xd;

import brooklyn.entity.basic.SoftwareProcessImpl;

/**
 * Created by vcarvalho on 3/2/15.
 */
public class XDNodeImpl extends SoftwareProcessImpl implements XDNode{


    public XDNodeImpl(){}

    @Override
    protected void connectSensors() {
        super.connectSensors();
        connectServiceUpIsRunning();
    }

    @Override
    public Class getDriverInterface() {
        return XDDriver.class;
    }


}
