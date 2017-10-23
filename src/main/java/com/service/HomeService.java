package com.service;

import com.bo.Page;
import com.dao.proDao;
import com.vo.Project;

import java.util.List;

 public class HomeService {
  public Page findAllProWithPage(int pageNum ,int pageSize,String proName,String author){
      List<Project> allPro = proDao.findAllPro(proName,author);
      int totalRecord = allPro.size();

      Page pb = new Page(pageNum,pageSize,totalRecord);

      int startIndex =pb.getStartIndex();

      pb.setList(proDao.findAll(startIndex ,pageSize,proName,author));
      return pb;
  }
}
