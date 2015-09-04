package com.github.chauhraj.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.DefaultTask
import org.gradle.api.Task;
import org.gradle.api.tasks.scala.ScalaCompile;

/**
 * @author chauhraj
 */
class SlickPlugin implements Plugin<Project> {

    public static final String SLICK_CONFIG = "slick"

    void apply(Project project) {
        
        def logger = project.logger

        project.getPlugins().
        project.extensions.create(SLICK_CONFIG, SlickConfig, project, logger)
        Task schemaGenTask = project.task('generateSlickSchema', type: SlickSchemaGenerator)
        schemaGenTask.group = 'Slick Schemagen'
        schemaGenTask.description = 'Generates Slick classes from Database schema'
        
        Task compileScala = project.task('compileScala', type: ScalaCompile)
        compileScala.dependsOn(schemaGenTask)
    }
}