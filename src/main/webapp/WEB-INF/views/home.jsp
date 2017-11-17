<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>万达企业服务</title>
    <meta name="viewport" content="width=device-width,initial-scake=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><!-- //For-Mobile-Apps-and-Meta-Tags -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/fileinput.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/public.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/fileinput.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap-treeview.js"></script>
    <script src="<%=request.getContextPath()%>/js/zh.js"></script>
</head>

<body style="background-image:url(<%=request.getContextPath()%>/image/home.jpg)">
<div id="container">
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container" >
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">万达演示</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
<div class="container" style="margin-top: 20px" >
    <div class="row clearfix">
        <div class="col-md-2 column"></div>
        <div class="col-md-8 column" style="margin-top: 100px">
            <div class="form-inline" role="form">
                <div class="form-group">
                    <span> 项目名称：</span>
                 <input type="text" id="proid" name="proname" class="form-control" value="${proname}" >&nbsp;&nbsp;&nbsp;
                    <span>提交人：</span>
                 <input type="text" id="publisherid" name="publishername" class="form-control" value="${publisherName}" >
                </div>

                <div class="form-group">
                    &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="btn btn-default" id="queryPro" value="查询">
                    &nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-primary btn-default" data-toggle="modal" data-target="#myModal" id="modal">新建</button>

                </div>
            </div>



            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false" >
                <div class="modal-dialog">

                    <div class="modal-content">
                        <div class="modal-header">

                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                            <h4 class="modal-title" id="myModalLabel">
                                新建项目
                            </h4>
                        </div>
                        <form action="<%=request.getContextPath()%>/lookPro/upload" name="pro" method="post" enctype="multipart/form-data">

                        <div class="modal-body">
                                <div class="input-group">
                                    <span class="input-group-addon">项目名称</span>
                                    <input type="text" class="form-control" name="proName" placeholder="名称">
                                </div>
                                <div class="input-group" style="margin-top: 10px">
                                    <span class="input-group-addon">上传人&nbsp;&nbsp;&nbsp;</span>
                                    <input type="text" class="form-control" name="author" placeholder="上传人">
                                </div>
                                <div  style="margin-top: 10px">
                                    <input id="file" type="file"name="pro"></div>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                </button>
                                <button type="submit" class="btn btn-primary">
                                    提交
                                </button>
                            </div>
                        </form>


                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>
        <div style="height: 320px">
        <table class="table table-striped" style="margin-top: 20px;">
            <caption>项目列表</caption>
            <thead>
            <tr>
                <th>序号</th>
                <th>项目名称</th>
                <th>项目版本</th>
                <th>上传人</th>
                <th>上传时间</th>
                <th colspan="3">操作</th>

            </tr>

            </thead>
            <c:forEach varStatus="status" var="pro" items="${page.list}">
                <tr>
                    <td>${status.index+1}</td>
                    <td>${pro.proName}</td>
                    <td  >
                        <a onclick="overShow('${pro.id}')">${pro.proVersion}</a>
                    </td>
                    <td>${pro.author}</td>
                    <td>${pro.createTime}</td>
                    <td style="text-align: center">
                        <%--<a href="#" class="btn btn-link"  onclick="openIndex('${pro.fileName}')">查看</a>--%>
                        <a href="#" class="btn btn-link"  onclick="openProDic('${pro.fileName}')">查看</a>
                        <button data-toggle="modal" data-target="#myModal1" value="${pro.id}" name="update" class="btn btn-link">更新 </button>
                        <a href="/lookPro/deletePro?proName=${pro.proName}&&fileName=${pro.fileName}" class="btn btn-link">删除</a>
                        </td>

                </tr>
            </c:forEach>
        </table>
            <div id="showDiv" style="position: absolute; background-color: white; border: 1px solid ;border-radius: 5px ; border-color: #999999; width: 45px;min-height: 0px;display: none" ></div>
            <div id="proDic" style="width:600px;height: 400px;background-color: white;position: absolute;top: 70px;left: 80px;display: none" ></div>
        </div>
            <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false" >
                <div class="modal-dialog">

                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                            <h4 class="modal-title" id="myModalLabel1">
                                新建项目
                            </h4>
                        </div>
                        <form action="<%=request.getContextPath()%>/lookPro/update" name="pro" method="post" enctype="multipart/form-data">

                            <div class="modal-body">
                                <div class="input-group">
                                    <span class="input-group-addon">项目名称</span>
                                    <input type="text" class="form-control" name="proName" id="upload"   placeholder="">
                                </div>
                                <div class="input-group" style="margin-top: 10px">
                                    <span class="input-group-addon">上传人&nbsp;&nbsp;&nbsp;</span>
                                    <input type="text" class="form-control" name="author" placeholder="">
                                </div>
                                <input type="hidden" name="id" id="id" >
                                <div  style="margin-top: 10px">
                                    <input id="file1" type="file"name="pro"></div>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                </button>
                                <button type="submit" class="btn btn-primary">
                                    提交
                                </button>
                            </div>
                        </form>


                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>
            <ul class="pagination" style="float: right">
              <li> <a href="#" onclick="findPro(1)">首页</a></li>
                <c:choose>
                    <c:when test="${page.totalPage eq 1}">
                        <li>  <a>1</a> </li>
                    </c:when>
                    <c:otherwise>
                        <%--如果当前页为第一页时，就没有上一页这个超链接显示 --%>
                        <c:if test="${page.pageNum eq 1}">
                            <c:forEach begin="${page.start}" end="${page.end}" step="1" var="i">
                                <c:if test="${page.pageNum==i}">
                                    <li class="active">  <a>${i}</a> </li>
                                </c:if>
                                <c:if test="${page.pageNum!=i}">
                                    <li>   <a href="#" onclick="findPro(${i})">${i}</a> </li>
                                </c:if>
                            </c:forEach>
                            <li>  <a href="#" onclick="findPro(${page.pageNum+1})">&raquo;</a></li>
                        </c:if>
                        <%--如果当前页不是第一页也不是最后一页，则有上一页和下一页这个超链接显示 --%>
                        <c:if test="${page.pageNum >1 && page.pageNum <page.totalPage}">
                            <a href="#" onclick="findPro(${page.pageNum-1})">&laquo;</a>
                            <c:forEach begin="${page.start}" end="${page.end}" step="1" var="i">
                                <c:if test="${page.pageNum==i}">
                                    <li class="active">  <a>${i}</a> </li>
                                </c:if>
                                <c:if test="${page.pageNum!=i}">
                                    <a href="#" onclick="findPro(${i})">${i}</a>
                                </c:if>
                            </c:forEach>
                            <a href="#" onclick="findPro(${page.pageNum+1})">&raquo;</a>
                        </c:if>
                        <%-- 如果当前页是最后一页，则只有上一页这个超链接显示，下一页没有 --%>
                        <c:if test="${page.pageNum eq page.totalPage}">
                            <li>  <a href="#" onclick="findPro(${page.pageNum-1})">&laquo;</a></li>
                            <c:forEach begin="${page.start}" end="${page.end}" step="1" var="i">
                                <c:if test="${page.pageNum==i}">
                                    <li class="active">  <a>${i}</a> </li>
                                </c:if>
                                <c:if test="${page.pageNum!=i}">
                                    <li>  <a href="#" onclick="findPro(${i})">${i}</a></li>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </c:otherwise>
                </c:choose>

              <li>  <a href="#" onclick="findPro(${page.totalPage})">尾页</a></li>
            </ul>
        <div style="background-color: white;margin-left: 40px;  height: 200px;width:600px;margin-top: -400px;filter: alpha(opacity=60); opacity:0.99; -moz-opacity:0.99;display: none"> </div>
        </div>
        <div class="col-md-2 column" id="aa"></div>
    </div>
