package com.xinfan.wxshop.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xinfan.wxshop.business.constants.SequenceConstants;
import com.xinfan.wxshop.business.dao.FilmDao;
import com.xinfan.wxshop.business.dao.SequenceDao;
import com.xinfan.wxshop.business.entity.Film;
import com.xinfan.wxshop.business.util.QueryParamterUtils;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.page.Pagination;

/**
 * @author huangmin
 * @DATE 2016年7月5日下午8:10:41
 * 
 */
public class FilmService {
	
	@Autowired
	private FilmDao filmDao;
	
	@Autowired
	private SequenceDao sequenceDao;

	public Pagination selectPageList(DataMap map, Pagination page) {

		QueryParamterUtils.addQueryTime(map, "startdate", "endate");

		List list = filmDao.selectPageList(map, page);
		page.setList(list);

		return page;
	}
	
	public Film getFilm(Integer filmId){
		return filmDao.selectByPrimaryKey(filmId);
	}
	
	public void saveFilm(Film film){
		int filmId = this.sequenceDao.getSequence(SequenceConstants.SEQ_FILM);
		film.setFilmId(filmId);
		
		filmDao.insertSelective(film);
	}
	
	public void deleteFilm(Integer filmId){
		filmDao.deleteByPrimaryKey(filmId);
	}
	
	public void updateFilm(Film film){
		filmDao.updateByPrimaryKeySelective(film);
	}
	
	
}
