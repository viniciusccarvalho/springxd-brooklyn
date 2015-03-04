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
   BasicAttributeSensorAndConfigKey<String> DOWNLOAD_URL = new BasicAttributeSensorAndConfigKey<String>(
            SoftwareProcess.DOWNLOAD_URL, "http://repo.spring.io/release/org/springframework/xd/spring-xd/${version}/spring-xd-${version}-dist.zip");


    @SetFromFlag("version")
     ConfigKey<String> SUGGESTED_VERSION = ConfigKeys.newConfigKeyWithDefault(SoftwareProcess.SUGGESTED_VERSION, "1.1.0.RELEASE");

    @SetFromFlag("mysqlHost")
     ConfigKey<String> MYSQL_HOST = ConfigKeys.newStringConfigKey("mysql.host", "Mysql Host");

    @SetFromFlag("mysqlUser")
     ConfigKey<String> MYSQL_USER = ConfigKeys.newStringConfigKey("mysql.user", "Mysql User");

    @SetFromFlag("mysqlPwd")
     ConfigKey<String> MYSQL_PWD = ConfigKeys.newStringConfigKey("mysql.password", "Mysql password");


    @SetFromFlag("mysqlPort")
     ConfigKey<Integer> MYSQL_PORT = ConfigKeys.newIntegerConfigKey("mysql.port", "Mysql Port");

    @SetFromFlag("redisHost")
     ConfigKey<String> REDIS_HOST = ConfigKeys.newStringConfigKey("redis.host", "Redis Host");

    @SetFromFlag("redisPort")
     ConfigKey<Integer> REDIS_PORT = ConfigKeys.newIntegerConfigKey("redis.port", "Redis Port");


    @SetFromFlag("zkHost")
     ConfigKey<String> ZK_HOST = ConfigKeys.newStringConfigKey("zookeeper.host", "Redis Host");

    @SetFromFlag("zkPort")
     ConfigKey<Integer> ZK_PORT = ConfigKeys.newIntegerConfigKey("zookeeper.port", "Redis Port");






}
