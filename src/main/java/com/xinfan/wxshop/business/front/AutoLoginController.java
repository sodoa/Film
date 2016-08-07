package com.xinfan.wxshop.business.front;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.xinfan.wxshop.business.util.WeixinUtils;
import com.xinfan.wxshop.business.vo.LoginSession;
import com.xinfan.wxshop.business.vo.OAuthInfo;
import com.xinfan.wxshop.business.vo.UserInfo;
import com.xinfan.wxshop.common.config.FileConfig;
import com.xinfan.wxshop.common.util.JSONUtils;

/**
 * @author huangmin
 * @DATE 2016年7月10日下午9:43:12
 * 
 */
@Controller
@RequestMapping("/movie")
public class AutoLoginController  extends BaseController{
	
        private static final Logger logger = LoggerFactory.getLogger(AutoLoginController.class);
        
        @Autowired
        private MovieService movieService;
        
        @Autowired
        private KeymovieService keymovieService;
        
        @Autowired
        private SearchkeyService searchkeyService;
        
    	@Autowired
    	private CustomerService CustomerService;
    	
    	
    	@RequestMapping("/login.jspx")
    	public void loginAndRegist(HttpServletRequest request, HttpServletResponse response){
    		try {
    			loginAuth(request,response);
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
				this.toError(e.getMessage());
			}
    	}

    	public void loginAuth(HttpServletRequest request, HttpServletResponse response) throws IOException {

    		String appid = FileConfig.getInstance().getString("weixin.appid");
    		String backUri = FileConfig.getInstance().getDomainUrlString("weixin.authlogin.backurl");
    		
    		String type = request.getParameter("type");
    		String fid = request.getParameter("fid");

    		backUri = backUri + "?type=" + type + "&fid=" + fid;
    		backUri = URLEncoder.encode(backUri);

    		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" + "appid=" + appid + "&redirect_uri=" + backUri
    				+ "&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
    		response.sendRedirect(url);
    	}

    	@RequestMapping("/login_back.jspx")
    	public String loginAuthBack(HttpServletRequest request, HttpServletResponse response)  {
    		
    		String code = request.getParameter("code");
    		String type = request.getParameter("type");
    		String fid = request.getParameter("fid");
    		
    		String appid = FileConfig.getInstance().getString("weixin.appid");
    		String appsecret = FileConfig.getInstance().getString("weixin.appsecret");
    		
    		
    		if (null != code && !"".equals(code)) {
    			logger.info("==============[OAuthServlet]获取网页授权code不为空，code=" + code);
    			// 根据code换取openId
    			OAuthInfo oa = WeixinUtils.getOAuthOpenId(appid, appsecret, code);
    			
    			if (oa != null) {
					UserInfo info = WeixinUtils.getUserInfo(oa.getAccessToken(), oa.getOpenId());
					if(info!=null){
						String account = "";
						String password = "12345678";
						String displayName = info.getNickname();
						
						Customer customer = CustomerService.regist(account, password, displayName, info.getOpenid(),info.getSex());
						
						LoginSession session = new LoginSession();
						session.setCustomerId(customer.getCustomerId());
						session.setDisplayName(customer.getDisplayname());
						session.setWx_id(info.getOpenid());
						session.setExpiryDate(customer.getExpirydate());
						
						logger.debug("#####auth user data : "+JSONUtils.toJSONString(customer) +" login sucess");
						
						LoginSessionUtils.setCustomerUserSessionMap(session);
						
						if("2".equals(type)){
							return "redirect:/share/index.jspx";
						}else{
							return "redirect:/movie/see.jspx?fid="+fid;
						}
					}
    			}
    		} 
    		
    		logger.info("==============[OAuthServlet]获取网页授权code失败！");
    		
    		return "redirect:/err.jspx?msg=" + "微信登录授权失败，请同意微信授权！";
    	}
    	
}