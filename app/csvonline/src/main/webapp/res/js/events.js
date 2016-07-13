function addIframeTab(tab, url, title, iconCls) {
	console.log(iconCls);
	var iframe = '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:98%;" ></iframe>';
	// if http url  jia js close
    if(url && url.indexOf('http') == 0)
    	iframe = '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:98%;" security="restricted" sandbox="" ></iframe>';
	var t = $(tab);
	//如果tab页面已经打开了，则先关闭
	if (t.tabs('exists', title))	
		t.tabs('close', title);
	t.tabs('add', {
		title : title,
		content : iframe,
		iconCls: iconCls,
        closable : true
	});		
}