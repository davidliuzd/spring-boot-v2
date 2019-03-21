package net.liuzd.spring.boot.v2.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import net.liuzd.spring.boot.v2.entity.User;
import net.liuzd.spring.boot.v2.entity.enums.UserStatusEnum;
import net.liuzd.spring.boot.v2.mapper.UserMapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAll(User user) {
        if (user.getPage() != null && user.getRows() != null) {
            PageHelper.startPage(user.getPage(), user.getRows());
        }
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (user.getName() != null && user.getName().length() > 0) {
            criteria.andLike("name", "%" + user.getName() + "%");
        }
        return userMapper.selectByExample(example);
    }

    public List<User> getAllByWeekend(User user) {
        if (user.getPage() != null && user.getRows() != null) {
            PageHelper.startPage(user.getPage(), user.getRows());
        }
        Weekend<User> weekend = Weekend.of(User.class);
        WeekendCriteria<User, Object> criteria = weekend.weekendCriteria();
        if (user.getName() != null && user.getName().length() > 0) {
            criteria.andLike("name", "%" + user.getName() + "%");
        }
        return userMapper.selectByExample(weekend);
    }

    public User getById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public int deleteById(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    public int save(User user) {      
        user.setStatus(UserStatusEnum.NORMAL);
        user.setMark(0);
        user.setVersion(0);
        user.setModifyTime(new Date());
        if (user.getId() != null) {
            return userMapper.updateByPrimaryKey(user);
        } else {
            user.setCreateTime(new Date());
            return userMapper.insert(user);
        }
    }

    public int inserts(List<User> users) {
        return userMapper.insertList(users);
    }

}
