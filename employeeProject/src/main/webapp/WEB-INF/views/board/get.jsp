<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style>
.uploadResult {
	width: 100%;
	background-color: gray;
}

.uploadResult ul {
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}

.uploadResult ul li {
	list-style: none;
	padding: 10px;
}

.uploadResult ul li img {
	width: 100px;
}
</style>

<style>
.bigPictureWrapper {
	position: absolute;
	display: none;
	justify-content: center;
	align-items: center;
	top: 0%;
	width: 100%;
	height: 100%;
	background-color: gray;
	z-index: 100;
}

.bigPicture {
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
}

.bigPicture img {
	width: 500px;
}
</style>

<%@include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-primary">
			<div class="panel-heading">Board Detail Page</div>
			<div class="panel-body">

				<div class="form-group" hidden>
					<label>Bno</label> <input class="form-control" name="bno"
						value="<c:out value='${board.b_no}'/>" readonly="readonly">
				</div>

				<div class="form-group">
					<label>Title</label> <input class="form-control" name="title"
						value="<c:out value='${board.title}'/>" readonly="readonly">
				</div>
				<div class="form-group">
					<label>Text area</label>
					<textarea class="form-control" rows="3" name="content"
						readonly="readonly"><c:out value='${board.content}' />
						</textarea>
				</div>
				<div class="form-group">
					<label>Writer</label> <input class="form-control" name="name"
						value="<c:out value='${board.name}'/>" readonly="readonly">
				</div>
				<div class="form-group">
					<label>Reg Date</label> <input class="form-control" name="regdate"
						value="<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${board.reg_date }" />"
						readonly="readonly">
				</div>
				<div class="form-group">
					<label>Upt Date</label> <input class="form-control"
						name="updatedate"
						value="<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${board.update_date }" />"
						readonly="readonly">
				</div>

				<div class="panel panel-default">

					<div class="panel-heading">Files</div>
					<!-- /.panel-heading -->
					<div class="panel-body">


						<div class='uploadResult'>
							<ul>

							</ul>
						</div>
					</div>
					<!--  end panel-body -->

				</div>


				<form id="operForm" action="/board/modify" method="get">
					<input type="hidden" id="bno" name="bno"
						value='<c:out value="${board.b_no}" />' /> <input type="hidden"
						name="pageNum" value="<c:out value="${cri.pageNum}" />" /> <input
						type="hidden" name="amount"
						value="<c:out value="${cri.amount}" />" /> <input type="hidden"
						name="type" value="<c:out value="${cri.type}" />" /> <input
						type="hidden" name="keyword"
						value="<c:out value="${cri.keyword}" />" />
				</form>


				<div>
					<div class="chat-panel panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-comments fa-fw"></i> Reply
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body" style="height: auto;">
							<ul class="chat">

							</ul>
						</div>
						<!-- /.panel-body -->
						<div class="panel-footer">
							<div class="input-group">
								<input id="btn-input" type="text" class="form-control input-sm"
									placeholder="댓글.."> <span class="input-group-btn">
									<button class="btn btn-warning btn-sm" id="btn-chat">
										추가</button>
								</span>
							</div>

							<div class="input-group pull-right"></div>

						</div>
						<!-- /.panel-footer -->
					</div>

				</div>
				<sec:authentication property="principal" var="modi"/>
				<sec:authorize access="isAuthenticated()">
				<c:if test="${modi.username eq board.emp_no }">
				<button data-oper="modify" class="btn btn-success">Modify</button>
				</c:if>
				</sec:authorize>
				<button data-oper="list" class="btn btn-primary">List</button>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>


<div class='bigPictureWrapper'>
	<div class='bigPicture'></div>
</div>


<!-- /.row -->
<!-- 
<div class='row'>
   <div class="col-lg-12">

      <div class="panel panel-default">
         <div class="panel-heading">
            <i class="fa fa-comments fa-fw"></i> Reply
            <button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New Reply</button>
         </div>

         <div class="panel-body">

            <ul class="chat">
               
            </ul>

         </div>
       
         <div class="panel-footer">
         	
         </div>
      </div>
   </div>

