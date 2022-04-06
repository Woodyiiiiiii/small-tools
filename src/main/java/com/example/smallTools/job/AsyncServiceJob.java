package com.example.smallTools.job;

/**
 * @Author:woody
 * @Date: 2022/4/6 23:21
 */
public abstract class AsyncServiceJob implements AsyncService {

    private Integer pageSize;

    public AsyncServiceJob() {
        pageSize = 10;
    }

    @Override
    public void execute() {



    }

}
