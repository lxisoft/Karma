/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.lxisoft.client.karma.api;

import com.lxisoft.client.karma.model.UserCheckDTO;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-11T21:56:15.510+05:30[Asia/Calcutta]")

@Api(value = "UserCheckResource", description = "the UserCheckResource API")
public interface UserCheckResourceApi {

    @ApiOperation(value = "createUserCheck", nickname = "createUserCheckUsingPOST", notes = "", response = UserCheckDTO.class, tags={ "user-check-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserCheckDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/user-checks",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<UserCheckDTO> createUserCheckUsingPOST(@ApiParam(value = "userCheckDTO" ,required=true )  @Valid @RequestBody UserCheckDTO userCheckDTO);


    @ApiOperation(value = "deleteUserCheck", nickname = "deleteUserCheckUsingDELETE", notes = "", tags={ "user-check-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(value = "/api/user-checks/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteUserCheckUsingDELETE(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "getAllUserChecksByCheckedNeedId", nickname = "getAllUserChecksByCheckedNeedIdUsingGET", notes = "", response = UserCheckDTO.class, responseContainer = "List", tags={ "user-check-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserCheckDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/user-checks/getAllUserChecksByCheckedNeedId/{checkedNeedId}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<UserCheckDTO>> getAllUserChecksByCheckedNeedIdUsingGET(@ApiParam(value = "checkedNeedId",required=true) @PathVariable("checkedNeedId") Long checkedNeedId,@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getAllUserChecks", nickname = "getAllUserChecksUsingGET", notes = "", response = UserCheckDTO.class, responseContainer = "List", tags={ "user-check-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserCheckDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/user-checks",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<UserCheckDTO>> getAllUserChecksUsingGET(@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getUserCheck", nickname = "getUserCheckUsingGET", notes = "", response = UserCheckDTO.class, tags={ "user-check-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserCheckDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/user-checks/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<UserCheckDTO> getUserCheckUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "markingGenuinenes", nickname = "markingGenuinenesUsingPOST", notes = "", response = UserCheckDTO.class, tags={ "user-check-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserCheckDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/user-checks/markingGenuinenes",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<UserCheckDTO> markingGenuinenesUsingPOST(@ApiParam(value = "userCheckDTO" ,required=true )  @Valid @RequestBody UserCheckDTO userCheckDTO);


    @ApiOperation(value = "updateUserCheck", nickname = "updateUserCheckUsingPUT", notes = "", response = UserCheckDTO.class, tags={ "user-check-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserCheckDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/user-checks",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.PUT)
    ResponseEntity<UserCheckDTO> updateUserCheckUsingPUT(@ApiParam(value = "userCheckDTO" ,required=true )  @Valid @RequestBody UserCheckDTO userCheckDTO);

}
