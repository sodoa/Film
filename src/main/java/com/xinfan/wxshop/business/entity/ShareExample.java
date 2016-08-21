package com.xinfan.wxshop.business.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShareExample {
    protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public ShareExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andShareidIsNull() {
			addCriterion("shareid is null");
			return (Criteria) this;
		}

		public Criteria andShareidIsNotNull() {
			addCriterion("shareid is not null");
			return (Criteria) this;
		}

		public Criteria andShareidEqualTo(Integer value) {
			addCriterion("shareid =", value, "shareid");
			return (Criteria) this;
		}

		public Criteria andShareidNotEqualTo(Integer value) {
			addCriterion("shareid <>", value, "shareid");
			return (Criteria) this;
		}

		public Criteria andShareidGreaterThan(Integer value) {
			addCriterion("shareid >", value, "shareid");
			return (Criteria) this;
		}

		public Criteria andShareidGreaterThanOrEqualTo(Integer value) {
			addCriterion("shareid >=", value, "shareid");
			return (Criteria) this;
		}

		public Criteria andShareidLessThan(Integer value) {
			addCriterion("shareid <", value, "shareid");
			return (Criteria) this;
		}

		public Criteria andShareidLessThanOrEqualTo(Integer value) {
			addCriterion("shareid <=", value, "shareid");
			return (Criteria) this;
		}

		public Criteria andShareidIn(List<Integer> values) {
			addCriterion("shareid in", values, "shareid");
			return (Criteria) this;
		}

		public Criteria andShareidNotIn(List<Integer> values) {
			addCriterion("shareid not in", values, "shareid");
			return (Criteria) this;
		}

		public Criteria andShareidBetween(Integer value1, Integer value2) {
			addCriterion("shareid between", value1, value2, "shareid");
			return (Criteria) this;
		}

		public Criteria andShareidNotBetween(Integer value1, Integer value2) {
			addCriterion("shareid not between", value1, value2, "shareid");
			return (Criteria) this;
		}

		public Criteria andTitleIsNull() {
			addCriterion("title is null");
			return (Criteria) this;
		}

		public Criteria andTitleIsNotNull() {
			addCriterion("title is not null");
			return (Criteria) this;
		}

		public Criteria andTitleEqualTo(String value) {
			addCriterion("title =", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotEqualTo(String value) {
			addCriterion("title <>", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThan(String value) {
			addCriterion("title >", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThanOrEqualTo(String value) {
			addCriterion("title >=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThan(String value) {
			addCriterion("title <", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThanOrEqualTo(String value) {
			addCriterion("title <=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLike(String value) {
			addCriterion("title like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotLike(String value) {
			addCriterion("title not like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleIn(List<String> values) {
			addCriterion("title in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotIn(List<String> values) {
			addCriterion("title not in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleBetween(String value1, String value2) {
			addCriterion("title between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotBetween(String value1, String value2) {
			addCriterion("title not between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNull() {
			addCriterion("description is null");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNotNull() {
			addCriterion("description is not null");
			return (Criteria) this;
		}

		public Criteria andDescriptionEqualTo(String value) {
			addCriterion("description =", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotEqualTo(String value) {
			addCriterion("description <>", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThan(String value) {
			addCriterion("description >", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
			addCriterion("description >=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThan(String value) {
			addCriterion("description <", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThanOrEqualTo(String value) {
			addCriterion("description <=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLike(String value) {
			addCriterion("description like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotLike(String value) {
			addCriterion("description not like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionIn(List<String> values) {
			addCriterion("description in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotIn(List<String> values) {
			addCriterion("description not in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionBetween(String value1, String value2) {
			addCriterion("description between", value1, value2, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotBetween(String value1, String value2) {
			addCriterion("description not between", value1, value2, "description");
			return (Criteria) this;
		}

		public Criteria andFilmidIsNull() {
			addCriterion("filmid is null");
			return (Criteria) this;
		}

		public Criteria andFilmidIsNotNull() {
			addCriterion("filmid is not null");
			return (Criteria) this;
		}

		public Criteria andFilmidEqualTo(Integer value) {
			addCriterion("filmid =", value, "filmid");
			return (Criteria) this;
		}

		public Criteria andFilmidNotEqualTo(Integer value) {
			addCriterion("filmid <>", value, "filmid");
			return (Criteria) this;
		}

		public Criteria andFilmidGreaterThan(Integer value) {
			addCriterion("filmid >", value, "filmid");
			return (Criteria) this;
		}

		public Criteria andFilmidGreaterThanOrEqualTo(Integer value) {
			addCriterion("filmid >=", value, "filmid");
			return (Criteria) this;
		}

		public Criteria andFilmidLessThan(Integer value) {
			addCriterion("filmid <", value, "filmid");
			return (Criteria) this;
		}

		public Criteria andFilmidLessThanOrEqualTo(Integer value) {
			addCriterion("filmid <=", value, "filmid");
			return (Criteria) this;
		}

		public Criteria andFilmidIn(List<Integer> values) {
			addCriterion("filmid in", values, "filmid");
			return (Criteria) this;
		}

		public Criteria andFilmidNotIn(List<Integer> values) {
			addCriterion("filmid not in", values, "filmid");
			return (Criteria) this;
		}

		public Criteria andFilmidBetween(Integer value1, Integer value2) {
			addCriterion("filmid between", value1, value2, "filmid");
			return (Criteria) this;
		}

		public Criteria andFilmidNotBetween(Integer value1, Integer value2) {
			addCriterion("filmid not between", value1, value2, "filmid");
			return (Criteria) this;
		}

		public Criteria andHeadimgIsNull() {
			addCriterion("headimg is null");
			return (Criteria) this;
		}

		public Criteria andHeadimgIsNotNull() {
			addCriterion("headimg is not null");
			return (Criteria) this;
		}

		public Criteria andHeadimgEqualTo(String value) {
			addCriterion("headimg =", value, "headimg");
			return (Criteria) this;
		}

		public Criteria andHeadimgNotEqualTo(String value) {
			addCriterion("headimg <>", value, "headimg");
			return (Criteria) this;
		}

		public Criteria andHeadimgGreaterThan(String value) {
			addCriterion("headimg >", value, "headimg");
			return (Criteria) this;
		}

		public Criteria andHeadimgGreaterThanOrEqualTo(String value) {
			addCriterion("headimg >=", value, "headimg");
			return (Criteria) this;
		}

		public Criteria andHeadimgLessThan(String value) {
			addCriterion("headimg <", value, "headimg");
			return (Criteria) this;
		}

		public Criteria andHeadimgLessThanOrEqualTo(String value) {
			addCriterion("headimg <=", value, "headimg");
			return (Criteria) this;
		}

		public Criteria andHeadimgLike(String value) {
			addCriterion("headimg like", value, "headimg");
			return (Criteria) this;
		}

		public Criteria andHeadimgNotLike(String value) {
			addCriterion("headimg not like", value, "headimg");
			return (Criteria) this;
		}

		public Criteria andHeadimgIn(List<String> values) {
			addCriterion("headimg in", values, "headimg");
			return (Criteria) this;
		}

		public Criteria andHeadimgNotIn(List<String> values) {
			addCriterion("headimg not in", values, "headimg");
			return (Criteria) this;
		}

		public Criteria andHeadimgBetween(String value1, String value2) {
			addCriterion("headimg between", value1, value2, "headimg");
			return (Criteria) this;
		}

		public Criteria andHeadimgNotBetween(String value1, String value2) {
			addCriterion("headimg not between", value1, value2, "headimg");
			return (Criteria) this;
		}

		public Criteria andCreatedateIsNull() {
			addCriterion("createdate is null");
			return (Criteria) this;
		}

		public Criteria andCreatedateIsNotNull() {
			addCriterion("createdate is not null");
			return (Criteria) this;
		}

		public Criteria andCreatedateEqualTo(Date value) {
			addCriterion("createdate =", value, "createdate");
			return (Criteria) this;
		}

		public Criteria andCreatedateNotEqualTo(Date value) {
			addCriterion("createdate <>", value, "createdate");
			return (Criteria) this;
		}

		public Criteria andCreatedateGreaterThan(Date value) {
			addCriterion("createdate >", value, "createdate");
			return (Criteria) this;
		}

		public Criteria andCreatedateGreaterThanOrEqualTo(Date value) {
			addCriterion("createdate >=", value, "createdate");
			return (Criteria) this;
		}

		public Criteria andCreatedateLessThan(Date value) {
			addCriterion("createdate <", value, "createdate");
			return (Criteria) this;
		}

		public Criteria andCreatedateLessThanOrEqualTo(Date value) {
			addCriterion("createdate <=", value, "createdate");
			return (Criteria) this;
		}

		public Criteria andCreatedateIn(List<Date> values) {
			addCriterion("createdate in", values, "createdate");
			return (Criteria) this;
		}

		public Criteria andCreatedateNotIn(List<Date> values) {
			addCriterion("createdate not in", values, "createdate");
			return (Criteria) this;
		}

		public Criteria andCreatedateBetween(Date value1, Date value2) {
			addCriterion("createdate between", value1, value2, "createdate");
			return (Criteria) this;
		}

		public Criteria andCreatedateNotBetween(Date value1, Date value2) {
			addCriterion("createdate not between", value1, value2, "createdate");
			return (Criteria) this;
		}

		public Criteria andOnlineIsNull() {
			addCriterion("online is null");
			return (Criteria) this;
		}

		public Criteria andOnlineIsNotNull() {
			addCriterion("online is not null");
			return (Criteria) this;
		}

		public Criteria andOnlineEqualTo(Integer value) {
			addCriterion("online =", value, "online");
			return (Criteria) this;
		}

		public Criteria andOnlineNotEqualTo(Integer value) {
			addCriterion("online <>", value, "online");
			return (Criteria) this;
		}

		public Criteria andOnlineGreaterThan(Integer value) {
			addCriterion("online >", value, "online");
			return (Criteria) this;
		}

		public Criteria andOnlineGreaterThanOrEqualTo(Integer value) {
			addCriterion("online >=", value, "online");
			return (Criteria) this;
		}

		public Criteria andOnlineLessThan(Integer value) {
			addCriterion("online <", value, "online");
			return (Criteria) this;
		}

		public Criteria andOnlineLessThanOrEqualTo(Integer value) {
			addCriterion("online <=", value, "online");
			return (Criteria) this;
		}

		public Criteria andOnlineIn(List<Integer> values) {
			addCriterion("online in", values, "online");
			return (Criteria) this;
		}

		public Criteria andOnlineNotIn(List<Integer> values) {
			addCriterion("online not in", values, "online");
			return (Criteria) this;
		}

		public Criteria andOnlineBetween(Integer value1, Integer value2) {
			addCriterion("online between", value1, value2, "online");
			return (Criteria) this;
		}

		public Criteria andOnlineNotBetween(Integer value1, Integer value2) {
			addCriterion("online not between", value1, value2, "online");
			return (Criteria) this;
		}

		public Criteria andSmntIsNull() {
			addCriterion("smnt is null");
			return (Criteria) this;
		}

		public Criteria andSmntIsNotNull() {
			addCriterion("smnt is not null");
			return (Criteria) this;
		}

		public Criteria andSmntEqualTo(Integer value) {
			addCriterion("smnt =", value, "smnt");
			return (Criteria) this;
		}

		public Criteria andSmntNotEqualTo(Integer value) {
			addCriterion("smnt <>", value, "smnt");
			return (Criteria) this;
		}

		public Criteria andSmntGreaterThan(Integer value) {
			addCriterion("smnt >", value, "smnt");
			return (Criteria) this;
		}

		public Criteria andSmntGreaterThanOrEqualTo(Integer value) {
			addCriterion("smnt >=", value, "smnt");
			return (Criteria) this;
		}

		public Criteria andSmntLessThan(Integer value) {
			addCriterion("smnt <", value, "smnt");
			return (Criteria) this;
		}

		public Criteria andSmntLessThanOrEqualTo(Integer value) {
			addCriterion("smnt <=", value, "smnt");
			return (Criteria) this;
		}

		public Criteria andSmntIn(List<Integer> values) {
			addCriterion("smnt in", values, "smnt");
			return (Criteria) this;
		}

		public Criteria andSmntNotIn(List<Integer> values) {
			addCriterion("smnt not in", values, "smnt");
			return (Criteria) this;
		}

		public Criteria andSmntBetween(Integer value1, Integer value2) {
			addCriterion("smnt between", value1, value2, "smnt");
			return (Criteria) this;
		}

		public Criteria andSmntNotBetween(Integer value1, Integer value2) {
			addCriterion("smnt not between", value1, value2, "smnt");
			return (Criteria) this;
		}
	}

	public static class Criteria extends GeneratedCriteria {
		protected Criteria() {
			super();
		}
	}

	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

}