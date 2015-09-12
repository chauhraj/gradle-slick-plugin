package com.github.chauhraj.gradle.internal

import com.github.chauhraj.gradle.config.SlickConfig;

import slick.codegen.SourceCodeGenerator$;

class SlickSchemaGenerator implements SchemaGenerator {

    def SourceCodeGenerator$ generator  = SourceCodeGenerator$.MODULE$
    
    @Override
    public void generateSchema(DBSchema p) {
        
        generator.main(p.dbInfo.driver(),
            p.dbInfo.jdbcDriver(),
            p.jdbcUrl(),
            p.config.outputFolder,
            p.packageName(),
            p.dbInfo.user,
            p.dbInfo.password)

    }

}
