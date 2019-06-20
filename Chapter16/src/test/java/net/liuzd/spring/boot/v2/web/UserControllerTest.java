package net.liuzd.spring.boot.v2.web;

import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j2;
import net.liuzd.spring.boot.v2.Application;
import net.liuzd.spring.boot.v2.entity.User;
import net.liuzd.spring.boot.v2.entity.enums.UserStatusEnum;
import net.liuzd.spring.boot.v2.util.JSONUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Log4j2
public class UserControllerTest {

    private MockMvc               mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testList() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/list").accept(
                MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(MockMvcResultHandlers.print()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        log.error("result : " + content);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testFindId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1L).accept(
                MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(MockMvcResultHandlers.print()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        log.error("result : " + content);
        Assert.assertEquals(200, status);
    }

    private User get(int i) {
        User bean = new User();
        bean.setName("天涯 " + i);
        bean.setAge(18 + i);
        bean.setEmail(i + "davidliuzd@sina.com");
        bean.setMark(1);
        bean.setVersion(1);
        bean.setStatus(UserStatusEnum.NORMAL);
        return bean;
    }

    @Test
    public void testCreate() throws Exception {
        User po = get(new Random().nextInt());
        String json = JSONUtils.toJson(po);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user").contentType(
                MediaType.APPLICATION_JSON_UTF8_VALUE).content(json).accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        log.error("result : " + content);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testUpdate() throws Exception {
        User bean = get(new Random().nextInt());
        bean.setId(1L);
        String json = JSONUtils.toJson(bean);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/id").contentType(
                MediaType.APPLICATION_JSON_UTF8_VALUE).content(json).accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        log.error("result : " + content);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testDel() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/user/{id}", 1L).accept(
                MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(MockMvcResultHandlers.print()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        log.error("result : " + content);
        Assert.assertEquals(200, status);
    }

}
