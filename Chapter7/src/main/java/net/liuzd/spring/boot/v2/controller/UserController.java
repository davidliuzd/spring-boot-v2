package net.liuzd.spring.boot.v2.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.liuzd.spring.boot.v2.config.PropertiesConfig;
import net.liuzd.spring.boot.v2.service.UserService;
import net.liuzd.spring.boot.v2.util.CommonUtil;

/**
 * @ClassName UserController
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService         userService;

    @Autowired
    private PropertiesConfig    propertiesConfig;

    /***
     * @param request
     * @return
     */
    @RequestMapping(value = "map", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> list(HttpServletRequest request) {
        Map<String, Object> map = CommonUtil.getParameterMap(request);
        return new ResponseEntity<>(userService.getList(map), HttpStatus.OK);
    }

    @RequestMapping(value = "get", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> get(HttpServletRequest request) {
        return new ResponseEntity<>(propertiesConfig.getProfilesActive(), HttpStatus.OK);
    }

}