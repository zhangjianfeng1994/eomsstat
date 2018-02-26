-- Create table
create table TAW_STATISTIC_INDICATE
(
  id                  VARCHAR2(34) not null,
  indicate_name       VARCHAR2(32) not null,
  indicate_value      VARCHAR2(528),
  complex_flag        VARCHAR2(32),
  condition           VARCHAR2(500),
  operateflag         VARCHAR2(128),
  group_type          VARCHAR2(64),
  indicatorfir_add    VARCHAR2(15),
  indicatorend_add    VARCHAR2(15),
  percent_sign        VARCHAR2(15) default 1030102,
  indicate_over_value VARCHAR2(20),
  delete_status       VARCHAR2(1),
  is_grasp            VARCHAR2(1),
  INDICATE_DESCRIBE   VARCHAR2(1000)
)

tablespace EOMSV35
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column TAW_STATISTIC_INDICATE.id
  is 'id';
comment on column TAW_STATISTIC_INDICATE.indicate_name
  is '指标名称';
comment on column TAW_STATISTIC_INDICATE.indicate_value
  is '指标值';
comment on column TAW_STATISTIC_INDICATE.complex_flag
  is '复合指标符号';
comment on column TAW_STATISTIC_INDICATE.condition
  is '查询条件';
comment on column TAW_STATISTIC_INDICATE.operateflag
  is '操作符号';
comment on column TAW_STATISTIC_INDICATE.group_type
  is '报表归属';
comment on column TAW_STATISTIC_INDICATE.indicatorfir_add
  is '首操作指标';
comment on column TAW_STATISTIC_INDICATE.indicatorend_add
  is '尾操作指标';
comment on column TAW_STATISTIC_INDICATE.percent_sign
  is '百分比符号';
comment on column TAW_STATISTIC_INDICATE.indicate_over_value
  is '阀值';
comment on column TAW_STATISTIC_INDICATE.delete_status
  is '删除状态集';
comment on column TAW_STATISTIC_INDICATE.is_grasp
  is '是否攥取【0：非攥取，1：攥取】';
comment on column TAW_STATISTIC_INDICATE.INDICATE_DESCRIBE
  is '指标描述';
  
  
  
create table TAW_STATISTIC_PARAMETER
(
  id              VARCHAR2(34) not null,
  param_name      VARCHAR2(32) not null,
  param_eng       VARCHAR2(128),
  param_code      VARCHAR2(128),
  param_code_type VARCHAR2(128),
  parent_param_id VARCHAR2(128),
  group_type      VARCHAR2(128),
  param_condition VARCHAR2(500),
  delete_status   VARCHAR2(1),
  eoms_param_type VARCHAR2(128)
)
tablespace EOMSV35
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column TAW_STATISTIC_PARAMETER.id
  is 'id';
comment on column TAW_STATISTIC_PARAMETER.param_name
  is '维度名称';
comment on column TAW_STATISTIC_PARAMETER.param_eng
  is '维度英文名称';
comment on column TAW_STATISTIC_PARAMETER.param_code
  is '维度编码';
comment on column TAW_STATISTIC_PARAMETER.param_code_type
  is '维度编码类型';
comment on column TAW_STATISTIC_PARAMETER.parent_param_id
  is '维度父id类型';
comment on column TAW_STATISTIC_PARAMETER.group_type
  is '维度报表归属';
comment on column TAW_STATISTIC_PARAMETER.param_condition
  is '标题头方式维度查询条件';
comment on column TAW_STATISTIC_PARAMETER.delete_status
  is '删除状态集';
comment on column TAW_STATISTIC_PARAMETER.eoms_param_type
  is 'eoms结构树维度列【dept_system：部门字段，none：非eoms系统字段】';
  
  

create table TAW_STATISTIC_DETAIL
(
  id              VARCHAR2(32) not null,
  detail_num      NUMBER,
  detail_num_name VARCHAR2(64),
  group_type      VARCHAR2(32),
  url_flag        VARCHAR2(1),
  url             VARCHAR2(200)
)

tablespace EOMSV35
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
  
  

 create table TAW_STATISTIC_DETAIL_ITEM
(
  id                  VARCHAR2(32) not null,
  detail_id           VARCHAR2(32) not null,
  detail_column_eng   VARCHAR2(128),
  detail_column_china VARCHAR2(128),
  transfer_type       VARCHAR2(32),
  transfer_code       VARCHAR2(32)
)
tablespace EOMSV35
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
  
  
  
create table TAW_STATISTIC_MODEL
(
  id             VARCHAR2(32) not null,
  model_name     VARCHAR2(32) not null,
  cycle          VARCHAR2(64),
  is_share_flag  VARCHAR2(1),
  is_common_flag VARCHAR2(1),
  group_type     VARCHAR2(32),
  detail_id      VARCHAR2(32),
  compute_rule   VARCHAR2(200),
  userid         VARCHAR2(100),
  model_type     VARCHAR2(10),
  xml_name       VARCHAR2(200),
  choose_before  VARCHAR2(3)
)

tablespace EOMSV35
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column TAW_STATISTIC_MODEL.id
  is 'id';
comment on column TAW_STATISTIC_MODEL.model_name
  is '模板名称';
comment on column TAW_STATISTIC_MODEL.cycle
  is '模板类型';
comment on column TAW_STATISTIC_MODEL.is_share_flag
  is '模板共享';
comment on column TAW_STATISTIC_MODEL.is_common_flag
  is '公共模板';
comment on column TAW_STATISTIC_MODEL.group_type
  is '报表归属';
comment on column TAW_STATISTIC_MODEL.detail_id
  is '详单报表';
comment on column TAW_STATISTIC_MODEL.compute_rule
  is '算法规则';
comment on column TAW_STATISTIC_MODEL.userid
  is '用户归属';
comment on column TAW_STATISTIC_MODEL.model_type
  is '模板类型【auto，xml】';
comment on column TAW_STATISTIC_MODEL.xml_name
  is 'xml文件的名称';
  
  
  
create table TAW_STATISTIC_MODEL_ITEM
(
  id                   VARCHAR2(32) not null,
  model_id             VARCHAR2(64),
  parent_item_id       VARCHAR2(32) not null,
  type                 VARCHAR2(32),
  item_id              VARCHAR2(32),
  is_leaf              VARCHAR2(1),
  item_code            VARCHAR2(32),
  parent_model_item_id VARCHAR2(32)
)

tablespace EOMSV35
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column TAW_STATISTIC_MODEL_ITEM.id
  is '模板项id';
comment on column TAW_STATISTIC_MODEL_ITEM.model_id
  is '模板id';
comment on column TAW_STATISTIC_MODEL_ITEM.parent_item_id
  is '模板项父类id';
comment on column TAW_STATISTIC_MODEL_ITEM.type
  is '项目类型【1：指标和指标类型维度  2：维度和攥取维度】';
comment on column TAW_STATISTIC_MODEL_ITEM.item_id
  is '项目id';
comment on column TAW_STATISTIC_MODEL_ITEM.is_leaf
  is '是否为叶子节点【0：非叶子节点 1：叶子节点】';
comment on column TAW_STATISTIC_MODEL_ITEM.item_code
  is '元素的编码';
comment on column TAW_STATISTIC_MODEL_ITEM.parent_model_item_id
  is '模板项的父类id';
  
  