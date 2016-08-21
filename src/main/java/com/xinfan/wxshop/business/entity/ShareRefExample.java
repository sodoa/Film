package com.xinfan.wxshop.business.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShareRefExample {
    protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public ShareRefExample() {
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

		public Criteria andRefidIsNull() {
			addCriterion("refid is null");
			return (Criteria) this;
		}

		public Criteria andRefidIsNotNull() {
			addCriterion("refid is not null");
			return (Criteria) this;
		}

		public Criteria andRefidEqualTo(Integer value) {
			addCriterion("refid =", value, "refid");
			return (Criteria) this;
		}

		public Criteria andRefidNotEqualTo(Integer value) {
			addCriterion("refid <>", value, "refid");
			return (Criteria) this;
		}

		public Criteria andRefidGreaterThan(Integer value) {
			addCriterion("refid >", value, "refid");
			return (Criteria) this;
		}

		public Criteria andRefidGreaterThanOrEqualTo(Integer value) {
			addCriterion("refid >=", value, "refid");
			return (Criteria) this;
		}

		public Criteria andRefidLessThan(Integer value) {
			addCriterion("refid <", value, "refid");
			return (Criteria) this;
		}

		public Criteria andRefidLessThanOrEqualTo(Integer value) {
			addCriterion("refid <=", value, "refid");
			return (Criteria) this;
		}

		public Criteria andRefidIn(List<Integer> values) {
			addCriterion("refid in", values, "refid");
			return (Criteria) this;
		}

		public Criteria andRefidNotIn(List<Integer> values) {
			addCriterion("refid not in", values, "refid");
			return (Criteria) this;
		}

		public Criteria andRefidBetween(Integer value1, Integer value2) {
			addCriterion("refid between", value1, value2, "refid");
			return (Criteria) this;
		}

		public Criteria andRefidNotBetween(Integer value1, Integer value2) {
			addCriterion("refid not between", value1, value2, "refid");
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

		public Criteria andCustomeridIsNull() {
			addCriterion("customerid is null");
			return (Criteria) this;
		}

		public Criteria andCustomeridIsNotNull() {
			addCriterion("customerid is not null");
			return (Criteria) this;
		}

		public Criteria andCustomeridEqualTo(Integer value) {
			addCriterion("customerid =", value, "customerid");
			return (Criteria) this;
		}

		public Criteria andCustomeridNotEqualTo(Integer value) {
			addCriterion("customerid <>", value, "customerid");
			return (Criteria) this;
		}

		public Criteria andCustomeridGreaterThan(Integer value) {
			addCriterion("customerid >", value, "customerid");
			return (Criteria) this;
		}

		public Criteria andCustomeridGreaterThanOrEqualTo(Integer value) {
			addCriterion("customerid >=", value, "customerid");
			return (Criteria) this;
		}

		public Criteria andCustomeridLessThan(Integer value) {
			addCriterion("customerid <", value, "customerid");
			return (Criteria) this;
		}

		public Criteria andCustomeridLessThanOrEqualTo(Integer value) {
			addCriterion("customerid <=", value, "customerid");
			return (Criteria) this;
		}

		public Criteria andCustomeridIn(List<Integer> values) {
			addCriterion("customerid in", values, "customerid");
			return (Criteria) this;
		}

		public Criteria andCustomeridNotIn(List<Integer> values) {
			addCriterion("customerid not in", values, "customerid");
			return (Criteria) this;
		}

		public Criteria andCustomeridBetween(Integer value1, Integer value2) {
			addCriterion("customerid between", value1, value2, "customerid");
			return (Criteria) this;
		}

		public Criteria andCustomeridNotBetween(Integer value1, Integer value2) {
			addCriterion("customerid not between", value1, value2, "customerid");
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