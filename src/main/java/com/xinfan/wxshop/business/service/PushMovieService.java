package com.xinfan.wxshop.business.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.xinfan.wxshop.business.constants.SequenceConstants;
import com.xinfan.wxshop.business.dao.FilmDao;
import com.xinfan.wxshop.business.dao.PushMovieDao;
import com.xinfan.wxshop.business.dao.SequenceDao;
import com.xinfan.wxshop.business.entity.Film;
import com.xinfan.wxshop.business.entity.Movie;
import com.xinfan.wxshop.business.entity.PushMovie;
import com.xinfan.wxshop.business.entity.PushMovieExample;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.page.Pagination;

/**
 * @author huangmin
 * @DATE 2016年7月10日下午1:01:54
 * 
 */
public class PushMovieService {
	
	@Autowired
	private PushMovieDao pushmovieDao;
	
	@Autowired
	private FilmDao filmDao;
	
	@Autowired
	private SequenceDao sequenceDao;
	
	public Pagination selectPageList(DataMap map, Pagination page) {
		List list = pushmovieDao.selectPageList(map, page);
		page.setList(list);

		return page;
	}
	
	public List<PushMovie> selectList(DataMap map){
		PushMovieExample example = new PushMovieExample();
		example.setOrderByClause("pushmovie_id desc");
		if(StringUtils.isNotBlank(map.getString("pushId"))){
			example.createCriteria().andPushIdEqualTo(map.getInt("pushId"));	
		}
		return pushmovieDao.selectByExample(example);
	}
	
	public PushMovie getMovie(Integer movieId){
		return pushmovieDao.selectByPrimaryKey(movieId);
	}
	
	public void deleteMovie(Integer movieId){
		pushmovieDao.deleteByPrimaryKey(movieId);
	}
	
	public void saveMovie(PushMovie movie){
		int pushmovieId = this.sequenceDao.getSequence(SequenceConstants.SEQ_PUSHMOVIE);
		movie.setPushmovieId(pushmovieId);
		
		Film film = filmDao.selectByPrimaryKey(movie.getFilmId());
		movie.setName(film.getName());
		movie.setPicture(film.getPicture());
		movie.setResume(film.getResume());
		
		pushmovieDao.insertSelective(movie);
	}
	
	public void updateMovie(PushMovie movie){
		pushmovieDao.updateByPrimaryKeySelective(movie);
	}

}
