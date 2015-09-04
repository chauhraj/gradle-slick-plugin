package com.github.chauhraj.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction;

import slick.codegen.SourceCodeGenerator$;



class SlickSchemaGenerator extends DefaultTask {

    def SourceCodeGenerator$ generator  = SourceCodeGenerator$.MODULE$
    
    @TaskAction
    def generateSchema () {
        def logger = project.logger
        
        logger.quiet("Generating Schema for Slick")        
        logger.quiet("---------------------------")        
        SlickConfig config = project.extensions.getByType(SlickConfig)
        logger.quiet("In Package  :{}", config.pkg)
        logger.quiet("Slick Driver:{}", config.slickDriver)
        logger.quiet("JDBC  Driver:{}", config.jdbcDriver)
        logger.quiet("JDBC  URL:{}",    config.jdbcUrl)
        logger.quiet("DB User   :{}",     config.user)
        logger.quiet("DB Passwd :{}",     config.password)
        
        SourceCodeGenerator$.MODULE$.main(config.slickDriver, 
                       config.jdbcDriver,
                       config.jdbcUrl,
                       config.outputFolder.absolutePath,
                       config.pkg,
                       config.user,
                       config.password)
        logger.quiet("Successfully generated the Schema")
    }
}
