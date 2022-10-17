package com.codex.profiler.profilerservice.controller;

import com.codex.profiler.profilerservice.exception.UserExistsException;
import com.codex.profiler.profilerservice.model.ResponseModel;
import com.codex.profiler.profilerservice.model.UserInfoModel;
import com.codex.profiler.profilerservice.service.UserAccessService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserAccessController {

    @Autowired
    private UserAccessService userAccessService;


    @ApiOperation(value = "To get user access-token", notes = "Returns a access-token which needs to be sent as header parameter in order access other APIs")
    @PostMapping("/getUserAccess")
    public ResponseEntity<ResponseModel> getUserAccess(@Valid @RequestBody UserInfoModel userInfoModel) throws UnsupportedEncodingException, UserExistsException {

        if (userAccessService.isUserExists(userInfoModel)) {
            throw new UserExistsException("User already exists / user email should be unique");
        }
        String accessToken = userAccessService.addUserAndGetToken(userInfoModel);
        return new ResponseEntity<>(new ResponseModel("User has been created successfully", "Access token : " + accessToken), HttpStatus.OK);
    }
}
