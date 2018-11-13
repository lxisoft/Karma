/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.lxisoft.client.karma.api;

import com.lxisoft.client.karma.model.SeverityDTO;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
<<<<<<< HEAD
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-09T14:03:13.373+05:30[Asia/Calcutta]")
=======
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-11T21:56:15.510+05:30[Asia/Calcutta]")
>>>>>>> f9e1ba14bcabbd447198cad5bb2290f500c4d3d3

@Api(value = "SeverityResource", description = "the SeverityResource API")
public interface SeverityResourceApi {

    @ApiOperation(value = "createSeverity", nickname = "createSeverityUsingPOST", notes = "", response = SeverityDTO.class, tags={ "severity-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = SeverityDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/severities",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<SeverityDTO> createSeverityUsingPOST(@ApiParam(value = "severityDTO" ,required=true )  @Valid @RequestBody SeverityDTO severityDTO);


    @ApiOperation(value = "deleteSeverity", nickname = "deleteSeverityUsingDELETE", notes = "", tags={ "severity-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(value = "/api/severities/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteSeverityUsingDELETE(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "getAllSeverities", nickname = "getAllSeveritiesUsingGET", notes = "", response = SeverityDTO.class, responseContainer = "List", tags={ "severity-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = SeverityDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/severities",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<SeverityDTO>> getAllSeveritiesUsingGET(@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getSeverityBySeverityLevel", nickname = "getSeverityBySeverityLevelUsingGET", notes = "", response = SeverityDTO.class, tags={ "severity-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = SeverityDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/getSeverityBySeverityLevel/{severityLevel}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<SeverityDTO> getSeverityBySeverityLevelUsingGET(@ApiParam(value = "severityLevel",required=true) @PathVariable("severityLevel") String severityLevel);


    @ApiOperation(value = "getSeverity", nickname = "getSeverityUsingGET", notes = "", response = SeverityDTO.class, tags={ "severity-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = SeverityDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/severities/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<SeverityDTO> getSeverityUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "updateSeverity", nickname = "updateSeverityUsingPUT", notes = "", response = SeverityDTO.class, tags={ "severity-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = SeverityDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/severities",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.PUT)
    ResponseEntity<SeverityDTO> updateSeverityUsingPUT(@ApiParam(value = "severityDTO" ,required=true )  @Valid @RequestBody SeverityDTO severityDTO);

}