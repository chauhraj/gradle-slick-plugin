package com.github.chauhraj.gradle.internal

import org.gradle.api.Project;

class DBInfo {
    
    def String dbType
    def String host
    def String port
    def String user
    def String password
    
    def List schemas = []
    
    private Project project
    
    DBInfo(Project project) {
        this.project = project 
    }
    
    def String driver() {
       switch(dbType) {
          case 'mysql' : return 'slick.driver.MySQLDriver'
          default : throw new UnsupportedOperationException("Currently, only supports MySql. Asked for '${dbType}'"); 
       } 
    }

        
    def String jdbcDriver() {
        switch(dbType) {
           case 'mysql' : return 'com.mysql.jdbc.Driver'
           default : throw new UnsupportedOperationException("Currently, only supports MySql. Asked for '${dbType}'"); 
        }
     }
 
    def schema (Closure c) {
        DBSchema sc = new DBSchema(this)
        project.configure(sc, c)
        schemas.push(sc)
    } 
}