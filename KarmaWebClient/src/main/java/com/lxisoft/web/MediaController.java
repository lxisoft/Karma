package com.lxisoft.web;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.client.karma.api.MediaResourceApi;
import com.lxisoft.client.karma.model.MediaDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * controller for managing Media.
 */
@Controller
public class MediaController {

    private final Logger log = LoggerFactory.getLogger(MediaController.class);

    private static final String ENTITY_NAME = "karmaMedia";

    @Autowired
    MediaResourceApi mediaResourceApi;
       
    /**
    *
    * @param fileName the id of the mediaDTO to retrieve
    * @return the ResponseEntity with status 200 (OK) and with body the mediaDTO, or with status 404 (Not Found)
    */
   @GetMapping("/media/getMedia/{fileName}")
   @Timed
   public String getMediaByFileName(@PathVariable String fileName,Model model) {
       log.debug("REST request to get Media : {}", fileName);
       
       MediaDTO mediaDTO = mediaResourceApi.getMediaByFileNameUsingGET(fileName).getBody();       
       
       model.addAttribute("media",mediaDTO);
       return "home";
   }
   
   
}
