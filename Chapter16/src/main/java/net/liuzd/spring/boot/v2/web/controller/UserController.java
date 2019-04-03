package net.liuzd.spring.boot.v2.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.liuzd.spring.boot.v2.common.BaseController;
import net.liuzd.spring.boot.v2.entity.User;
import net.liuzd.spring.boot.v2.entity.query.UserQuery;
import net.liuzd.spring.boot.v2.service.UserService;
import net.liuzd.spring.boot.v2.util.ResultUtils;
import net.liuzd.spring.boot.v2.web.Result;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(UserQuery query, @RequestParam(value = "page", defaultValue = "1") final Integer page,
            @RequestParam(value = "size", defaultValue = "10") final Integer size) {
        log.warn("分页：{}", query);
        return ResultUtils.get("page", () -> userService.page(query, page, size));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result find(HttpServletRequest request, @PathVariable Long id) {
        log.warn("查询：{}", id);
        return ResultUtils.get("bean", () -> userService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Result add(HttpServletRequest request, @Valid @RequestBody User bean, BindingResult bindingResult) {
        Result result = ResultUtils.getResult(bindingResult);
        if (!result.isSuccess()) {
            return result;
        }
        log.warn("新增：{}", bean);
        return ResultUtils.get("id", () -> userService.add(bean));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result update(HttpServletRequest request, @PathVariable Long id, @Valid @RequestBody User bean,
            BindingResult bindingResult) {
        Result result = ResultUtils.getResult(bindingResult);
        if (!result.isSuccess()) {
            return result;
        }
        log.warn("更新：{}", bean);
        bean.setId(id);
        return ResultUtils.get(() -> userService.update(bean) > 0);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result del(HttpServletRequest request, @PathVariable Long id) {
        log.warn("删除ID：{}", id);
        return ResultUtils.get(() -> userService.deleteById(id) > 0);
    }

}