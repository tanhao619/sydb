package com.cdyoue.oddJobs.api;


import com.cdyoue.oddJobs.dto.User;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T01:12:00.448Z")

@Api(value = "users", description = "the users API")
public interface UsersApi {

    @ApiOperation(value = "Delete user", notes = "This can only be done by the logged in user.", response = Void.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid username supplied", response = Void.class),
        @ApiResponse(code = 404, message = "User not found", response = Void.class) })
    @RequestMapping(value = "/users/{username}",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json" },
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteUser(@ApiParam(value = "The name that needs to be deleted", required = true) @PathVariable("username") String username);


    @ApiOperation(value = "Get user by user name", notes = "", response = User.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = User.class),
        @ApiResponse(code = 400, message = "Invalid username supplied", response = User.class),
        @ApiResponse(code = 404, message = "User not found", response = User.class) })
    @RequestMapping(value = "/users/{username}",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<User> getUserByName(@ApiParam(value = "The name that needs to be fetched. Use user1 for testing.", required = true) @PathVariable("username") String username);


    @ApiOperation(value = "Logs user into the system", notes = "", response = String.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = String.class),
        @ApiResponse(code = 400, message = "Invalid username/password supplied", response = String.class) })
    @RequestMapping(value = "/users/login",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<String> loginUser(@ApiParam(value = "The user name for login") @RequestParam(value = "username", required = false) String username,
                                     @ApiParam(value = "The password for login in clear text") @RequestParam(value = "password", required = false) String password);


    @ApiOperation(value = "Logs out current logged in user session", notes = "", response = Void.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Void.class) })
    @RequestMapping(value = "/users/logout",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Void> logoutUser();


    @ApiOperation(value = "Updated user", notes = "This can only be done by the logged in user.", response = Void.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid user supplied", response = Void.class),
        @ApiResponse(code = 404, message = "User not found", response = Void.class) })
    @RequestMapping(value = "/users/{username}",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateUser(@ApiParam(value = "name that need to be deleted", required = true) @PathVariable("username") String username,
                                    @ApiParam(value = "Updated user object") @RequestBody User body);

}
