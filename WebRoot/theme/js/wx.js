function GetClearUrlPath()
{
　　var url = document.location.toString();
    var relUrl = url;

　　if(relUrl.indexOf("?") != -1){
　　　　relUrl = relUrl.split("?")[0];
　　}

	if(relUrl.indexOf("#") != -1){
		relUrl = relUrl.split("?")[0];
	}
　　return relUrl;
}
