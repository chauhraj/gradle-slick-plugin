package com.github.chauhraj.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.plugins.scala.ScalaPlugin
import org.gradle.api.tasks.scala.ScalaCompile

import com.github.chauhraj.gradle.config.SlickConfig
import com.github.chauhraj.gradle.internal.SlickSchemaGenerator
import com.github.chauhraj.gradle.task.DefaultSchemaGenerator

/**
 * @author chauhraj
 */
class SlickSchemaGenPlugin implements Plugin<Project> {

    public static final String SLICK_CONFIG = "slick"
    
    def Project project
    
    void apply(Project project) {
        this.project = project 
        def logger = project.logger

        SlickConfig config = project.extensions.create(SLICK_CONFIG, SlickConfig, project)
        config.schemaGenerator = new SlickSchemaGenerator()
        
        Task schemaGenTask = project.task('generateSlickSchema', type: DefaultSchemaGenerator)
        schemaGenTask.group = 'Slick Schemagen'
        schemaGenTask.description = 'Generates Slick classes from Database schema'
        
        if(!project.getPlugins().hasPlugin(SlickSchemaGenPlugin)) {
            project.getPluginManager().apply(ScalaPlugin)
        }
        
        project.getTasks().withType(ScalaCompile) {
            ScalaCompile compileScala ->
            compileScala.dependsOn(schemaGenTask)
        }
    }
}