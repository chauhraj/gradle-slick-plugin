package com.github.chauhraj.gradle

import java.io.File
import javax.inject.Inject;


import org.gradle.api.Project;
import org.gradle.logging.internal.slf4j.OutputEventListenerBackedLogger;

class SlickConfig {
    
    String slickDriver
    String jdbcDriver
    String jdbcUrl
    File   outputFolder  
    String pkg
    String user
    String password
    
    @Inject
    SlickConfig(Project project, OutputEventListenerBackedLogger logger) {
        logger.quiet('Slick config')
        outputFolder = new File(project.projectDir, "src/main/scala")   
    }
}
