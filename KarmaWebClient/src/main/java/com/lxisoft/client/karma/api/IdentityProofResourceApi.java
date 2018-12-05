/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.lxisoft.client.karma.api;

import com.lxisoft.client.karma.model.IdentityProofDTO;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-12-06T01:17:31.492812+05:30[Asia/Calcutta]")

@Api(value = "IdentityProofResource", description = "the IdentityProofResource API")
public interface IdentityProofResourceApi {

    @ApiOperation(value = "createIdentityProof", nickname = "createIdentityProofUsingPOST", notes = "", response = IdentityProofDTO.class, tags={ "identity-proof-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = IdentityProofDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/identity-proofs",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<IdentityProofDTO> createIdentityProofUsingPOST(@ApiParam(value = "identityProofDTO" ,required=true )  @Valid @RequestBody IdentityProofDTO identityProofDTO);


    @ApiOperation(value = "deleteIdentityProof", nickname = "deleteIdentityProofUsingDELETE", notes = "", tags={ "identity-proof-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(value = "/api/identity-proofs/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteIdentityProofUsingDELETE(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "getAllIdentityProofs", nickname = "getAllIdentityProofsUsingGET", notes = "", response = IdentityProofDTO.class, responseContainer = "List", tags={ "identity-proof-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = IdentityProofDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/identity-proofs",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<IdentityProofDTO>> getAllIdentityProofsUsingGET(@ApiParam(value = "filter") @Valid @RequestParam(value = "filter", required = false) String filter,@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getIdentityProof", nickname = "getIdentityProofUsingGET", notes = "", response = IdentityProofDTO.class, tags={ "identity-proof-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = IdentityProofDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/identity-proofs/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<IdentityProofDTO> getIdentityProofUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "updateIdentityProof", nickname = "updateIdentityProofUsingPUT", notes = "", response = IdentityProofDTO.class, tags={ "identity-proof-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = IdentityProofDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/identity-proofs",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.PUT)
    ResponseEntity<IdentityProofDTO> updateIdentityProofUsingPUT(@ApiParam(value = "identityProofDTO" ,required=true )  @Valid @RequestBody IdentityProofDTO identityProofDTO);

}
