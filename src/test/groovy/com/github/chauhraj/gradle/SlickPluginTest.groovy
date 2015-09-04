package com.github.chauhraj.gradle

import org.junit.Test
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project

import static org.junit.Assert.*

/**
 * @author chauhraj
 */
class SlickPluginTest {

    @Test
    public void taskAddedOfTypeSlickSchemaGenerator() {
        
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'com.github.chauhraj.slick.schemagen'
        
        assertTrue(project.tasks.generateSlickSchema instanceof SlickSchemaGenerator)
    }
}