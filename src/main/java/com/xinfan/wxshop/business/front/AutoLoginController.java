package com.xinfan.wxshop.business.front;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xinfan.wxshop.business.entity.Customer;
import com.xinfan.wxshop.business.service.CustomerService;
import com.xinfan.wxshop.business.service.KeymovieService;
import com.xinfan.wxshop.business.service.MovieService;
import com.xinfan.wxshop.business.service.SearchkeyService;
import com.xinfan.wxshop.business.util.LoginSessionUtils;
import com.xinfan.wxshop.business.util.RequestUtils;
import com.xinfan.wxshop.business.util.WeixinUtils;
import com.xinfan.wxshop.business.vo.OAuthInfo;
import com.xinfan.wxshop.business.vo.UserInfo;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.config.FileConfig;
import com.xinfan.wxshop.common.security.DesUtils;

/**
 * @author huangmin
 * @DATE 2016年7月10日下午9:43:12
 * 
 */
@Controller
@RequestMapping("/weixin")
public class AutoLoginController  {
	
        private static final Logger logger = LoggerFactory.getLogger(AutoLoginController.class);
        
        @Autowired
        private MovieService movieService;
        
        @Autowired
        private KeymovieService keymovieService;
        
        @Autowired
        private SearchkeyService searchkeyService;
        
    	@Autowired
    	private CustomerService CustomerService;
    	
    	
    	@RequestMapping("/login.html")
    	public void loginAndRegist(HttpServletRequest request, HttpServletResponse response){
    		try {
				weixin1(request,response);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}

    	public void weixin1(HttpServletRequest request, HttpServletResponse response) throws IOException {

    		String appid = FileConfig.getInstance().getString("weixin.appid");
    		String backUri = FileConfig.getInstance().getString("weixin.authlogin.backurl");
    		
    		String fid = request.getParameter("fid");

    		// String backUri = "http://***/topayServlet";
    		// 授权后要跳转的链接所需的参数一般有会员号，金额，订单号之类，
    		// 最好自己带上一个加密字符串将金额加上一个自定义的key用MD5签名或者自己写的签名,
    		// 比如 Sign = %3D%2F%CS%

    		backUri = backUri + "?fid=" + fid;
    		// URLEncoder.encode 后可以在backUri 的url里面获取传递的所有参数
    		backUri = URLEncoder.encode(backUri);

    		// scope 参数视各自需求而定，这里用scope=snsapi_base 不弹出授权页面直接授权目的只获取统一支付接口的openid
    		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" + "appid=" + appid + "&redirect_uri=" + backUri
    				+ "&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
    		response.sendRedirect(url);
    	}

    	@RequestMapping("/weixin_auto_login_back.html")
    	public void weixin2(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    		String code = request.getParameter("code");
    		String fid = request.getParameter("fid");
    		
    		String appid = FileConfig.getInstance().getString("weixin.appid");
    		String appsecret = FileConfig.getInstance().getString("weixin.appsecret");
    		
    		
    		if (null != code && !"".equals(code)) {
    			logger.info("==============[OAuthServlet]获取网页授权code不为空，code=" + code);
    			// 根据code换取openId
    			OAuthInfo oa = WeixinUtils.getOAuthOpenId(appid, appsecret, code);
    			
    			if (oa != null) {
    				Customer customer = CustomerService.getByWeixinId(oa.getOpenId());
    				if(customer == null){
    					UserInfo info = WeixinUtils.getUserInfo(oa.getAccessToken(), oa.getOpenId());
    					if(info!=null){
    						String account = "";
    						String password = "12345678";
    						String displayName = info.getNickname();
    						
    						DataMap attributes = new DataMap();
    						attributes.put("wx_id", info.getOpenid());
    						attributes.put("sex", info.getSex());
    						attributes.put("share_id", "");
    						
    						this.CustomerService.regist(account, password, displayName, attributes);
    						
    						LoginSessionUtils.setCustomerUserSessionMap(attributes);
    						
    						request.getRequestDispatcher("/see.jspx?fid="+fid).forward(request, response);
    						
    					}
    				}
    			}
    			
    		} else {
    			logger.info("==============[OAuthServlet]获取网页授权code失败！");
    		}
    		
    		request.getRequestDispatcher("/").forward(request, response);
    	}
    	
    	
    	
    	
    	
    	
    
}