package com.mishelon.base.controllers;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mishelon.base.dto.UserDTO;
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

/**
 * Controller for User entity operations
 *
 * @author mishelon
 * @since 4 feb 2022
 */
@RestController
@OpenAPIDefinition(info = @Info(title = "Users Controller", version = "0.0",
    description = "Endpoint for users operations",
    contact = @Contact(name = "mishelon@gmail.com", email = "mishelon@gmail.com")))
public class UserController {
  private static final Logger LOG = LogManager.getLogger(UserController.class);

  @Autowired
  private MessageSource msg;

  @Autowired
  private UserService userSvc;

  @GetMapping("/failer/{id}")
  public ResponseEntity<UserEntity> failer(@PathVariable Long id) {

    throw new NullPointerException();
  }

  /**
   * @param id User ID
   * @return User if exists, 404 otherwise.
   */
  @GetMapping("/findById/{id}")
  @Operation(summary = "find User by ID",
      parameters = @Parameter(name = "id", description = "User ID"),
      responses = {
          @ApiResponse(responseCode = "404", description = "User not found",
              content = @Content(schema = @Schema(hidden = true))),
          @ApiResponse(responseCode = "200", description = "The user",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = UserDTO.class)))})
  public ResponseEntity<UserDTO> findByID(@PathVariable String id) {
    ResponseEntity<UserDTO> res;

    try {
      msg.getMessage("saludo", null, LocaleContextHolder.getLocale());

      UserDTO userDTO = userSvc.findById(id);
      if (userDTO != null) {
        res = new ResponseEntity<>(userDTO, HttpStatus.OK);
      } else {
        LOG.info("No se encontró usuario id {}", id);
        res = new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      res = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return res;
  }

  @SuppressWarnings("rawtypes")
  @PutMapping("/user/update")
  public ResponseEntity update(@RequestBody UserDTO user) {
    ResponseEntity res;
    try {
      UserDTO userDTO = userSvc.update(user);
      if (userDTO != null) {
        res = ResponseEntity.status(HttpStatus.OK).body(userDTO);
      } else {
        LOG.info("No se encontró usuario para actualizar id {}", user.getId());
        res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("nada");
      }
    } catch (Exception e) {
      String cause = ExceptionUtils.getRootCauseMessage(e);
      res = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cause);
    }
    return res;
  }

}
