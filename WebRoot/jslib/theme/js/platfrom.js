/*! js weidian 2015-01-26 */
var appid=M.urlQuery("appid");"cn.geili.IShopping2"==appid||"com.geili.koudai"==appid||"com.koudai.iShoppingDev"==appid||"cn.geili.KoudaiGouwu"==appid?M.setCookie("platfrom","koudai"):"com.koudai.weidian.buyer"==appid?M.setCookie("platfrom","weidian_buyer"):"com.koudai.haidai"==appid?M.setCookie("platfrom","daigou"):"com.chunfen.brand5"==appid&&M.setCookie("platfrom","banjia");