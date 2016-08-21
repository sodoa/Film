package com.xinfan.wxshop.business.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerExample {
    protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public CustomerExample() {
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

		public Criteria andCustomerIdIsNull() {
			addCriterion("customer_id is null");
			return (Criteria) this;
		}

		public Criteria andCustomerIdIsNotNull() {
			addCriterion("customer_id is not null");
			return (Criteria) this;
		}

		public Criteria andCustomerIdEqualTo(Integer value) {
			addCriterion("customer_id =", value, "customerId");
			return (Criteria) this;
		}

		public Criteria andCustomerIdNotEqualTo(Integer value) {
			addCriterion("customer_id <>", value, "customerId");
			return (Criteria) this;
		}

		public Criteria andCustomerIdGreaterThan(Integer value) {
			addCriterion("customer_id >", value, "customerId");
			return (Criteria) this;
		}

		public Criteria andCustomerIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("customer_id >=", value, "customerId");
			return (Criteria) this;
		}

		public Criteria andCustomerIdLessThan(Integer value) {
			addCriterion("customer_id <", value, "customerId");
			return (Criteria) this;
		}

		public Criteria andCustomerIdLessThanOrEqualTo(Integer value) {
			addCriterion("customer_id <=", value, "customerId");
			return (Criteria) this;
		}

		public Criteria andCustomerIdIn(List<Integer> values) {
			addCriterion("customer_id in", values, "customerId");
			return (Criteria) this;
		}

		public Criteria andCustomerIdNotIn(List<Integer> values) {
			addCriterion("customer_id not in", values, "customerId");
			return (Criteria) this;
		}

		public Criteria andCustomerIdBetween(Integer value1, Integer value2) {
			addCriterion("customer_id between", value1, value2, "customerId");
			return (Criteria) this;
		}

		public Criteria andCustomerIdNotBetween(Integer value1, Integer value2) {
			addCriterion("customer_id not between", value1, value2, "customerId");
			return (Criteria) this;
		}

		public Criteria andDisplaynameIsNull() {
			addCriterion("displayname is null");
			return (Criteria) this;
		}

		public Criteria andDisplaynameIsNotNull() {
			addCriterion("displayname is not null");
			return (Criteria) this;
		}

		public Criteria andDisplaynameEqualTo(String value) {
			addCriterion("displayname =", value, "displayname");
			return (Criteria) this;
		}

		public Criteria andDisplaynameNotEqualTo(String value) {
			addCriterion("displayname <>", value, "displayname");
			return (Criteria) this;
		}

		public Criteria andDisplaynameGreaterThan(String value) {
			addCriterion("displayname >", value, "displayname");
			return (Criteria) this;
		}

		public Criteria andDisplaynameGreaterThanOrEqualTo(String value) {
			addCriterion("displayname >=", value, "displayname");
			return (Criteria) this;
		}

		public Criteria andDisplaynameLessThan(String value) {
			addCriterion("displayname <", value, "displayname");
			return (Criteria) this;
		}

		public Criteria andDisplaynameLessThanOrEqualTo(String value) {
			addCriterion("displayname <=", value, "displayname");
			return (Criteria) this;
		}

		public Criteria andDisplaynameLike(String value) {
			addCriterion("displayname like", value, "displayname");
			return (Criteria) this;
		}

		public Criteria andDisplaynameNotLike(String value) {
			addCriterion("displayname not like", value, "displayname");
			return (Criteria) this;
		}

		public Criteria andDisplaynameIn(List<String> values) {
			addCriterion("displayname in", values, "displayname");
			return (Criteria) this;
		}

		public Criteria andDisplaynameNotIn(List<String> values) {
			addCriterion("displayname not in", values, "displayname");
			return (Criteria) this;
		}

		public Criteria andDisplaynameBetween(String value1, String value2) {
			addCriterion("displayname between", value1, value2, "displayname");
			return (Criteria) this;
		}

		public Criteria andDisplaynameNotBetween(String value1, String value2) {
			addCriterion("displayname not between", value1, value2, "displayname");
			return (Criteria) this;
		}

		public Criteria andAccountIsNull() {
			addCriterion("account is null");
			return (Criteria) this;
		}

		public Criteria andAccountIsNotNull() {
			addCriterion("account is not null");
			return (Criteria) this;
		}

		public Criteria andAccountEqualTo(String value) {
			addCriterion("account =", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountNotEqualTo(String value) {
			addCriterion("account <>", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountGreaterThan(String value) {
			addCriterion("account >", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountGreaterThanOrEqualTo(String value) {
			addCriterion("account >=", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountLessThan(String value) {
			addCriterion("account <", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountLessThanOrEqualTo(String value) {
			addCriterion("account <=", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountLike(String value) {
			addCriterion("account like", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountNotLike(String value) {
			addCriterion("account not like", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountIn(List<String> values) {
			addCriterion("account in", values, "account");
			return (Criteria) this;
		}

		public Criteria andAccountNotIn(List<String> values) {
			addCriterion("account not in", values, "account");
			return (Criteria) this;
		}

		public Criteria andAccountBetween(String value1, String value2) {
			addCriterion("account between", value1, value2, "account");
			return (Criteria) this;
		}

		public Criteria andAccountNotBetween(String value1, String value2) {
			addCriterion("account not between", value1, value2, "account");
			return (Criteria) this;
		}

		public Criteria andPwdIsNull() {
			addCriterion("pwd is null");
			return (Criteria) this;
		}

		public Criteria andPwdIsNotNull() {
			addCriterion("pwd is not null");
			return (Criteria) this;
		}

		public Criteria andPwdEqualTo(String value) {
			addCriterion("pwd =", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdNotEqualTo(String value) {
			addCriterion("pwd <>", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdGreaterThan(String value) {
			addCriterion("pwd >", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdGreaterThanOrEqualTo(String value) {
			addCriterion("pwd >=", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdLessThan(String value) {
			addCriterion("pwd <", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdLessThanOrEqualTo(String value) {
			addCriterion("pwd <=", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdLike(String value) {
			addCriterion("pwd like", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdNotLike(String value) {
			addCriterion("pwd not like", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdIn(List<String> values) {
			addCriterion("pwd in", values, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdNotIn(List<String> values) {
			addCriterion("pwd not in", values, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdBetween(String value1, String value2) {
			addCriterion("pwd between", value1, value2, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdNotBetween(String value1, String value2) {
			addCriterion("pwd not between", value1, value2, "pwd");
			return (Criteria) this;
		}

		public Criteria andSexIsNull() {
			addCriterion("sex is null");
			return (Criteria) this;
		}

		public Criteria andSexIsNotNull() {
			addCriterion("sex is not null");
			return (Criteria) this;
		}

		public Criteria andSexEqualTo(Integer value) {
			addCriterion("sex =", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexNotEqualTo(Integer value) {
			addCriterion("sex <>", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexGreaterThan(Integer value) {
			addCriterion("sex >", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexGreaterThanOrEqualTo(Integer value) {
			addCriterion("sex >=", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexLessThan(Integer value) {
			addCriterion("sex <", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexLessThanOrEqualTo(Integer value) {
			addCriterion("sex <=", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexIn(List<Integer> values) {
			addCriterion("sex in", values, "sex");
			return (Criteria) this;
		}

		public Criteria andSexNotIn(List<Integer> values) {
			addCriterion("sex not in", values, "sex");
			return (Criteria) this;
		}

		public Criteria andSexBetween(Integer value1, Integer value2) {
			addCriterion("sex between", value1, value2, "sex");
			return (Criteria) this;
		}

		public Criteria andSexNotBetween(Integer value1, Integer value2) {
			addCriterion("sex not between", value1, value2, "sex");
			return (Criteria) this;
		}

		public Criteria andWxIdIsNull() {
			addCriterion("wx_id is null");
			return (Criteria) this;
		}

		public Criteria andWxIdIsNotNull() {
			addCriterion("wx_id is not null");
			return (Criteria) this;
		}

		public Criteria andWxIdEqualTo(String value) {
			addCriterion("wx_id =", value, "wxId");
			return (Criteria) this;
		}

		public Criteria andWxIdNotEqualTo(String value) {
			addCriterion("wx_id <>", value, "wxId");
			return (Criteria) this;
		}

		public Criteria andWxIdGreaterThan(String value) {
			addCriterion("wx_id >", value, "wxId");
			return (Criteria) this;
		}

		public Criteria andWxIdGreaterThanOrEqualTo(String value) {
			addCriterion("wx_id >=", value, "wxId");
			return (Criteria) this;
		}

		public Criteria andWxIdLessThan(String value) {
			addCriterion("wx_id <", value, "wxId");
			return (Criteria) this;
		}

		public Criteria andWxIdLessThanOrEqualTo(String value) {
			addCriterion("wx_id <=", value, "wxId");
			return (Criteria) this;
		}

		public Criteria andWxIdLike(String value) {
			addCriterion("wx_id like", value, "wxId");
			return (Criteria) this;
		}

		public Criteria andWxIdNotLike(String value) {
			addCriterion("wx_id not like", value, "wxId");
			return (Criteria) this;
		}

		public Criteria andWxIdIn(List<String> values) {
			addCriterion("wx_id in", values, "wxId");
			return (Criteria) this;
		}

		public Criteria andWxIdNotIn(List<String> values) {
			addCriterion("wx_id not in", values, "wxId");
			return (Criteria) this;
		}

		public Criteria andWxIdBetween(String value1, String value2) {
			addCriterion("wx_id between", value1, value2, "wxId");
			return (Criteria) this;
		}

		public Criteria andWxIdNotBetween(String value1, String value2) {
			addCriterion("wx_id not between", value1, value2, "wxId");
			return (Criteria) this;
		}

		public Criteria andRegdateIsNull() {
			addCriterion("regdate is null");
			return (Criteria) this;
		}

		public Criteria andRegdateIsNotNull() {
			addCriterion("regdate is not null");
			return (Criteria) this;
		}

		public Criteria andRegdateEqualTo(Date value) {
			addCriterion("regdate =", value, "regdate");
			return (Criteria) this;
		}

		public Criteria andRegdateNotEqualTo(Date value) {
			addCriterion("regdate <>", value, "regdate");
			return (Criteria) this;
		}

		public Criteria andRegdateGreaterThan(Date value) {
			addCriterion("regdate >", value, "regdate");
			return (Criteria) this;
		}

		public Criteria andRegdateGreaterThanOrEqualTo(Date value) {
			addCriterion("regdate >=", value, "regdate");
			return (Criteria) this;
		}

		public Criteria andRegdateLessThan(Date value) {
			addCriterion("regdate <", value, "regdate");
			return (Criteria) this;
		}

		public Criteria andRegdateLessThanOrEqualTo(Date value) {
			addCriterion("regdate <=", value, "regdate");
			return (Criteria) this;
		}

		public Criteria andRegdateIn(List<Date> values) {
			addCriterion("regdate in", values, "regdate");
			return (Criteria) this;
		}

		public Criteria andRegdateNotIn(List<Date> values) {
			addCriterion("regdate not in", values, "regdate");
			return (Criteria) this;
		}

		public Criteria andRegdateBetween(Date value1, Date value2) {
			addCriterion("regdate between", value1, value2, "regdate");
			return (Criteria) this;
		}

		public Criteria andRegdateNotBetween(Date value1, Date value2) {
			addCriterion("regdate not between", value1, value2, "regdate");
			return (Criteria) this;
		}

		public Criteria andExpirydateIsNull() {
			addCriterion("expirydate is null");
			return (Criteria) this;
		}

		public Criteria andExpirydateIsNotNull() {
			addCriterion("expirydate is not null");
			return (Criteria) this;
		}

		public Criteria andExpirydateEqualTo(Date value) {
			addCriterion("expirydate =", value, "expirydate");
			return (Criteria) this;
		}

		public Criteria andExpirydateNotEqualTo(Date value) {
			addCriterion("expirydate <>", value, "expirydate");
			return (Criteria) this;
		}

		public Criteria andExpirydateGreaterThan(Date value) {
			addCriterion("expirydate >", value, "expirydate");
			return (Criteria) this;
		}

		public Criteria andExpirydateGreaterThanOrEqualTo(Date value) {
			addCriterion("expirydate >=", value, "expirydate");
			return (Criteria) this;
		}

		public Criteria andExpirydateLessThan(Date value) {
			addCriterion("expirydate <", value, "expirydate");
			return (Criteria) this;
		}

		public Criteria andExpirydateLessThanOrEqualTo(Date value) {
			addCriterion("expirydate <=", value, "expirydate");
			return (Criteria) this;
		}

		public Criteria andExpirydateIn(List<Date> values) {
			addCriterion("expirydate in", values, "expirydate");
			return (Criteria) this;
		}

		public Criteria andExpirydateNotIn(List<Date> values) {
			addCriterion("expirydate not in", values, "expirydate");
			return (Criteria) this;
		}

		public Criteria andExpirydateBetween(Date value1, Date value2) {
			addCriterion("expirydate between", value1, value2, "expirydate");
			return (Criteria) this;
		}

		public Criteria andExpirydateNotBetween(Date value1, Date value2) {
			addCriterion("expirydate not between", value1, value2, "expirydate");
			return (Criteria) this;
		}

		public Criteria andLongitudeIsNull() {
			addCriterion("longitude is null");
			return (Criteria) this;
		}

		public Criteria andLongitudeIsNotNull() {
			addCriterion("longitude is not null");
			return (Criteria) this;
		}

		public Criteria andLongitudeEqualTo(String value) {
			addCriterion("longitude =", value, "longitude");
			return (Criteria) this;
		}

		public Criteria andLongitudeNotEqualTo(String value) {
			addCriterion("longitude <>", value, "longitude");
			return (Criteria) this;
		}

		public Criteria andLongitudeGreaterThan(String value) {
			addCriterion("longitude >", value, "longitude");
			return (Criteria) this;
		}

		public Criteria andLongitudeGreaterThanOrEqualTo(String value) {
			addCriterion("longitude >=", value, "longitude");
			return (Criteria) this;
		}

		public Criteria andLongitudeLessThan(String value) {
			addCriterion("longitude <", value, "longitude");
			return (Criteria) this;
		}

		public Criteria andLongitudeLessThanOrEqualTo(String value) {
			addCriterion("longitude <=", value, "longitude");
			return (Criteria) this;
		}

		public Criteria andLongitudeLike(String value) {
			addCriterion("longitude like", value, "longitude");
			return (Criteria) this;
		}

		public Criteria andLongitudeNotLike(String value) {
			addCriterion("longitude not like", value, "longitude");
			return (Criteria) this;
		}

		public Criteria andLongitudeIn(List<String> values) {
			addCriterion("longitude in", values, "longitude");
			return (Criteria) this;
		}

		public Criteria andLongitudeNotIn(List<String> values) {
			addCriterion("longitude not in", values, "longitude");
			return (Criteria) this;
		}

		public Criteria andLongitudeBetween(String value1, String value2) {
			addCriterion("longitude between", value1, value2, "longitude");
			return (Criteria) this;
		}

		public Criteria andLongitudeNotBetween(String value1, String value2) {
			addCriterion("longitude not between", value1, value2, "longitude");
			return (Criteria) this;
		}

		public Criteria andLatitudeIsNull() {
			addCriterion("latitude is null");
			return (Criteria) this;
		}

		public Criteria andLatitudeIsNotNull() {
			addCriterion("latitude is not null");
			return (Criteria) this;
		}

		public Criteria andLatitudeEqualTo(String value) {
			addCriterion("latitude =", value, "latitude");
			return (Criteria) this;
		}

		public Criteria andLatitudeNotEqualTo(String value) {
			addCriterion("latitude <>", value, "latitude");
			return (Criteria) this;
		}

		public Criteria andLatitudeGreaterThan(String value) {
			addCriterion("latitude >", value, "latitude");
			return (Criteria) this;
		}

		public Criteria andLatitudeGreaterThanOrEqualTo(String value) {
			addCriterion("latitude >=", value, "latitude");
			return (Criteria) this;
		}

		public Criteria andLatitudeLessThan(String value) {
			addCriterion("latitude <", value, "latitude");
			return (Criteria) this;
		}

		public Criteria andLatitudeLessThanOrEqualTo(String value) {
			addCriterion("latitude <=", value, "latitude");
			return (Criteria) this;
		}

		public Criteria andLatitudeLike(String value) {
			addCriterion("latitude like", value, "latitude");
			return (Criteria) this;
		}

		public Criteria andLatitudeNotLike(String value) {
			addCriterion("latitude not like", value, "latitude");
			return (Criteria) this;
		}

		public Criteria andLatitudeIn(List<String> values) {
			addCriterion("latitude in", values, "latitude");
			return (Criteria) this;
		}

		public Criteria andLatitudeNotIn(List<String> values) {
			addCriterion("latitude not in", values, "latitude");
			return (Criteria) this;
		}

		public Criteria andLatitudeBetween(String value1, String value2) {
			addCriterion("latitude between", value1, value2, "latitude");
			return (Criteria) this;
		}

		public Criteria andLatitudeNotBetween(String value1, String value2) {
			addCriterion("latitude not between", value1, value2, "latitude");
			return (Criteria) this;
		}

		public Criteria andRegTypeIsNull() {
			addCriterion("reg_type is null");
			return (Criteria) this;
		}

		public Criteria andRegTypeIsNotNull() {
			addCriterion("reg_type is not null");
			return (Criteria) this;
		}

		public Criteria andRegTypeEqualTo(Integer value) {
			addCriterion("reg_type =", value, "regType");
			return (Criteria) this;
		}

		public Criteria andRegTypeNotEqualTo(Integer value) {
			addCriterion("reg_type <>", value, "regType");
			return (Criteria) this;
		}

		public Criteria andRegTypeGreaterThan(Integer value) {
			addCriterion("reg_type >", value, "regType");
			return (Criteria) this;
		}

		public Criteria andRegTypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("reg_type >=", value, "regType");
			return (Criteria) this;
		}

		public Criteria andRegTypeLessThan(Integer value) {
			addCriterion("reg_type <", value, "regType");
			return (Criteria) this;
		}

		public Criteria andRegTypeLessThanOrEqualTo(Integer value) {
			addCriterion("reg_type <=", value, "regType");
			return (Criteria) this;
		}

		public Criteria andRegTypeIn(List<Integer> values) {
			addCriterion("reg_type in", values, "regType");
			return (Criteria) this;
		}

		public Criteria andRegTypeNotIn(List<Integer> values) {
			addCriterion("reg_type not in", values, "regType");
			return (Criteria) this;
		}

		public Criteria andRegTypeBetween(Integer value1, Integer value2) {
			addCriterion("reg_type between", value1, value2, "regType");
			return (Criteria) this;
		}

		public Criteria andRegTypeNotBetween(Integer value1, Integer value2) {
			addCriterion("reg_type not between", value1, value2, "regType");
			return (Criteria) this;
		}

		public Criteria andStateIsNull() {
			addCriterion("state is null");
			return (Criteria) this;
		}

		public Criteria andStateIsNotNull() {
			addCriterion("state is not null");
			return (Criteria) this;
		}

		public Criteria andStateEqualTo(Integer value) {
			addCriterion("state =", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateNotEqualTo(Integer value) {
			addCriterion("state <>", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateGreaterThan(Integer value) {
			addCriterion("state >", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateGreaterThanOrEqualTo(Integer value) {
			addCriterion("state >=", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateLessThan(Integer value) {
			addCriterion("state <", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateLessThanOrEqualTo(Integer value) {
			addCriterion("state <=", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateIn(List<Integer> values) {
			addCriterion("state in", values, "state");
			return (Criteria) this;
		}

		public Criteria andStateNotIn(List<Integer> values) {
			addCriterion("state not in", values, "state");
			return (Criteria) this;
		}

		public Criteria andStateBetween(Integer value1, Integer value2) {
			addCriterion("state between", value1, value2, "state");
			return (Criteria) this;
		}

		public Criteria andStateNotBetween(Integer value1, Integer value2) {
			addCriterion("state not between", value1, value2, "state");
			return (Criteria) this;
		}

		public Criteria andShareuserIsNull() {
			addCriterion("shareuser is null");
			return (Criteria) this;
		}

		public Criteria andShareuserIsNotNull() {
			addCriterion("shareuser is not null");
			return (Criteria) this;
		}

		public Criteria andShareuserEqualTo(Integer value) {
			addCriterion("shareuser =", value, "shareuser");
			return (Criteria) this;
		}

		public Criteria andShareuserNotEqualTo(Integer value) {
			addCriterion("shareuser <>", value, "shareuser");
			return (Criteria) this;
		}

		public Criteria andShareuserGreaterThan(Integer value) {
			addCriterion("shareuser >", value, "shareuser");
			return (Criteria) this;
		}

		public Criteria andShareuserGreaterThanOrEqualTo(Integer value) {
			addCriterion("shareuser >=", value, "shareuser");
			return (Criteria) this;
		}

		public Criteria andShareuserLessThan(Integer value) {
			addCriterion("shareuser <", value, "shareuser");
			return (Criteria) this;
		}

		public Criteria andShareuserLessThanOrEqualTo(Integer value) {
			addCriterion("shareuser <=", value, "shareuser");
			return (Criteria) this;
		}

		public Criteria andShareuserIn(List<Integer> values) {
			addCriterion("shareuser in", values, "shareuser");
			return (Criteria) this;
		}

		public Criteria andShareuserNotIn(List<Integer> values) {
			addCriterion("shareuser not in", values, "shareuser");
			return (Criteria) this;
		}

		public Criteria andShareuserBetween(Integer value1, Integer value2) {
			addCriterion("shareuser between", value1, value2, "shareuser");
			return (Criteria) this;
		}

		public Criteria andShareuserNotBetween(Integer value1, Integer value2) {
			addCriterion("shareuser not between", value1, value2, "shareuser");
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