package com.springboot.controller;

import com.springboot.model.Miscellaneous;
import com.springboot.service.MiscellaneousService;
import com.springboot.util.BaseResponse;
import com.springboot.util.Const;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = "Miscellaneous")
@Validated
public class MiscellaneousController {

    @Autowired
    private MiscellaneousService miscellaneousService;

    @GetMapping(value = Const.API_MISCELLANEOUS)
    public ResponseEntity<BaseResponse<Collection<Miscellaneous>>> getAllMiscellaneous() {
        BaseResponse<Collection<Miscellaneous>> response = new BaseResponse<>();
        try {
            List<Miscellaneous> airline = miscellaneousService.getMiscellaneous();
            response.setMessage(Const.MESSAGE_SUCCESS);
            response.setBody(airline);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
