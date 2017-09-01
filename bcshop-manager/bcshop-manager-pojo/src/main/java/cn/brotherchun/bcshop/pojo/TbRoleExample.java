package cn.brotherchun.bcshop.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TbRoleExample implements Serializable{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbRoleExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRolenameIsNull() {
            addCriterion("rolename is null");
            return (Criteria) this;
        }

        public Criteria andRolenameIsNotNull() {
            addCriterion("rolename is not null");
            return (Criteria) this;
        }

        public Criteria andRolenameEqualTo(String value) {
            addCriterion("rolename =", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameNotEqualTo(String value) {
            addCriterion("rolename <>", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameGreaterThan(String value) {
            addCriterion("rolename >", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameGreaterThanOrEqualTo(String value) {
            addCriterion("rolename >=", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameLessThan(String value) {
            addCriterion("rolename <", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameLessThanOrEqualTo(String value) {
            addCriterion("rolename <=", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameLike(String value) {
            addCriterion("rolename like", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameNotLike(String value) {
            addCriterion("rolename not like", value, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameIn(List<String> values) {
            addCriterion("rolename in", values, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameNotIn(List<String> values) {
            addCriterion("rolename not in", values, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameBetween(String value1, String value2) {
            addCriterion("rolename between", value1, value2, "rolename");
            return (Criteria) this;
        }

        public Criteria andRolenameNotBetween(String value1, String value2) {
            addCriterion("rolename not between", value1, value2, "rolename");
            return (Criteria) this;
        }

        public Criteria andRoledescIsNull() {
            addCriterion("roledesc is null");
            return (Criteria) this;
        }

        public Criteria andRoledescIsNotNull() {
            addCriterion("roledesc is not null");
            return (Criteria) this;
        }

        public Criteria andRoledescEqualTo(String value) {
            addCriterion("roledesc =", value, "roledesc");
            return (Criteria) this;
        }

        public Criteria andRoledescNotEqualTo(String value) {
            addCriterion("roledesc <>", value, "roledesc");
            return (Criteria) this;
        }

        public Criteria andRoledescGreaterThan(String value) {
            addCriterion("roledesc >", value, "roledesc");
            return (Criteria) this;
        }

        public Criteria andRoledescGreaterThanOrEqualTo(String value) {
            addCriterion("roledesc >=", value, "roledesc");
            return (Criteria) this;
        }

        public Criteria andRoledescLessThan(String value) {
            addCriterion("roledesc <", value, "roledesc");
            return (Criteria) this;
        }

        public Criteria andRoledescLessThanOrEqualTo(String value) {
            addCriterion("roledesc <=", value, "roledesc");
            return (Criteria) this;
        }

        public Criteria andRoledescLike(String value) {
            addCriterion("roledesc like", value, "roledesc");
            return (Criteria) this;
        }

        public Criteria andRoledescNotLike(String value) {
            addCriterion("roledesc not like", value, "roledesc");
            return (Criteria) this;
        }

        public Criteria andRoledescIn(List<String> values) {
            addCriterion("roledesc in", values, "roledesc");
            return (Criteria) this;
        }

        public Criteria andRoledescNotIn(List<String> values) {
            addCriterion("roledesc not in", values, "roledesc");
            return (Criteria) this;
        }

        public Criteria andRoledescBetween(String value1, String value2) {
            addCriterion("roledesc between", value1, value2, "roledesc");
            return (Criteria) this;
        }

        public Criteria andRoledescNotBetween(String value1, String value2) {
            addCriterion("roledesc not between", value1, value2, "roledesc");
            return (Criteria) this;
        }

        public Criteria andShortcodeIsNull() {
            addCriterion("shortcode is null");
            return (Criteria) this;
        }

        public Criteria andShortcodeIsNotNull() {
            addCriterion("shortcode is not null");
            return (Criteria) this;
        }

        public Criteria andShortcodeEqualTo(String value) {
            addCriterion("shortcode =", value, "shortcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeNotEqualTo(String value) {
            addCriterion("shortcode <>", value, "shortcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeGreaterThan(String value) {
            addCriterion("shortcode >", value, "shortcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeGreaterThanOrEqualTo(String value) {
            addCriterion("shortcode >=", value, "shortcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeLessThan(String value) {
            addCriterion("shortcode <", value, "shortcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeLessThanOrEqualTo(String value) {
            addCriterion("shortcode <=", value, "shortcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeLike(String value) {
            addCriterion("shortcode like", value, "shortcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeNotLike(String value) {
            addCriterion("shortcode not like", value, "shortcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeIn(List<String> values) {
            addCriterion("shortcode in", values, "shortcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeNotIn(List<String> values) {
            addCriterion("shortcode not in", values, "shortcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeBetween(String value1, String value2) {
            addCriterion("shortcode between", value1, value2, "shortcode");
            return (Criteria) this;
        }

        public Criteria andShortcodeNotBetween(String value1, String value2) {
            addCriterion("shortcode not between", value1, value2, "shortcode");
            return (Criteria) this;
        }

        public Criteria andRolecodeIsNull() {
            addCriterion("rolecode is null");
            return (Criteria) this;
        }

        public Criteria andRolecodeIsNotNull() {
            addCriterion("rolecode is not null");
            return (Criteria) this;
        }

        public Criteria andRolecodeEqualTo(String value) {
            addCriterion("rolecode =", value, "rolecode");
            return (Criteria) this;
        }

        public Criteria andRolecodeNotEqualTo(String value) {
            addCriterion("rolecode <>", value, "rolecode");
            return (Criteria) this;
        }

        public Criteria andRolecodeGreaterThan(String value) {
            addCriterion("rolecode >", value, "rolecode");
            return (Criteria) this;
        }

        public Criteria andRolecodeGreaterThanOrEqualTo(String value) {
            addCriterion("rolecode >=", value, "rolecode");
            return (Criteria) this;
        }

        public Criteria andRolecodeLessThan(String value) {
            addCriterion("rolecode <", value, "rolecode");
            return (Criteria) this;
        }

        public Criteria andRolecodeLessThanOrEqualTo(String value) {
            addCriterion("rolecode <=", value, "rolecode");
            return (Criteria) this;
        }

        public Criteria andRolecodeLike(String value) {
            addCriterion("rolecode like", value, "rolecode");
            return (Criteria) this;
        }

        public Criteria andRolecodeNotLike(String value) {
            addCriterion("rolecode not like", value, "rolecode");
            return (Criteria) this;
        }

        public Criteria andRolecodeIn(List<String> values) {
            addCriterion("rolecode in", values, "rolecode");
            return (Criteria) this;
        }

        public Criteria andRolecodeNotIn(List<String> values) {
            addCriterion("rolecode not in", values, "rolecode");
            return (Criteria) this;
        }

        public Criteria andRolecodeBetween(String value1, String value2) {
            addCriterion("rolecode between", value1, value2, "rolecode");
            return (Criteria) this;
        }

        public Criteria andRolecodeNotBetween(String value1, String value2) {
            addCriterion("rolecode not between", value1, value2, "rolecode");
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