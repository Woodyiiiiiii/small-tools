package com.example.smallTools.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.smallTools.dao.StudentMapper;
import com.example.smallTools.model.StudentModel;
import com.example.smallTools.redis.RedisCache;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: woodyiiiiiii
 * @Date: 2021/12/31 22:50
 */
@Service
public class StudentService extends ServiceImpl<StudentMapper, StudentModel> {

    /**
     * 查询所有
     */
    @RedisCache
    public List<StudentModel> selectAll() {
        return baseMapper.selectList(null);
    }

}
