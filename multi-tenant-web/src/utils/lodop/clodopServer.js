var downLoadUrl = window.AppData.config.baseUrl.WebSite + 'uploads/printPlug/'; //todo 配置下载地址

//====页面引用CLodop云打印必须的JS文件：====
if (needCLodop()) {
	var head = document.head || document.getElementsByTagName("head")[0] || document.documentElement;
	var oscript = document.createElement("script");
	oscript.src ="http://localhost:8000/CLodopfuncs.js?priority=3";
	head.insertBefore( oscript,head.firstChild );

    oscript = document.createElement("script");
    oscript.src ="http://localhost:8001/CLodopfuncs.js?priority=1";
    head.insertBefore( oscript,head.firstChild );
	//本机云打印的后补端口8001：
	oscript = document.createElement("script");
	oscript.src ="http://localhost:18000/CLodopfuncs.js?priority=2";
	head.insertBefore( oscript,head.firstChild );
};

//====获取LODOP对象的主过程：====
function getLodop(oOBJECT,oEMBED, errCB){
    var timestamp = Date.parse(new Date());
    timestamp = timestamp / 1000;
    var fileName = "CLodop_Setup_for_Win32NT.exe?timeStamp="+timestamp;
    var strCLodopInstall="<br>打印服务插件未安装，请下载安装后请刷新页面。<br><a href='"+ downLoadUrl + fileName + " '  target='_self'><font color='#5e8af5'>[点击下载]</font></a>";
    var strCLodopUpdate="<br>打印服务插件需升级！点击这里<a href='"+ downLoadUrl + fileName + "' target='_self'><font color='#5e8af5'>执行升级</font></a>，升级后请刷新页面。";
    var LODOP;
    try{
        try{ LODOP=getCLodop();} catch(err) {};
        if (!LODOP && document.readyState!=="complete") {errCB("打印插件还" +
			"没准备好，请稍后再试！"); return null};
        if (!LODOP) {
        	errCB(strCLodopInstall);
        	return null;

        }
		if (CLODOP.CVERSION<"2.0.6.8") {
        	errCB(strCLodopUpdate);
		}
		if (oEMBED && oEMBED.parentNode) oEMBED.parentNode.removeChild(oEMBED);
		if (oOBJECT && oOBJECT.parentNode) oOBJECT.parentNode.removeChild(oOBJECT);
        //===如下空白位置适合调用统一功能(如注册语句、语言选择等):===
        LODOP.SET_LICENSES("","8718895D5DE0FA07EF80F1C5C548626F","C94CEE276DB2187AE6B65D56B3FC2848","");
        //=========================================== ================
        return LODOP;
    } catch(err) {errCB("getLodop出错:"+err);};
};

function needCLodop(){
    return true;
};

function CLodopHasInstalled() {
	try{ var clodopObj=getCLodop();} catch(err) {};
    var isInstalled = !!clodopObj;
	return isInstalled;
}


