package net.liuzd.spring.boot.v2.config;

import java.util.List;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;

import net.liuzd.spring.boot.v2.methods.DeleteAll;


public class MyLogicSqlInjector extends LogicSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList() {
        List<AbstractMethod> methodList = super.getMethodList();
        methodList.add(new DeleteAll());
        return methodList;
    }
}