</div>
</div>
</body>
<script>
function openIndex(proName) {
    var url="<%=request.getContextPath()%>/pro/"+proName+"/index.html";
    window.open(encodeURI(url));
}
function openProDic(fileName) {
    $.ajax({
        url:"<%=request.getContextPath()%>/lookPro/openProDic",
        data:{path:fileName},
        success:function (data) {
          var tree ="["+ data+"]";
            $("#proDic").treeview({data:tree});
            $("#proDic").show();
        },
        error:function () {
            alert("sad!");
        }
    })
//    var tree=[{
//        text: "Node 1",
//        icon: "glyphicon glyphicon-stop",
//        selectedIcon: "glyphicon glyphicon-stop",
//        color: "#000000",
//        backColor: "#FFFFFF",
//        href: "#node-1",
//        selectable: true,
//        state: {
//            checked: true,
//            disabled: false,
//            expanded: true,
//            selected: true
//        },
//        tags: ['available'],
//        nodes: [
//            {},
//
//    ]
//}];
//    $("#proDic").treeview({data:tree});
//    $("#proDic").show();
}
$(function () {
    var oFileInput = new FileInput();
    var uFileInput = new FileInput();
    uFileInput.Init("file1","/lookpro/upload");
    oFileInput.Init("file","/lookpro/upload");
});
function findPro(pageNum) {
    window.open("<%=request.getContextPath()%>/lookPro/findPro?proName="+$("#proid").val()+"&&author="+$("#publisherid").val()+"&&pageNum="+pageNum,parent);
}
$(":button[name=update]").click(function () {
    $("#id").val($(this).val());
});
var FileInput = function () {
    var oFile = new Object();

    //初始化fileinput控件（第一次初始化）
    oFile.Init = function(ctrlName, uploadUrl) {
        var control = $('#' + ctrlName);

        //初始化上传控件的样式
        control.fileinput({
            language: 'zh',
            uploadUrl:uploadUrl, //上传后台操作的方法
            uploadAsync: true, //设置上传同步异步 此为同步
            maxFileSize: 200000,
            showCaption:true,
            showPreview:false,
            showRemove:true,
            showUpload:false,
//            initialPreview:'../file_52.mp4',
            dropZoneEnabled:false,
//            previewFileIcon: '<i class="fa fa-file"></i>',
            allowedPreviewTypes: ['image'],
            allowedFileExtensions: ['mp4','zip','rar'], //限制上传文件后缀
        });

        //导入文件上传完成之后的事件
        $("#file").on("fileuploaded", function (event, data, previewId, index) {
            $("#myModal").modal("hide");
            var data = data.response.lstOrderImport;
            if (data == undefined) {
                toastr.error('文件格式类型不正确');
                return;
            }
            //1.初始化表格
            var oTable = new TableInit();
            oTable.Init(data);
            $("#div_startimport").show();
        });
    }
    return oFile;
};
$("#queryPro").click(function () {
   window.open("<%=request.getContextPath()%>/lookPro/findPro?proName="+$("#proid").val()+"&&author="+$("#publisherid").val()+"&&pageNum=1",'parent');

})
$(document).click(function () {
    $("#showDiv").hide();
})
function overShow(id) {
    var showDiv = document.getElementById('showDiv');
    showDiv.style.left = event.clientX/2.6+'px';
    showDiv.style.top = event.clientY/2.0+'px';


    var url = "<%=request.getContextPath()%>/lookPro/showVersion";
    var temp="";
    $.ajax({
        url:url,
        data:{"id":id},
        success:function (data) {
            temp+="<table class='table table-striped' >";
            for(var i=0; i<data.length;i++){
              temp += "<tr><td><a onclick=openIndex('"+data[i].fileName +"')>"+data[i].proVersion+"</a></td></tr>";
            }
            temp+="</table>"
            showDiv.innerHTML=temp;
            showDiv.style.display = 'block';
        },
        error:function () {
            alert("sad!");
        }
    });

}

</script>
</html>