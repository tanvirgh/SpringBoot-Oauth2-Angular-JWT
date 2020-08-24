package com.cmed.health.app.controller;

import com.cmed.health.app.api.ApiConstants;
import com.cmed.health.app.api.ApiProvider;
import com.cmed.health.app.util.ResponseMaker;
import com.cmed.health.core.dto.UserDto;
import com.cmed.health.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */

@RestController
@RequestMapping(path = ApiProvider.UserApi.ROOT_PATH)
public class UserController {

    private UserService userService;
    private ResponseMaker responseMaker;

    @Autowired
    public UserController(UserService userService, ResponseMaker responseMaker) {
        this.userService = userService;
        this.responseMaker = responseMaker;
    }

//    @PostMapping
//    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
//        Optional<UserDto> userDtoOptional = userService.persist(userDto);
//        return responseMaker.responseForPost(userDtoOptional);
//    }

    @GetMapping(value = ApiProvider.UserApi.USERID, produces = "application/json")
    public ResponseEntity<UserDto> get(@PathVariable(name = ApiConstants.USER_ID) long id) {

        Optional<UserDto> userDtoOptional = userService.findById(id, UserDto.class);
        return responseMaker.responseForGet(userDtoOptional);
    }

//    @GetMapping
//    public Collection<UserDto> getAll(@RequestParam(value=ApiConstants.RequestParams.ORG_ID, required=false) Long orgId,
//                                      @RequestParam(value=ApiConstants.RequestParams.ROLE, required=false) String roleToLoad,
//                                      @RequestParam(value=ApiConstants.RequestParams.LOGGED_IN_USER_ID, required=false) Long teamLeadId) {
//        if(orgId!=null && roleToLoad.equals("PARTICIPANTS")){
//            return userService.findAllUserByOrganizationIdAndRoleNameAndTeamLead(orgId, roleToLoad, teamLeadId);
//        }
//        if(orgId!=null && roleToLoad.equals("LEAD")){
//            return userService.findAllUserByOrganizationIdAndRoleName(orgId, roleToLoad);
//        }
//        if(orgId!=null) return userService.findAllUsersByOrganizationId(orgId);
//        return userService.getAll(UserDto.class);
//    }




}
