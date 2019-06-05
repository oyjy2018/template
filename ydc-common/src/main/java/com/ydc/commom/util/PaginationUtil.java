package com.ydc.commom.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ydc.model.cgj.Pagination;

import java.util.List;
import java.util.function.Function;

public class PaginationUtil {

    /**
     * 分页处理
     *
     * @param t
     * @param function
     * @param <T>
     * @param <R>
     * @return
     * @throws Exception
     */
    public static <T extends Pagination, R> R paginationQuery(T t, Function<T, R> function) {
        return paginationQuery(t, t, function);
    }

    /**
     * 分页处理
     *
     * @param pagination
     * @param t
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> R paginationQuery(Pagination pagination, T t, Function<T, R> function) {
        try {
            if (pagination != null) {
                PageHelper.startPage(pagination.getPage(), pagination.getRows());
            }
            return function.apply(t);
        } finally {
            PageHelper.clearPage();
        }
    }

    /**
     * 返回List总记录条数
     * @param list
     * @return
     */
    public static Long pageTotalQuery(List<?> list){
        return list == null ? 0 : new PageInfo<>(list).getTotal();
    }
}
