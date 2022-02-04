package com.mishelon.base.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mishelon.base.entities.UserEntity;
import com.mishelon.base.services.UserService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@OpenAPIDefinition (info =
@Info(
          title = "Users Controller",
          version = "0.0",
          description = "Endpoint for users operations",
          contact = @Contact(name = "mishelon@gmail.com", email = "mishelon@gmail.com")
  )
)
public class UserController {
	private static Logger LOG = LogManager.getLogger(UserController.class);
	
	@Autowired
	private UserService userSvc;
	

	
	/**
	 * @param id User ID
	 * @return User if exists, 404 otherwise.
	 */
	@GetMapping("/findById/{id}")
	@Operation(summary = "find User by ID", parameters = @Parameter(name = "id", description =  "User ID") ,  responses = {
			@ApiResponse(responseCode = "404", description = "User not found",
					content =  @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "200", description = "The user",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class)))
            }
	)
	public ResponseEntity<UserEntity> findByID(@PathVariable Long id) {
		ResponseEntity<UserEntity> res;
		try {
		UserEntity user = userSvc.findById(id);
			if(user!=null) {
				res = new ResponseEntity<UserEntity>(user,HttpStatus.OK);
			} else {
				LOG.info("No se encontró usuario id {}",id);
				res = new ResponseEntity<UserEntity>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			res = new ResponseEntity<UserEntity>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return res;
	}
	 
	@GetMapping("/failer/{id}")
	public ResponseEntity<UserEntity> failer(@PathVariable Long id) { 
		
		throw new NullPointerException();
	}

}
