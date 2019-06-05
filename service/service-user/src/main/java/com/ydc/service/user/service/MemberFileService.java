package com.ydc.service.user.service;

import com.ydc.model.cgj.MemberFile;

import java.util.List;

/**
 * 客户文件service
 */
public interface MemberFileService {

    /**
     * 批量添加客户文件
     * @param list
     * @return
     */
    Integer batchAddMemberFile(List<MemberFile> list);

    /**
     * 通过客户id和类型获取图片信息
     * @param memberId
     * @param types
     * @return
     */
    List<MemberFile> getMemberFileByMemberIdAndType(Integer memberId, String types);
}
