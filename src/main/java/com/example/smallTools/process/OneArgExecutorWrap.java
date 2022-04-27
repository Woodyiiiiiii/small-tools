package com.example.smallTools.process;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author:woody
 * @Date: 2022/4/27 23:24
 */
public class OneArgExecutorWrap<Arg1, R> extends ObjectNullFilter<R> {

    private final Arg1 arg1;

    private Supplier<R> supplier;

    private Function<Arg1, R> function;

    public OneArgExecutorWrap(Arg1 arg1) {
        this.arg1 = arg1;
    }



    //--------------------实现链式初始化--------------------

    public OneArgExecutorWrap<Arg1, R> nonAccept(Supplier<R> supplier) {
        this.supplier = supplier;
        return this;
    }

    public OneArgExecutorWrap<Arg1, R> notNullAccept(Function<Arg1, R> function) {
        this.function = function;
        return this;
    }

    public OneArgExecutorWrap<Arg1, R> build(Arg1 arg1) {
        return new OneArgExecutorWrap<>(arg1);
    }

    //--------------------执行方法--------------------

    @Override
    public R execute() {

        if (nonNull(arg1)) {
            if (supplier != null) {
                return supplier.get();
            }
        }

        if (function != null) {
            return function.apply(arg1);
        }

        return null;

    }

    //--------------------实现范例--------------------

    public static void main(String[] args) {

        // 在很多情况下需要我们先判断对象是否存在，不存在则插入数据库，存在则更新数据库数据
        // Model model = baseMapper.selectOne(queryWrapper);

        // 使用
        // return OneArgExecutorWrap.build(model)
        //      .nonAccept(() -> baseMapper.insert(model))
        //      .notNullAccept(model -> baseMapper.updateById(model))
        //      .execute();

    }

}
