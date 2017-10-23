package com.bo;

import com.vo.Project;
import org.apache.poi.hssf.record.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

public class Page {
 private int pageNum;//当前页，从请求那边传过来
 private int pageSize;//每页显示的数据条数
 private int totalRecord;//总的记录条数，查询数据库得到的数据

 //需要计算的来
 private int totalPage ;//总页数，通过totalRecord 和pagesize 计算得来

 private int startIndex;//开始页
 private ArrayList<Project> list;//将每页显示的数据放在list集合中
 //分页显示的页数，比如在页面上显示1，2，3
 private int start;
 private int end;


 public Page(int pageNum,int pageSize ,int totalRecord){
     this.pageNum =pageNum;
     this.pageSize=pageSize;
     this.totalRecord = totalRecord;

     if(totalRecord%pageSize==0){
         this.totalPage=totalRecord/pageSize;
     }else {
         this.totalPage= totalRecord/pageSize+1;
     }
     this.startIndex=(pageNum-1)*pageSize;
     this.start=1;
     this.end=5;

     if(totalPage<=5){
         this.end=this.totalPage;
     }else {
         this.start=pageNum-2;
         this.end=pageNum+2;
         if(start<0){
             this.start=1;
             this.end=5;
         }
         if(end >this.totalPage){
             this.end =totalPage;
             this.start=end-5;
         }
     }
 }
    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }


    public ArrayList<Project> getList() {
        return list;
    }

    public void setList(ArrayList<Project> list) {
        this.list = list;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

}
