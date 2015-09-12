package com.github.chauhraj.gradle.config

import javax.inject.Inject

import org.gradle.api.Project

import com.github.chauhraj.gradle.internal.DBInfo;
import com.github.chauhraj.gradle.internal.DBSchema
import com.github.chauhraj.gradle.internal.SchemaGenerator;


class SlickConfig {
    
    private Project project
    
    def List<DBInfo> dbInfos = []
    
    def String basePackage
    def String outputFolder = 'build/generated-sources/scala'
    
    def SchemaGenerator schemaGenerator  
    
    @Inject
    SlickConfig(Project project) {
        this.project = project
    }
    
    def mysql (Closure c) {
        DBInfo dbInfo = new DBInfo(project)
        dbInfo.dbType = 'mysql'
        project.configure(dbInfo, c)
        dbInfos.push(dbInfo)
    }
}
