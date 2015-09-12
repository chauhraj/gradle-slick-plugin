package com.github.chauhraj.gradle.internal

import com.github.chauhraj.gradle.config.SlickConfig;

class DBSchema {
    def String name
    
    DBInfo dbInfo
    
    SlickConfig config
    
    DBSchema(DBInfo dbInfo) {
        this.dbInfo = dbInfo
        config = dbInfo.project.extensions.getByType(SlickConfig)
    }
    
    def String packageName () { "${config.basePackage}.${name.toLowerCase()}" }
    def String jdbcUrl () { "jdbc:${dbInfo.dbType}://${dbInfo.host}:${dbInfo.port}/${name.toLowerCase()}" }

}
