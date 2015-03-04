package org.springframework.xd;

import brooklyn.entity.basic.AbstractSoftwareProcessSshDriver;
import brooklyn.entity.basic.Entities;
import brooklyn.entity.drivers.downloads.DownloadResolver;
import brooklyn.location.basic.SshMachineLocation;
import brooklyn.util.collections.MutableMap;
import brooklyn.util.os.Os;
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
        return newScript(MutableMap.of(USE_PID_FILE, getPidFile()), CHECK_RUNNING).execute() == 0;
    }


    @Override
    public void stop() {
        newScript(MutableMap.of("usePidFile", getPidFile()), STOPPING).execute();
    }

    @Override
    public void kill(){
        newScript(MutableMap.of("usePidFile", getPidFile()), KILLING).execute();
    }

    @Override
    public void install() {
        log.info("Downloading Spring XD");
        DownloadResolver resolver = Entities.newDownloader(this);
        List<String> urls = resolver.getTargets();
        String saveAs = resolver.getFilename();
        setExpandedInstallDir(getInstallDir() + "/" + resolver.getUnpackedDirectoryName(getDefaultUnpackedDirectoryName()));
        List<String> commands = ImmutableList.<String>builder()
                                .add(BashCommands.installJavaLatestOrWarn())
                                .addAll(BashCommands.commandsToDownloadUrlsAs(urls, saveAs))
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


    public String getPidFile() { return Os.mergePathsUnix(getRunDir(), "xd.pid"); }

    @Override
    public void launch() {
        entity.setAttribute(XDNode.PID_FILE, getPidFile());
        List<String> commands = ImmutableList.<String>builder().add(String.format("nohup %s/xd/bin/xd-singlenode > console.out 2>&1 &", getExpandedInstallDir())).build();
        log.info("Launching Spring XD on {}", commands.toString());
        newScript(MutableMap.of("usePidFile",getPidFile()),LAUNCHING)
                .failOnNonZeroResultCode()
                .body.append(commands)
                .execute();
    }
}
