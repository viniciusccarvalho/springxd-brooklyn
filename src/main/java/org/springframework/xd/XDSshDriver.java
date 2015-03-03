package org.springframework.xd;

import brooklyn.entity.basic.AbstractSoftwareProcessSshDriver;
import brooklyn.entity.basic.Entities;
import brooklyn.entity.drivers.downloads.DownloadResolver;
import brooklyn.location.basic.SshMachineLocation;
import brooklyn.util.ssh.BashCommands;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by vcarvalho on 3/2/15.
 */
public class XDSshDriver extends AbstractSoftwareProcessSshDriver implements XDDriver {

    private static final Logger log = LoggerFactory.getLogger(XDSshDriver.class);

    protected String getDefaultUnpackedDirectoryName() {
        return "spring-xd-"+getVersion();
    }

    public XDSshDriver(XDNodeImpl xdnode, SshMachineLocation machine){
        super(xdnode,machine);
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void stop() {

    }

    @Override
    public void install() {
        log.debug("Downloading Spring XD");
        DownloadResolver resolver = Entities.newDownloader(this);
        List<String> urls = resolver.getTargets();
        String saveAs = resolver.getFilename();
        setExpandedInstallDir(getInstallDir() + "/" + resolver.getUnpackedDirectoryName(getDefaultUnpackedDirectoryName()));
        List<String> commands = ImmutableList.<String>builder()
                                .addAll(BashCommands.commandsToDownloadUrlsAs(urls,saveAs))
                                .add(BashCommands.INSTALL_ZIP)
                                .add("unzip " + saveAs)
                                .build();
        newScript(INSTALLING)
                .failOnNonZeroResultCode()
                .body.append(commands)
                .execute();


    }

    @Override
    public void customize() {

    }

    @Override
    public void launch() {
        List<String> commands = ImmutableList.<String>builder().add(String.format("%s/xd/bin/xd-singlenode", getExpandedInstallDir())).build();
        newScript(INSTALLING)
                .failOnNonZeroResultCode()
                .body.append(commands)
                .execute();
    }
}
