package com.github.chauhraj.gradle.task

import org.gradle.api.DefaultTask
import org.gradle.api.file.FileTreeElement
import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.TaskAction

import com.github.chauhraj.gradle.config.SlickConfig
import com.github.chauhraj.gradle.internal.DBInfo
import com.github.chauhraj.gradle.internal.DBSchema



class DefaultSchemaGenerator extends DefaultTask {

    @TaskAction
    def generateSchema () {
        def logger = project.logger
        
        SlickConfig config = project.extensions.getByType(SlickConfig)
        config.dbInfos.each {
            DBInfo dbInfo ->
            dbInfo.schemas.each {
                DBSchema schema ->
                logger.info("   Generating Slick '{}' classes for Schema '{}' for Slick", schema.dbInfo.dbType, schema.name)
                logger.info("------------------------------------------------------------------")
                config.schemaGenerator.generateSchema(schema)
                logger.info("Successfully generated")
                
            }
        }
        
        project.convention.getPlugin(JavaPluginConvention.class).sourceSets.all { SourceSet sourceSet ->
            sourceSet.scala.srcDir { project.file(config.outputFolder) }
        }    
     }
}
