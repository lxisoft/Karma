/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.lxisoft.client.karma.api;

import com.lxisoft.client.karma.model.ApprovalStatusDTO;
import com.lxisoft.client.karma.model.CategoryDTO;
import com.lxisoft.client.karma.model.CommentDTO;
import com.lxisoft.client.karma.model.HelpDTO;
import com.lxisoft.client.karma.model.NeedDTO;
import com.lxisoft.client.karma.model.ReplyDTO;
import com.lxisoft.client.karma.model.SeverityDTO;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-22T13:19:16.116+05:30[Asia/Calcutta]")

@Api(value = "AggregateResource", description = "the AggregateResource API")
public interface AggregateResourceApi {

    @ApiOperation(value = "addComment", nickname = "addCommentUsingPOST", notes = "", response = CommentDTO.class, tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = CommentDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/comments",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<CommentDTO> addCommentUsingPOST(@ApiParam(value = "commentDTO" ,required=true )  @Valid @RequestBody CommentDTO commentDTO);


    @ApiOperation(value = "addReply", nickname = "addReplyUsingPOST", notes = "", response = ReplyDTO.class, tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = ReplyDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/replies",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<ReplyDTO> addReplyUsingPOST(@ApiParam(value = "replyDTO" ,required=true )  @Valid @RequestBody ReplyDTO replyDTO);


    @ApiOperation(value = "createUserCheck", nickname = "createUserCheckUsingPOST", notes = "", response = UserCheckDTO.class, tags={ "aggregate-resource", })
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


    @ApiOperation(value = "deleteHelp", nickname = "deleteHelpUsingDELETE", notes = "", tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(value = "/api/helps/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteHelpUsingDELETE(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "deleteNeed", nickname = "deleteNeedUsingDELETE", notes = "", tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(value = "/api/needs/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteNeedUsingDELETE(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "doDislike", nickname = "doDislikeUsingPOST", notes = "", response = UserCheckDTO.class, tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserCheckDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/user-checks/dislike",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<UserCheckDTO> doDislikeUsingPOST(@ApiParam(value = "userCheckDTO" ,required=true )  @Valid @RequestBody UserCheckDTO userCheckDTO);


    @ApiOperation(value = "doLike", nickname = "doLikeUsingPOST", notes = "", response = UserCheckDTO.class, tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserCheckDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/user-checks/like",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<UserCheckDTO> doLikeUsingPOST(@ApiParam(value = "userCheckDTO" ,required=true )  @Valid @RequestBody UserCheckDTO userCheckDTO);


    @ApiOperation(value = "getAllApprovalStatuses", nickname = "getAllApprovalStatusesUsingGET", notes = "", response = ApprovalStatusDTO.class, responseContainer = "List", tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = ApprovalStatusDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/approval-statuses",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<ApprovalStatusDTO>> getAllApprovalStatusesUsingGET(@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getAllCategories", nickname = "getAllCategoriesUsingGET", notes = "", response = CategoryDTO.class, responseContainer = "List", tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = CategoryDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/categories",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<CategoryDTO>> getAllCategoriesUsingGET(@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getAllCommentsByHelpId", nickname = "getAllCommentsByHelpIdUsingGET", notes = "", response = CommentDTO.class, responseContainer = "List", tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = CommentDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/CommentsByHelpId/{helpId}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<CommentDTO>> getAllCommentsByHelpIdUsingGET(@ApiParam(value = "helpId",required=true) @PathVariable("helpId") Long helpId,@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getAllCommentsByNeedId", nickname = "getAllCommentsByNeedIdUsingGET", notes = "", response = CommentDTO.class, responseContainer = "List", tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = CommentDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/commentsByNeedId/{needId}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<CommentDTO>> getAllCommentsByNeedIdUsingGET(@ApiParam(value = "needId",required=true) @PathVariable("needId") Long needId,@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getAllHelpsByApprovedStatus", nickname = "getAllHelpsByApprovedStatusUsingGET", notes = "", response = HelpDTO.class, responseContainer = "List", tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = HelpDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/helps/getAllHelpsByApprovedStatus/{approvalStatus}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<HelpDTO>> getAllHelpsByApprovedStatusUsingGET(@ApiParam(value = "approvalStatus",required=true) @PathVariable("approvalStatus") String approvalStatus,@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getAllHelpsByfulfilledNeedId", nickname = "getAllHelpsByfulfilledNeedIdUsingGET", notes = "", response = HelpDTO.class, responseContainer = "List", tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = HelpDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/helps/getAllHelpsByfulfilledNeedId/{fulfilledNeedId}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<HelpDTO>> getAllHelpsByfulfilledNeedIdUsingGET(@ApiParam(value = "fulfilledNeedId",required=true) @PathVariable("fulfilledNeedId") Long fulfilledNeedId,@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getAllHelps", nickname = "getAllHelpsUsingGET", notes = "", response = HelpDTO.class, responseContainer = "List", tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = HelpDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/helps",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<HelpDTO>> getAllHelpsUsingGET(@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getAllNeedsByApprovedStatus", nickname = "getAllNeedsByApprovedStatusUsingGET", notes = "", response = NeedDTO.class, responseContainer = "List", tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = NeedDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/needs/getAllNeedsByApprovedStatus/{approvalStatus}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<NeedDTO>> getAllNeedsByApprovedStatusUsingGET(@ApiParam(value = "approvalStatus",required=true) @PathVariable("approvalStatus") String approvalStatus,@ApiParam(value = "eagerload", defaultValue = "false") @Valid @RequestParam(value = "eagerload", required = false, defaultValue="false") Boolean eagerload,@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getAllNeedsBySeverityId", nickname = "getAllNeedsBySeverityIdUsingGET", notes = "", response = NeedDTO.class, responseContainer = "List", tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = NeedDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/needs/getNeedsBySeverityId/{severityId}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<NeedDTO>> getAllNeedsBySeverityIdUsingGET(@ApiParam(value = "severityId",required=true) @PathVariable("severityId") Long severityId,@ApiParam(value = "eagerload", defaultValue = "false") @Valid @RequestParam(value = "eagerload", required = false, defaultValue="false") Boolean eagerload,@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getAllNeeds", nickname = "getAllNeedsUsingGET", notes = "", response = NeedDTO.class, responseContainer = "List", tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = NeedDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/needs",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<NeedDTO>> getAllNeedsUsingGET(@ApiParam(value = "eagerload", defaultValue = "false") @Valid @RequestParam(value = "eagerload", required = false, defaultValue="false") Boolean eagerload,@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getAllRepliesByCommentId", nickname = "getAllRepliesByCommentIdUsingGET", notes = "", response = ReplyDTO.class, responseContainer = "List", tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = ReplyDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/getAllRepliesByCommentId/{commentId}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<ReplyDTO>> getAllRepliesByCommentIdUsingGET(@ApiParam(value = "commentId",required=true) @PathVariable("commentId") Long commentId,@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getAllSeverities", nickname = "getAllSeveritiesUsingGET", notes = "", response = SeverityDTO.class, responseContainer = "List", tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = SeverityDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/severities",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<SeverityDTO>> getAllSeveritiesUsingGET(@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getAllUserChecksByCheckedNeedId", nickname = "getAllUserChecksByCheckedNeedIdUsingGET", notes = "", response = UserCheckDTO.class, responseContainer = "List", tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = UserCheckDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/user-checks/getAllUserChecksByCheckedNeedId/{checkedNeedId}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<UserCheckDTO>> getAllUserChecksByCheckedNeedIdUsingGET(@ApiParam(value = "checkedNeedId",required=true) @PathVariable("checkedNeedId") Long checkedNeedId,@ApiParam(value = "") @Valid @RequestParam(value = "offset", required = false) Long offset,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "") @Valid @RequestParam(value = "paged", required = false) Boolean paged,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort,@ApiParam(value = "") @Valid @RequestParam(value = "sort.sorted", required = false) Boolean sortSorted,@ApiParam(value = "") @Valid @RequestParam(value = "sort.unsorted", required = false) Boolean sortUnsorted,@ApiParam(value = "") @Valid @RequestParam(value = "unpaged", required = false) Boolean unpaged);


    @ApiOperation(value = "getApprovalStatus", nickname = "getApprovalStatusUsingGET", notes = "", response = ApprovalStatusDTO.class, tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = ApprovalStatusDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/approval-statuses/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<ApprovalStatusDTO> getApprovalStatusUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "getHelp", nickname = "getHelpUsingGET", notes = "", response = HelpDTO.class, tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = HelpDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/helps/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<HelpDTO> getHelpUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "getNeed", nickname = "getNeedUsingGET", notes = "", response = NeedDTO.class, tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = NeedDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/needs/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<NeedDTO> getNeedUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "helpNeedy", nickname = "helpNeedyUsingPOST", notes = "", response = HelpDTO.class, tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = HelpDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/helps",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<HelpDTO> helpNeedyUsingPOST(@ApiParam(value = "helpDTO" ,required=true )  @Valid @RequestBody HelpDTO helpDTO);


    @ApiOperation(value = "markingGenuinenes", nickname = "markingGenuinenesUsingPOST", notes = "", response = UserCheckDTO.class, tags={ "aggregate-resource", })
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


    @ApiOperation(value = "PostNeed", nickname = "postNeedUsingPOST", notes = "", response = NeedDTO.class, tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = NeedDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/needs",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<NeedDTO> postNeedUsingPOST(@ApiParam(value = "needDTO" ,required=true )  @Valid @RequestBody NeedDTO needDTO);


    @ApiOperation(value = "updateHelp", nickname = "updateHelpUsingPUT", notes = "", response = HelpDTO.class, tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = HelpDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/helps",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.PUT)
    ResponseEntity<HelpDTO> updateHelpUsingPUT(@ApiParam(value = "helpDTO" ,required=true )  @Valid @RequestBody HelpDTO helpDTO);


    @ApiOperation(value = "updateNeedApprovalStatus", nickname = "updateNeedApprovalStatusUsingPUT", notes = "", response = NeedDTO.class, tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = NeedDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/needsApprovalStatus",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.PUT)
    ResponseEntity<NeedDTO> updateNeedApprovalStatusUsingPUT(@ApiParam(value = "needDTO" ,required=true )  @Valid @RequestBody NeedDTO needDTO);


    @ApiOperation(value = "updateNeed", nickname = "updateNeedUsingPUT", notes = "", response = NeedDTO.class, tags={ "aggregate-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = NeedDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/needs",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.PUT)
    ResponseEntity<NeedDTO> updateNeedUsingPUT(@ApiParam(value = "needDTO" ,required=true )  @Valid @RequestBody NeedDTO needDTO);


    @ApiOperation(value = "updateUserCheck", nickname = "updateUserCheckUsingPUT", notes = "", response = UserCheckDTO.class, tags={ "aggregate-resource", })
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
