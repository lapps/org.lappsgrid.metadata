package org.lappsgrid.metadata

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import groovy.transform.CompileStatic

/**
 * @author Keith Suderman
 */
@CompileStatic
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(["schema","name","version","description","vendor","allow","license","url", "parameters", "requires", "produces"])
class ServiceMetadata {

    public static final String DEFAULT_SCHEMA_URL = 'http://vocab.lappsgrid.org/schema/service-schema-1.0.0.json'

    /** The JSON schema that describes the JSON format. */
    @JsonProperty('$schema')
    String schema

    /** A human readable name for the service. */
    String name

    /** Name or URI of the organization providing the service. */
    String vendor

    /**
     * The service version number in [major].[minor].[revision] format with
     * an optional trailing qualifier. E.G. 1.1.0-SNAPSHOT
     */
    String version

    /**
     * A plain text description of the service or the URL to an online
     * description.
     */
    String description

    /**
     * Permitted uses of this service.
     * <p>
     * This field is actually a URI that describes the allowable usages, e.g. commerial,
     * research, etc.  If this field is omitted then it is assumed that the service
     * can be used for any purpose.
     */
    String allow

    /**
     * The license for this service.
     */
    String license

    /**
     * The full URL used to invoke the service.
     */
    String url

    /**
     * Descriptions of the parameters required by the service.
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List parameters = []

    /**
     * Output format specification.  The output specification consists of:
     * <ul>
     * <li> the character encoding
     * <li> the language produced.
     * <li> the document format (ContentType)
     * <li> annotations produced.
     * </ul>
     */
    IOSpecification produces = new IOSpecification()

    /**
     * The input requirements of the service.  The input specification consists of:
     * <ul>
     * <li> the character encoding
     * <li> the input language accepted
     * <li> the document format(s) (ContentTypes) accepted.
     * <li> required annotations.
     * </ul>
     */
    IOSpecification requires = new IOSpecification()

    public ServiceMetadata() {
        this.schema = DEFAULT_SCHEMA_URL
    }
//
//    public ServiceMetadata(File file) {
//        this(file.text)
//    }
//

    public ServiceMetadata(Map map) {
        this.schema = map.schema
        this.name = map.name
        this.vendor = map.vendor
        this.version = map.version
        this.description = map.description
        this.allow = map.allow
        this.license = map.license
        this.url = map.url
        this.parameters = (List) map.parameters
        this.requires = new IOSpecification((Map)map.requires)
        this.produces = new IOSpecification((Map)map.produces)
    }

}