</div>
-->


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>Reply</label> <input class="form-control" name='reply'
						value='New Reply!!!!'>
				</div>
				<div class="form-group">
					<label>Replyer</label> <input class="form-control" name='replyer'
						value='replyer'>
				</div>
				<div class="form-group">
					<label>Reply Date</label> <input class="form-control"
						name='replydate' value='2018-01-01 13:13'>
				</div>

			</div>
			<div class="modal-footer">
				<button id='modalModBtn' type="button" class="btn btn-warning">Modify</button>
				<button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
				<button id='modalRegisterBtn' type="button" class="btn btn-primary">Register</button>
				<button id='modalCloseBtn' type="button" class="btn btn-default">Close</button>
			</div>
		</div>
		<!— /.modal-content —>
	</div>
	<!— /.modal-dialog —>
</div>
<!— /.modal —>
<script type="text/javascript" src="/resources/js/reply.js?v=12"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						console.log("===================");
						var bnoValue = '<c:out value='${board.b_no}'/>';

						var replyUL = $(".chat");

						var pageNum = 1;
						// panel-footer
						var replyPageFooter = $(".panel-footer .pull-right");

						showList(1);

						function showList(page) {
							replyService
									.getList(
											{
												bno : bnoValue,
												page : page
											},
											function(replyCnt, list) {

												console.log("replyCnt : "
														+ replyCnt);
												console.log("list : " + list);

												if (page == -1) {
													pageNum = Math
															.ceil(replyCnt / 10.0);
													showList(pageNum);
													return;
												}

												var str = "";
												if (list == null
														|| list.length == 0) {
													replyUL.html("");
													return;
												}
												for (var i = 0, len = list.length || 0; i < len; i++) {
													str += "<li class='left clearfix' data-rno='"+list[i].r_no+"'>";
													str += "<div class='chat-body clearfix' style='margin-left:0px !important;'><div class='header'>";
													str += "<strong class='primary-font'>"
															+ list[i].name
															+ "</strong>";
													str += "<small class='pull-right text-muted'><i class='fa fa-clock-o fa-fw'></i>"
															+ replyService
																	.displayTime(list[i].reply_date)
															+ "</small></div>";
													str += "<p>"
															+ list[i].reply
															+ "</p></div></li>";
												}
												replyUL.html(str);
												showReplyPage(replyCnt);
											});

						} // func showList <end>

						function showReplyPage(replyCnt) {

							var endNum = Math.ceil(pageNum / 10.0) * 10;
							var startNum = endNum - 9;

							var prev = startNum != 1;
							var next = false;

							if (endNum * 10 >= replyCnt) {
								endNum = Math.ceil(replyCnt / 10.0);
							}

							if (endNum * 10 < replyCnt) {
								next = true;
							}
							// pageNum : 820 
							// endNum : 819
							if (endNum < pageNum) {
								pageNum = endNum;
							}

							var str = "<ul class='pagination pull-right'>";
							if (prev) {
								str += "<li class='page-item'><a class='page-link' href='"
										+ (startNum - 1)
										+ "'>Previous</a></li> ";
							}

							for (var i = startNum; i <= endNum; i++) {
								var active = (pageNum == i) ? "active" : "";
								str += "<li class='page-item " + active + "'><a class='page-link' href='"+i+"'>"
										+ i + "</a></li>";
							}

							if (next) {
								str += "<li class='page-item'><a class='page-link' href='"
										+ (endNum + 1) + "'>Next</a></li> ";
							}

							str += "</ul>";

							console.log(str);

							replyPageFooter.html(str);

						} // show reply page

						replyPageFooter.on("click", "li a", function(e) {
							e.preventDefault();
							var targetPageNum = $(this).attr("href");
							pageNum = targetPageNum;
							showList(pageNum);
						});

						/* var modal = $(".modal");
						var modalInputReply = modal.find("input[name='reply']");
						var modalInputReplyer = modal
								.find("input[name='replyer']");
						var modalInputReplyDate = modal
								.find("input[name='replydate']");

						var modalModBtn = $("#modalModBtn");
						var modalRemoveBtn = $("#modalRemoveBtn");
						// 		var modalRegisterBtn = $("#modalRegisterBtn"); */

						$("#btn-chat").on("click", function(e) {
							var reply = $('#btn-input').val();
							if (reply == null || reply == '')
								return;
							var emp_no = 500001; // 임시 값
							console.log(reply);

							var reply = {
								reply : reply,
								emp_no : emp_no,
								b_no : bnoValue
							};
							replyService.add(reply, function(result) {
								alert(result);
								$('#btn-input').val("");
								showList(-1);
							});

							/* modal.find("input").val("");
							modalInputReplyer.removeAttr("readonly");
							modalInputReplyDate.closest("div").hide(); //.show();
							modal.find("button[id != 'modalCloseBtn']").hide();
							modalRegisterBtn.show();
							$(".modal").modal("show"); */

						});

						/* 	modalRegisterBtn.on("click", function(e) {
								var reply = {
									reply : modalInputReply.val(),
									replyer : modalInputReplyer.val(),
									bno : bnoValue
								};
								replyService.add(reply, function(result) {
									alert(result);
									modal.find("input").val("");
									modal.modal("hide");
									showList(-1);
								});
							}); */

						/* $('#modalCloseBtn').on("click", function(e) {
							modal.find("input").val("");
							modal.modal("hide");
						}); */

						//UL
						/* $(".chat")
								.on(
										"click",
										"li",
										function(e) {
											var rno = $(this).data("rno");
											console.log("rno :" + rno);
											replyService
													.get(
															rno,
															function(reply) {
																modalInputReply
																		.val(reply.reply);
																modalInputReplyer
																		.val(
																				reply.replyer)
																		.attr(
																				"readonly",
																				"readonly");
																modalInputReplyDate
																		.closest(
																				"div")
																		.show();
																modalInputReplyDate
																		.val(
																				replyService
																						.displayTime(reply.replydate))
																		.attr(
																				"readonly",
																				"readonly");

																modal
																		.data(
																				"rno",
																				reply.rno);

																modal
																		.find(
																				"button[id != 'modalCloseBtn']")
																		.hide();
																modalModBtn
																		.show();
																modalRemoveBtn
																		.show();

																$('.modal')
																		.modal(
																				"show");
															});

										}); */

						/* modalModBtn.on("click", function(e) {
							var reply = {
								rno : modal.data("rno"),
								reply : modalInputReply.val()
							};
							replyService.update(reply, function(result) {
								alert(result);
								modal.modal("hide");
								showList(pageNum);
							});
						}); */

						/* modalRemoveBtn.on("click", function(e) {
							var rno = modal.data("rno");
							replyService.remove(rno, function(result) {
								alert(result);
								modal.modal("hide");
								showList(pageNum);
							});
						}); */

					}); // END : $(document).ready
