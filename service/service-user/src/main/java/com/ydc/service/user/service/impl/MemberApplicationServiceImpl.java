package com.ydc.service.user.service.impl;

import com.ydc.beans.file.FileUtil;
import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.view.dto.cgj.MemberDTO;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.dao.cgj.common.MemberFileDao;
import com.ydc.dao.cgj.user.MemberApplicationDao;
import com.ydc.dao.cgj.user.MemberDao;
import com.ydc.model.cgj.Member;
import com.ydc.model.cgj.MemberApplication;
import com.ydc.model.cgj.MemberFile;
import com.ydc.service.user.service.MemberApplicationService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberApplicationServiceImpl implements MemberApplicationService {
    private final Logger logger = LogManager.getLogger(MemberApplicationServiceImpl.class);

    @Resource
    private MemberApplicationDao memberApplicationDao;

    @Resource
    private MemberFileDao memberFileDao;

    @Resource
    private MemberDao memberDao;

    @Override
    public MemberApplication getMemberApplication(String mobilePhone, String loginName, Integer application) {
        return memberApplicationDao.getMemberApplication(mobilePhone, loginName, application);
    }

    @Override
    public List<Map<String, Object>> getMemberApplicationList(MemberDTO memberDTO) {
        return PaginationUtil.paginationQuery(memberDTO ,(tempMemberDTO) -> memberApplicationDao.getMemberApplicationList(tempMemberDTO));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, value = "cgjTransactionManager")
    public int updateMemberApplication(MemberApplicationVO memberApplicationVO) {
        int result = memberDao.updateByPrimaryKeySelective(memberApplicationVO);
        List<MemberFile> memberFiles = memberApplicationVO.getFileList();
        List<MemberFile> oldMemberFiles = memberFileDao.getMemberFileByMember(memberApplicationVO.getId());
        if (result > 0){
            updateMemberFile(memberFiles, oldMemberFiles);
        }
        return result;
    }

    private void updateMemberFile(List<MemberFile> newFileList, List<MemberFile> oldFileList){
        //如果新文件list为空，则删除旧文件
        if (CollectionUtils.isEmpty(newFileList)){
            delFileList(oldFileList);
            return;
        }
        //如果旧文件list为空，则添加新文件
        if (CollectionUtils.isEmpty(oldFileList)){
            insertFileList(newFileList);
            return;
        }
        //如果都不为空，则找出新增，删除的文件分别操作
        List<MemberFile> insertFileList = new ArrayList<>();
        Map<Integer, MemberFile> oldFileMap = oldFileList.parallelStream()
                .collect(Collectors.toMap(MemberFile::getId, memberFile -> memberFile, (memberFile1, memberFile2) -> memberFile2));
        for (MemberFile memberFile : newFileList){
            if (memberFile.getId() == null){
                insertFileList.add(memberFile);
                continue;
            }
            MemberFile notDelMemberFile = oldFileMap.get(memberFile.getId());
            if (notDelMemberFile != null){
                oldFileList.remove(notDelMemberFile);
            }
        }
        insertFileList(insertFileList);
        delFileList(oldFileList);
    }

    private void delFileList(List<MemberFile> memberFiles){
        if (CollectionUtils.isEmpty(memberFiles)){
            return;
        }
        memberFiles.forEach(memberFile -> {
            memberFile.setStatus(CodeConstant.DISABLE_STATUS);
            memberFileDao.updateByPrimaryKeySelective(memberFile);
        });
    }

    private Integer insertFileList(List<MemberFile> memberFiles){
        if (CollectionUtils.isEmpty(memberFiles)){
            return 0;
        }
        memberFiles.parallelStream().forEach(memberFile ->{
            memberFile.setCreateTime(new Date());
            memberFile.setUpdateTime(new Date());
        });
        return memberFileDao.batchAddMemberFile(memberFiles);
    }

    @Override
    public MemberApplicationVO getMemberApplicationById(int memberId) {
        Member member = memberDao.selectByPrimaryKey(memberId);
        List<MemberFile> fileList = memberFileDao.getMemberFileByMember(memberId);
        for(MemberFile memberFile : fileList){
            try {
                memberFile.setViewFileUrl(FileUtil.getBrowseFile(memberFile.getFileUrl(),memberFile.getFileName()));
            } catch (Exception e) {
               logger.error("获取客户信息生成图片地址异常, memberId: {}", memberId, e);
            }
        }
        MemberApplicationVO memberApplicationVO = new MemberApplicationVO();
        memberApplicationVO.setId(member.getId());
        memberApplicationVO.setMobilePhone(member.getMobilePhone());
        memberApplicationVO.setIdCard(member.getIdCard());
        memberApplicationVO.setRealName(member.getRealName());
        memberApplicationVO.setFileList(fileList);
        return memberApplicationVO;
    }
}
