package net.liuzd.spring.boot.v2.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({ ArithmeticException.class })
    @ResponseBody
    public JsonResult<Exception> handleIndexOutOfBoundsException(HttpServletRequest req, Exception e) {
        String code = "500";        
        return new JsonResult<Exception>(code,e.getMessage(),e);
    }

    public static final String DEFAULT_ERROR_VIEW = "403";

    @ExceptionHandler(value = org.apache.shiro.authz.AuthorizationException.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

}
