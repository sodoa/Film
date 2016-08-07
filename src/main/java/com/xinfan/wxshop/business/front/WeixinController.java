package com.xinfan.wxshop.business.front;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.sd4324530.fastweixin.message.Article;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.NewsMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;
import com.github.sd4324530.fastweixin.message.req.MenuEvent;
import com.github.sd4324530.fastweixin.message.req.ScanCodeEvent;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;
import com.xinfan.wxshop.business.entity.Customer;
import com.xinfan.wxshop.business.entity.Keymovie;
import com.xinfan.wxshop.business.entity.Movie;
import com.xinfan.wxshop.business.entity.Searchkey;
import com.xinfan.wxshop.business.pay.weixin.MenuManger;
import com.xinfan.wxshop.business.pay.weixin.WxNotifyUtils;
import com.xinfan.wxshop.business.service.CustomerService;
import com.xinfan.wxshop.business.service.KeymovieService;
import com.xinfan.wxshop.business.service.MovieService;
import com.xinfan.wxshop.business.service.SearchkeyService;
import com.xinfan.wxshop.business.util.ConfigUtils;
import com.xinfan.wxshop.business.util.WeixinUtils;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.config.FileConfig;

/**
 * @author huangmin
 * @DATE 2016年7月10日下午9:43:12
 * 
 */
@Controller
@RequestMapping("/weixin")
public class WeixinController extends WeixinControllerSupport {
	
        private static final Logger log = LoggerFactory.getLogger(WeixinController.class);
        
        @Autowired
        private MovieService movieService;
        
        @Autowired
        private KeymovieService keymovieService;
        
        @Autowired
        private SearchkeyService searchkeyService;
        
    	@Autowired
    	private CustomerService CustomerService;
    	
        //设置TOKEN，用于绑定微信服务器
        @Override
        protected String getToken() {
            return FileConfig.getInstance().getString("weixin.token");
        }
        
        //重写父类方法，处理对应的微信消息
        @Override
        protected BaseMsg handleTextMsg(TextReqMsg msg) {
            String content = msg.getContent();
            log.debug("用户发送到服务器的内容:", content);
            
            String domain = FileConfig.getInstance().getString("domain.name");
            
            //snsapi_base  --   snsapi_userinfo
            //电影分类
            if("1".equals(content.trim()) || "2".equals(content.trim()) || "3".equals(content.trim())
        		|| "4".equals(content.trim())|| "5".equals(content.trim())
            		){
            	DataMap map = new DataMap();
            	map.put("type", content.trim());
            	List<Movie> movieList = movieService.selectList(map);
            	if(CollectionUtils.isNotEmpty(movieList)){
            		List<Article> articles = new ArrayList<Article>();
            		log.debug("################" +movieList.size());
            		for(Movie movie:movieList){
            			log.debug("################" +movie.getName());
            			Article article = new Article();
            			article.setTitle(movie.getName());
            			article.setPicUrl(domain + StringUtils.replace( movie.getPicture(), "\\", "/"));
            			article.setUrl(domain+"/movie/see.jspx?fid="+movie.getFilmId());
            			articles.add(article);
            		}
            		return new NewsMsg(articles);
            	}else{
            		return new TextMsg(ConfigUtils.getValue("nofilm","对不起，没有您搜索的影片！" 
            				+ ConfigUtils.getValue("subscribe","欢迎关注！")));
            	}
            
            }else if(StringUtils.isNotBlank(content) && content.startsWith("我想看")){
            	//我想看
            	String word =content.replaceFirst("我想看", "");
            	DataMap map = new DataMap();
            	map.put("word", word);
            	List<Searchkey> list = searchkeyService.selectList(map);
            	if(CollectionUtils.isNotEmpty(list)){
            		Searchkey searchkey = list.get(0);
                	searchkey.setTimes(searchkey.getTimes() + 1);
                	searchkey.setUpdateTime(new Date());
                	searchkeyService.updateMovie(searchkey);
            	}else{
            		Searchkey searchkey = new Searchkey();
                	searchkey.setWord(content.replaceFirst("我想看", ""));
                	searchkey.setTimes(1);
                	searchkeyService.saveSearchkey(searchkey);
            	}
            	return new TextMsg(ConfigUtils.getValue("iwantsee","已经收到您的请求，我们将会为您尽快添加！"
            			+ ConfigUtils.getValue("subscribe","欢迎关注！")));
            	
            }else{
            	//关键字电影
            	DataMap map = new DataMap();
            	map.put("name", content.trim());
            	List<Keymovie> movieList = keymovieService.selectList(map);
            	
            	if(CollectionUtils.isNotEmpty(movieList)){
            		List<Article> articles = new ArrayList<Article>();
            		log.debug("################" +movieList.size());
            		for(Keymovie movie:movieList){
            			log.debug("################" +movie.getName());
            			Article article = new Article();
            			article.setTitle(movie.getName());
            			article.setPicUrl(domain + StringUtils.replace( movie.getPicture(), "\\", "/"));
            			article.setUrl(domain+"/movie/see.jspx?fid="+movie.getFilmId());
            			
            			articles.add(article);
            		}
            		return new NewsMsg(articles);
            	}else{
            		return new TextMsg(ConfigUtils.getValue("nofilm","对不起，没有您搜索的影片！")
            				+ ConfigUtils.getValue("subscribe","欢迎关注！"));
            	}
            }
            
        }
		@Override
		protected BaseMsg handleSubscribe(BaseEvent event) {
			return new TextMsg(ConfigUtils.getValue("subscribe","欢迎关注！"));
		}
		//欢迎关注爆品电影！回复“1”获取最新院线电影，回复“2”获取网络事件影片，回复“3”获取激情伦理电影，
		//或者直接回复影片名获取电影。如果没有您想看的电影，请回复“我想看XXX”,我们将为您尽快添加。 
        
		//惨淡
		@RequestMapping("/menu")
		public void createmenu(HttpServletRequest request, HttpServletResponse response) throws IOException {

			String opt = request.getParameter("opt");
			if ("create".equals(opt)) {
				MenuManger.createMenu();
			} else if ("del".equals(opt)) {
				MenuManger.deleteMenu();
			} else {
				response.getOutputStream().println("error code");
			}
		}

		//点击菜单
		@Override
		protected BaseMsg handleMenuClickEvent(MenuEvent event) {
			return new TextMsg(ConfigUtils.getValue("subscribe","欢迎关注！"));
		}

		//扫描二维码
		@Override
		protected BaseMsg handleScanCodeEvent(ScanCodeEvent event) {
			if("subscribe".equals(event.getEvent())){
				int customerId = WeixinUtils.getScanEventFromId(event.getEventKey());
				
				CustomerService.updateRewardData(customerId);
				Customer customer = CustomerService.getById(customerId);
				
				String wxId = event.getFromUserName();
				Customer newcustomer = CustomerService.getByWeixinId(wxId);
				
				WxNotifyUtils.customerShareJoinNotify(customer.getWxId(), newcustomer.getDisplayname(), customer.getExpirydate());
			}
			
			return super.handleScanCodeEvent(event);
		}

}