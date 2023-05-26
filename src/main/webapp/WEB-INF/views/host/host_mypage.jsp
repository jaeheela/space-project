<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Space Moon</title>
<style type="text/css">
table {
	margin-left: auto;
	margin-right: auto;
}
.out_box {
	padding-top: 20px;
}

h2 {
	margin-top: 50px;
}

.def_info {
	margin-top: 50px;
}

.det_info {
	margin-top: 15px;
}

.id {
	padding-right: 15px;
	padding-bottom: 15px;
}

.id2 {
	padding-bottom: 15px;
}
</style>
</head>
<body>
	<div class="content_wraper">
		<section class="content_member_wrap">
			<h2>프로필 관리</h2>
			<div class="profile_inner">
				<div class="profile_box">
					<div class="def_info">
						<strong>${loginHost.getHName() }</strong>
					</div>
					<div class="det_info">
						<table>
							<tr>
								<th scope="row" class="id">아이디</th>
								<td class="id2">${loginHost.getHId() }</td>
							</tr>
							<tr>
								<th scope="row" class="id">이메일</th>
								<td class="id2">${loginHost.getHEmail() }</td>
							</tr>
							<tr>
								<th scope="row" class="id">연락처</th>
								<td class="id2">${loginHost.getHPhone() }</td>
							</tr>
						</table>
					</div>
					<div class="out_box">
						<button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/host/modify'">호스트 정보 변경하기</button>
						<button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/host/remove'">서비스 탈퇴하기</button>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
</html>