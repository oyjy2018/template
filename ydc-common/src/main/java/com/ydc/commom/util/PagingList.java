package com.ydc.commom.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.Lists;


import java.util.List;
import java.util.stream.Collectors;

/**
 * 对数据集合进分页获取
 *
 * @author
 * @create 2018-12-24 16:36
 **/
public class PagingList<T> {

    private static final Logger logger = LogManager.getLogger(PagingList.class);

    /**
     * 总页数
     */
    private int totalPage = 0;

    /**
     * 当前是第几页
     */
    private int pageNum;

    /**
     * 每页的大小
     */
    private int pageSize;


    private List<T> pageData;


    public PagingList(List<T> pageResult, int pageSize,int pageNum) {
        this.pageData = pageResult;
        this.pageSize = pageSize;
        this.pageNum = getPageNum(pageNum);
        init(pageResult, pageSize);
    }


    private void init(List<T> pageResult, int pageSize) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Paging size must be greater than zero.");
        }
        if (null == pageResult) {
            throw new NullPointerException("Paging resource list must be not null.");
        }
        if (pageResult.size() % pageSize > 0) {
            this.totalPage = (pageResult.size() / pageSize) + 1;
        } else {
            this.totalPage = pageResult.size() / pageSize;
        }
    }

    /**
     * 返回当前剩余页数
     *
     * @return
     */
    private int getSurplusPage() {
        if (pageData.size() % pageSize > 0) {
            return (pageData.size() / pageSize) + 1;
        } else {
            return pageData.size() / pageSize;
        }
    }

    /**
     * 返回是否还有下一页数据
     *
     * @return
     */
    public boolean hasNext() {
        return pageData.size() > 0;
    }

    /**
     * 获取分页后，总的页数
     *
     * @return
     */
    public int getTotalPage() {
        return totalPage;
    }

    public List<T> next() {
        logger.info("subject:{},pageNum:{},pageSize:{}","list分页",this.pageNum,this.pageSize);
        pageData = pageData.stream().skip(this.pageNum).collect(Collectors.toList());
        List<T> pagingData = pageData.stream().limit(this.pageSize).collect(Collectors.toList());
        return pagingData;
    }

    /**
     * 返回需要跳过的下标
     * @return
     */
    public int getPageNum(int pageNum){
        this.pageNum = pageNum > 0 ? ((pageNum - 1) * this.pageSize) : pageNum;
//        this.pageNum = this.pageNum > pageData.size() ?  (pageData.size() - (this.pageNum % pageData.size())) : this.pageNum;
        logger.info("subject:{},pageNum:{}","list分页计算需要跳过的下标",this.pageNum);
        return this.pageNum;
    }

    /**
     * 返回当前页数
     *
     * @return
     */
    public int getCurPageNo() {
        return totalPage - getSurplusPage();
    }

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList();
        for(int i=0; i<16; i++){
            list.add(i);
        }
        list = new PagingList<>(list,10,3).next();
       logger.info("size:"+list.size());
        list.forEach(System.out::println);
    }
}
