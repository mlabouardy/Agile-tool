/*
 * 
 * Pour acceder à l'api rest tu dois utiliser l'url de base citée en dessous , suivie du service que tu souhaites utiliser.
 * Les éléments entre accolades sont à remplacer par les valeurs.
 * Tous les liens des fonctionnalités sont visibles dans la classe Ressources Rest ( dans le package com.bordeaux.res )
 * 
 */


/* var baseUrl = "http://localhost:8085/rest/ressources"; // URL DE TOUTES LES FONCTIONNALITES


var urlGetDevs =  baseUrl + "/getDevs"; // URL DE LA FONCTIONNALITE REST

var urlgetDendencies = baseUrl +  "/getDependenciesFrom/{id}"; // REMPLACER ID PAR LA VALEUR VOULUE ( EN L'OCCURRENCE 
//L'ID DE LA TACHE DONT ON SOUHAITE RECUPERER LES DEPENCES)

var urlgetTasksFromSprint = baseUrl + "/getTasksFromSprint/{id}";

var urlgetCurrentSprintFromScrumMaster = baseUrl + "/getCurrentSprintFromScrumMaster/{id}";

*/


// FONCTION D'OBTENTION DES DONNEES D'UNE FONCTIONNALITE REST DE TYPE GET, AU FORMAT JSON
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

/* var datasDevs = requeteGetJson(urlGetDevs); // STOCKAGE DES DONNEES 

function listUsers(datas , type){
	
	for(var i = 0; i < datas.length; i++){
	
		 alert(datas[i].champs); // ON PARCOURT LE TABLEAU D'OBJETS JSON , EN RECUPERANT LES CHAMPS QUI NOUS INTERESSENT APRES LE "."
		
	}
}*/