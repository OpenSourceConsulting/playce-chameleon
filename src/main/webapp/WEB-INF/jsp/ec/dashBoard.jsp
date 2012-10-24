<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<style>
.toggleDiv {border:		1px solid Black; width: 400px; padding: 10px 10px 10px 10px;}
</style>
<script>  
function setToogleContents(obj) {
	$(obj).parent().parent().find('.toggleDiv').toggle();
}
</script>
<div style="position:absolute; top: 10px; left: 10px;">
	<div>
		<p><b>Getting Started</b> <img src="<c:url value='/images/jquery.selectBox-arrow.gif'/>" onclick="setToogleContents(this);" /></p>
		<div class="toggleDiv">
			<div> 
			  <p>To start using ............</p>
			  <p>...........................</p>
			</div>
		</div>
	</div>
	<div style="padding-top: 20px;">
		<p><b>Service Health</b> <img src="<c:url value='/images/jquery.selectBox-arrow.gif'/>" onclick="setToogleContents(this);" /></p>
		<div class="toggleDiv">
			<p>service status</p>
			<table border="1">
				<tr>
					<th width="180">current Status</th>
					<th width="180">Details</th>
				</tr>
				<tr>
					<td>Amazon ...</td>
					<td>normally</td>
				</tr>
			</table>
			<br/>
			<p>Availability Zone Status</p>
			<table border="1">
				<tr>
					<th width="180">current Status</th>
					<th width="180">Details</th>
				</tr>
				<tr>
					<td>ap-south-1a ...</td>
					<td>normally</td>
				</tr>
				<tr>
					<td>ap-south-1b ...</td>
					<td>normally</td>
				</tr>
			</table>
		</div>
	</div>
	<div style="padding-top: 20px;">
		<p><b>My Resource</b> <img src="<c:url value='/images/jquery.selectBox-arrow.gif'/>" onclick="setToogleContents(this);" /></p>
		<div class="toggleDiv">
			<div> 
			  <p>To start using ............</p>
			  <p>...........................</p>
			</div>
		</div>
	</div>
</div>
<div style="position:absolute; top: 10px; left: 450px;">
	<div >
		<p><b>Event</b> <img src="<c:url value='/images/jquery.selectBox-arrow.gif'/>" onclick="setToogleContents(this);" /></p>
		<div class="toggleDiv">
			<div> 
			  <p>Asia pacific(Singapore) : No events</p>
			</div>
		</div>
	</div>
	<div style="padding-top: 20px;">
		<p><b>Related Link</b> <img src="<c:url value='/images/jquery.selectBox-arrow.gif'/>" onclick="setToogleContents(this);" /></p>
		<div class="toggleDiv">
			<div> 
			  <p>Getting Started Guide</p>
			  <p>Docuemntation</p>
			  <p>All EC2 Resources</p>
			  <p>....</p>
			</div>
		</div>
	</div>
</div>