</script>

<script type="text/javascript">
	$(document).ready(function() {

		var operForm = $('#operForm');

		$("button[data-oper='modify']").on("click", function(e) {
			operForm.attr("action", "/board/modify").submit();
		});

		$("button[data-oper='list']").on("click", function(e) {
			operForm.find("#bno").remove();
			operForm.attr("action", "/board/list").submit();
		});

	});
</script>

<script>
	/* $(document).ready(function(){
		(function(){
			var bno = '<c:out value="${board.b_no}" />';
			console.log(bno);
			$.getJSON("/board/getAttachList", {bno:bno}, function(arr){
			
				var str = "";
				       
				$(arr).each(function(i, attach){
				//image type
			       if(attach.fileType){
			          var fileCallPath =  encodeURIComponent( attach.uploadPath+ "/s_"+attach.uuid +"_"+attach.fileName);       
			          str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
			          str += "<img src='/display?fileName="+fileCallPath+"'>";
			          str += "</div>";
			          str +"</li>";
			        }else{
			          str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
			          str += "<span> "+ attach.fileName+"</span><br/>";
			          str += "<img src='/resources/img/attach.png'></a>";
			          str += "</div>";
			          str +"</li>";
			        }
				});
		        $(".uploadResult ul").html(str);
		         
			});
		})(); */

	/* $(".uploadResult").on("click", "li", function(e){
		console.log("view image");
		var liObj = $(this);
		
		var path = encodeURIComponent(liObj.data("path") + "/"
				+liObj.data("uuid") + "_"
				+liObj.data("filename"));
		if(liObj.data("type")){
			showImage(path);
		}else{
			self.location = "/download?fileName="+path;
		}
	});// upliadResult li click end  */

	/* function showImage(fileCallPath){
		alert(fileCallPath);
		$(".bigPictureWrapper").css("display", "flex").show();
		$(".bigPicture")
		.html("<img src='/display?fileName="+fileCallPath+"'>")
		.animate({width:'100%', height:'100%'}, 1000);
	} */

	/* $(".bigPictureWrapper").on("click", function(e){
		$(".bigPicture")
		.animate({width:'0%', height:'0%'}, 1000);
		setTimeout(function(){
			$(".bigPictureWrapper").hide();
		}, 1000);
	});
	
	}); */
</script>



<%@include file="../includes/footer.jsp"%>