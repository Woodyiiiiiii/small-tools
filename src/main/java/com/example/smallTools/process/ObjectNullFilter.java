package com.example.smallTools.process;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 * @Author:woody
 * @Date: 2022/4/27 23:26
 */
public abstract class ObjectNullFilter<R> implements ExecutorWrap<R> {

    protected boolean nonNull(Object obj) {

        if (obj == null) {
            return true;
        }

        if (obj instanceof String) {
            String str = (String) obj;
            if (StringUtils.isBlank(str)) {
                return true;
            }
        }

        if (obj instanceof Collection) {
            Collection<?> collection = (Collection<?>) obj;
            return CollectionUtil.isEmpty(collection);
        }

        return false;

    }

}
