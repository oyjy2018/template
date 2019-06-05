package com.ydc.service.user.service.impl;

import com.ydc.dao.cgj.common.MemberFileDao;
import com.ydc.model.cgj.MemberFile;
import com.ydc.service.user.service.MemberFileService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MemberFileServiceImpl implements MemberFileService {

    @Resource
    private MemberFileDao memberFileDao;

    @Override
    public Integer batchAddMemberFile(List<MemberFile> list) {
        return memberFileDao.batchAddMemberFile(list);
    }

    @Override
    public List<MemberFile> getMemberFileByMemberIdAndType(Integer memberId, String types) {
        return memberFileDao.getMemberFileByMemberIdAndType(memberId, types);
    }
}
