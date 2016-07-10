package com.xinfan.wxshop.business.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.xinfan.wxshop.business.constants.SequenceConstants;
import com.xinfan.wxshop.business.dao.FilmDao;
import com.xinfan.wxshop.business.dao.KeymovieDao;
import com.xinfan.wxshop.business.dao.SequenceDao;
import com.xinfan.wxshop.business.entity.Film;
import com.xinfan.wxshop.business.entity.Keymovie;
import com.xinfan.wxshop.business.entity.MovieExample;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.page.Pagination;

/**
 * @author huangmin
 * @DATE 2016年7月10日下午1:01:54
 * 
 */
public class KeymovieService {
	
	@Autowired
	private KeymovieDao movieDao;
	
	@Autowired
	private FilmDao filmDao;
	
	@Autowired
	private SequenceDao sequenceDao;

	public Pagination selectPageList(DataMap map, Pagination page) {

		List list = movieDao.selectPageList(map, page);
		page.setList(list);

		return page;
	}
	
	public List<Keymovie> selectList(DataMap map){
		MovieExample example = new MovieExample();
		if(StringUtils.isNotBlank(map.getString("type"))){
			example.createCriteria().andTypeEqualTo(map.getInt("type"));	
		}
		
		return movieDao.selectByExample(example);
	}
	
	public Keymovie getMovie(Integer movieId){
		return movieDao.selectByPrimaryKey(movieId);
	}
	
	public void deleteMovie(Integer movieId){
		movieDao.deleteByPrimaryKey(movieId);
	}
	
	public void saveMovie(Keymovie movie){
		int movieId = this.sequenceDao.getSequence(SequenceConstants.SEQ_KEYMOVIE);
		movie.setKeymovieId(movieId);
		
		Film film = filmDao.selectByPrimaryKey(movie.getFilmId());
		movie.setName(film.getName());
		movie.setPicture(film.getPicture());

		movieDao.insertSelective(movie);
	}
	
	public void updateMovie(Keymovie movie){
		movieDao.updateByPrimaryKeySelective(movie);
	}

}
