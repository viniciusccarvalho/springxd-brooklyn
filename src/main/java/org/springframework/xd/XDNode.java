package org.springframework.xd;

import brooklyn.config.ConfigKey;
import brooklyn.entity.basic.ConfigKeys;
import brooklyn.entity.basic.SoftwareProcess;
import brooklyn.entity.proxying.ImplementedBy;
import brooklyn.event.basic.BasicAttributeSensorAndConfigKey;
import brooklyn.util.flags.SetFromFlag;

/**
 * Created by vcarvalho on 3/2/15.
 */
@ImplementedBy(XDNodeImpl.class)
public interface XDNode extends SoftwareProcess {

    @SetFromFlag("downloadUrl")
    public static final BasicAttributeSensorAndConfigKey<String> DOWNLOAD_URL = new BasicAttributeSensorAndConfigKey<String>(
            SoftwareProcess.DOWNLOAD_URL, "http://repo.spring.io/release/org/springframework/xd/spring-xd/${version}/spring-xd-${version}-dist.zip");


    @SetFromFlag("version")
    public static final ConfigKey<String> SUGGESTED_VERSION = ConfigKeys.newConfigKeyWithDefault(SoftwareProcess.SUGGESTED_VERSION, "1.1.0.RELEASE");

    @SetFromFlag("mysqlHost")
    public static final ConfigKey<String> MYSQL_HOST = ConfigKeys.newStringConfigKey("mysql.host", "Mysql Host");

    @SetFromFlag("mysqlUser")
    public static final ConfigKey<String> MYSQL_USER = ConfigKeys.newStringConfigKey("mysql.user", "Mysql User");

    @SetFromFlag("mysqlPwd")
    public static final ConfigKey<String> MYSQL_PWD = ConfigKeys.newStringConfigKey("mysql.password", "Mysql password");


    @SetFromFlag("mysqlPort")
    public static final ConfigKey<Integer> MYSQL_PORT = ConfigKeys.newIntegerConfigKey("mysql.port", "Mysql Port");

    @SetFromFlag("redisHost")
    public static final ConfigKey<String> REDIS_HOST = ConfigKeys.newStringConfigKey("redis.host", "Redis Host");

    @SetFromFlag("redisPort")
    public static final ConfigKey<Integer> REDIS_PORT = ConfigKeys.newIntegerConfigKey("redis.port", "Redis Port");


    @SetFromFlag("zkHost")
    public static final ConfigKey<String> ZK_HOST = ConfigKeys.newStringConfigKey("zookeeper.host", "Redis Host");

    @SetFromFlag("zkPort")
    public static final ConfigKey<Integer> ZK_PORT = ConfigKeys.newIntegerConfigKey("zookeeper.port", "Redis Port");






}
