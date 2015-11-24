<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container container-fluid">
	
	<div class="users row" >

	</div>

</div>




<script>

var urlGetDevs = "http://localhost:8085/rest/ressources/getDevs";
var urlGetScrumMasters = "http://localhost:8085/rest/ressources/getScrumMasters";
var urlGetProductOwner = "http://localhost:8085/rest/ressources/getProductOwner";


function requeteGetJson(url){
	
	var datas;
	$.ajax({
	
		url: url,
		dataType: "json",
		async: false,
		success: function(data){
			datas = data;
		},
		error: function(jqXHR, textStatus, ex) {
			alert(textStatus + "," + ex + "," + jqXHR.responseText);
		}
	});
		
	return datas;	
}


var datasSM = requeteGetJson(urlGetScrumMasters);
var datasDevs = requeteGetJson(urlGetDevs);
var datasPO = requeteGetJson(urlGetProductOwner);

function listUsers(datas , type){
	for(var i = 0; i < datas.length; i++){
		$(".users").append("\
		<div class=\"col-md-3 col-sm-3\">\
			<div id=\"" + type +"_" + i + "\" class=\"" + type +"\">\
				<dl>\
					<dt> Nom  </dt>\
					<dd id=\"name\">" + datas[i].firstname + " " + datas[i].lastname + "\
					<dt> email </dt>\
					<dd id=\"email\">" + datas[i].email + "</dd>\
					<dt> role </dt>\
					<dd id=\"role\">"+ datas[i].role.name + "</dd>\
					<dt> Projet </dt>\
					<dd id=\"project name\">" + "TO DO" + "</dd>\
				</dl>\
			</div>\
		</div>");
		
	}
}

listUsers(datasSM, "scrumMaster");
listUsers(datasDevs, "dev");
listUsers(datasPO, "productOwner");


</script>