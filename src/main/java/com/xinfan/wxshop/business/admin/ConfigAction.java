package com.xinfan.wxshop.business.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xinfan.wxshop.business.constants.BizConstants;
import com.xinfan.wxshop.business.entity.Config;
import com.xinfan.wxshop.business.model.DataGrid;
import com.xinfan.wxshop.business.model.JSONResult;
import com.xinfan.wxshop.business.service.ConfigService;
import com.xinfan.wxshop.business.util.RequestUtils;
import com.xinfan.wxshop.common.base.BizException;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.cache.CacheHelper;
import com.xinfan.wxshop.common.page.Pagination;
import com.xinfan.wxshop.common.util.LogUtils;

@Controller
@RequestMapping("/admin/config")
public class ConfigAction {
	
	@Autowired
	private ConfigService configService;
	
	@RequestMapping("/list.jspx")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("/admin/config/list");
		
		List<Config> list= configService.getList();
		mv.addObject("list", list);
		
		return mv;
	}
	
	@RequestMapping("/data.jspx")
	public @ResponseBody DataGrid data() {
		List list = configService.getList();
		Pagination page = new Pagination();
		page.setList(list);
		return new DataGrid(page);
	}

	@RequestMapping("/save_update.jspx")
	public @ResponseBody
	JSONResult save() {
		DataMap paramter = RequestUtils.getRequestDataMap();
		JSONResult result = null;
		try {
			String configs = paramter.getString("configs");
			if (configs != null && configs.trim().length() > 0) {
				String[] args = configs.split("##");
				for(String arg : args){
					String key = arg.split("#")[0];
					String value = arg.split("#")[1];
					Config config = new Config();
					config.setId(key.toLowerCase());
					config.setValue(value);
					configService.update(config);
				}
				
				CacheHelper.getInstance().refresh(BizConstants.CACHE_KEY_CONFIGCACHE);
			}
			
			result = JSONResult.success();
		} catch (BizException e) {
			LogUtils.error(e.getMessage(), e);
			result = new JSONResult();
			result.putResult(1).putMessage(e.getMessage());
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
			result = new JSONResult();
			result.putResult(1).putMessage("保存异常");
		}

		return result;
	}
	
}
