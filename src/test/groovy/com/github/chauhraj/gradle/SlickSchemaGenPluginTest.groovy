package com.github.chauhraj.gradle

import static org.junit.Assert.*
import static org.mockito.Mockito.*
import static org.hamcrest.CoreMatchers.*

import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.scala.ScalaPlugin
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner

import com.github.chauhraj.gradle.config.SlickConfig
import com.github.chauhraj.gradle.internal.SchemaGenerator;
import com.github.chauhraj.gradle.internal.DBSchema;
import com.github.chauhraj.gradle.task.DefaultSchemaGenerator

/**
 * @author chauhraj
 */

@RunWith(MockitoJUnitRunner)
class SlickSchemaGenPluginTest {

    private SlickSchemaGenPlugin plugin
    
    @Mock
    private SchemaGenerator schemaGenerator
    
    @Before
    public void setup() {
        plugin = new SlickSchemaGenPlugin()
        
        Project project = ProjectBuilder.builder().build()

        plugin.apply( project )
        
    }
    
    @Test
    public void taskAddedOfTypeSlickSchemaGenerator() {
        assertTrue(plugin.project.tasks.generateSlickSchema instanceof DefaultSchemaGenerator)
    }
    
    @Test
    public void pluginShouldAlsoApplyScalaAndJavaPlugin() {
        assertTrue(plugin.project.getPlugins().hasPlugin(ScalaPlugin))
        assertTrue(plugin.project.getPlugins().hasPlugin(JavaPlugin))
    }

    @Test
    public void schemaGeneratedSuccessfullyForMySql() {
        
        SlickConfig config = plugin.project.extensions.getByType(SlickConfig)
        config.basePackage = "com.github.chauhraj.samples"
        config.outputFolder = 'build/generated-sources/scala' 
        config.mysql {
           host = "localhost"
           port = "3306"
           user = 'root'
           password = 'root123'
           schema {
               name = 'sakila'
           }
        }
        
        config.schemaGenerator = schemaGenerator
        DefaultSchemaGenerator schemaGenTask = plugin.project.getTasks().getByName(/generateSlickSchema/)
        schemaGenTask.generateSchema()
        
        def ArgumentCaptor<DBSchema> paramsCaptor = ArgumentCaptor.forClass(DBSchema) 
        verify(schemaGenerator, times(1)).generateSchema(paramsCaptor.capture())
        DBSchema schema = paramsCaptor.getValue()
        assertNotNull(schema)
        assertThat(schema.name, is("sakila"))
        assertThat(schema.dbInfo.dbType, is("mysql"))
    }

//    @Test
//    public void generatedSourcesShouldBePartOfScalaOrJavaSourceSet() {
//        fail("Not implemented")
//    }
}