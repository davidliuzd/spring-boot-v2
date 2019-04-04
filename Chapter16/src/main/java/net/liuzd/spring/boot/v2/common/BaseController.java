package net.liuzd.spring.boot.v2.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import net.liuzd.spring.boot.v2.web.Result;

public class BaseController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    protected ModelAndView view(String viewName) {
        return view(viewName, null);
    }

    protected ModelAndView view(String viewName, Result result) {
        return new ModelAndView(viewName, result.getData());
    }

    protected ModelAndView toError() {
        return new ModelAndView("errors/500");
    }

    protected ModelAndView redirect(String viewName) {
        return new ModelAndView("redirect:" + viewName);
    }

}