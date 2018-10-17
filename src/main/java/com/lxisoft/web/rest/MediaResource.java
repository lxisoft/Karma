package com.lxisoft.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.MediaService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;
import com.lxisoft.service.dto.MediaDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Media.
 */
@RestController
@RequestMapping("/api")
public class MediaResource {

    private final Logger log = LoggerFactory.getLogger(MediaResource.class);

    private static final String ENTITY_NAME = "karmaMedia";

    private final MediaService mediaService;

    public MediaResource(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    /**
     * POST  /media : Create a new media.
     *
     * @param mediaDTO the mediaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mediaDTO, or with status 400 (Bad Request) if the media has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws IOException 
     */
    @PostMapping("/media")
    @Timed
    public ResponseEntity<MediaDTO> createMedia(@RequestBody MediaDTO mediaDTO) throws URISyntaxException, IOException {
        log.debug("REST request to save Media : {}", mediaDTO);
        if (mediaDTO.getId() != null) {
            throw new BadRequestAlertException("A new media cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MediaDTO result = mediaService.save(mediaDTO);
        return ResponseEntity.created(new URI("/api/media/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /media : Updates an existing media.
     *
     * @param mediaDTO the mediaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mediaDTO,
     * or with status 400 (Bad Request) if the mediaDTO is not valid,
     * or with status 500 (Internal Server Error) if the mediaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws IOException 
     */
    @PutMapping("/media")
    @Timed
    public ResponseEntity<MediaDTO> updateMedia(@RequestBody MediaDTO mediaDTO) throws URISyntaxException, IOException {
        log.debug("REST request to update Media : {}", mediaDTO);
        if (mediaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MediaDTO result = mediaService.save(mediaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mediaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /media : get all the media.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of media in body
     */
    @GetMapping("/media")
    @Timed
    public ResponseEntity<List<MediaDTO>> getAllMedia(Pageable pageable) {
        log.debug("REST request to get a page of Media");
        Page<MediaDTO> page = mediaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/media");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /media/:id : get the "id" media.
     *
     * @param id the id of the mediaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mediaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/media/{id}")
    @Timed
    public ResponseEntity<MediaDTO> getMedia(@PathVariable Long id) {
        log.debug("REST request to get Media : {}", id);
        Optional<MediaDTO> mediaDTO = mediaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mediaDTO);
    }

    /**
     * DELETE  /media/:id : delete the "id" media.
     *
     * @param id the id of the mediaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/media/{id}")
    @Timed
    public ResponseEntity<Void> deleteMedia(@PathVariable Long id) {
        log.debug("REST request to delete Media : {}", id);
        mediaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    /**
     *
     * @param fileName the id of the mediaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mediaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/media/getMedia/{fileName}")
    @Timed
    public ResponseEntity<MediaDTO> getMedia(@PathVariable String fileName) {
        log.debug("REST request to get Media : {}", fileName);
        Optional<MediaDTO> mediaDTO = mediaService.findByFileName(fileName);
        return ResponseUtil.wrapOrNotFound(mediaDTO);
    }
    
    /**
    *
    * @param needId the id of the mediaDTO to retrieve
    * @return the ResponseEntity with status 200 (OK) and with body the mediaDTO, or with status 404 (Not Found)
    */
   @GetMapping("/media/getAllUrlByNeedId/{needId}")
   @Timed
   public ResponseEntity<List<MediaDTO>> getAllUrlByNeedId(@PathVariable Long needId,Pageable pageable) {
       log.debug("REST request to get a page of Media{}",needId);
       Page<MediaDTO> page = mediaService.findAllUrlByNeedId(needId,pageable);
       HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/getAllUrlByNeedId");
       return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
   }
}
