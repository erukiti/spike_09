package org.erukiti.spike_09;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import java.io.File;
import java.io.IOException;

public class Validator {
    private JsonSchema schema;

    public Validator(String filename) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode schemaNode = mapper.readTree(new File(filename));

//            URITranslatorConfiguration tconf = URITranslatorConfiguration.newBuilder().setNamespace("resource:/sch/").freeze();
//            LoadingConfiguration lcfg = LoadingConfiguration.newBuilder().setURITranslatorConfiguration(tconf).freeze();

//            LoadingConfiguration lcfg = LoadingConfiguration.newBuilder().preloadSchema("resource:/schemata/person", schemaNode).freeze();
//            JsonSchemaFactory factory = JsonSchemaFactory.newBuilder().setLoadingConfiguration(lcfg).freeze();


            JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            this.schema = factory.getJsonSchema(schemaNode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProcessingException e) {
            e.printStackTrace();
        }
    }

    public boolean isValidated(String filename) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode target = mapper.readTree(new File(filename));
            ProcessingReport report = schema.validate(target);
            System.out.println(report);
            return report.isSuccess();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (ProcessingException e) {
            e.printStackTrace();
            return false;
        }
    }

}
