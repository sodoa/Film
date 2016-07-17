/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/7/17 16:33:26                           */
/*==============================================================*/


drop table if exists seq_name;

drop table if exists t_bill;

drop table if exists t_config;

drop table if exists t_customer;

drop table if exists t_dict;

drop table if exists t_film;

drop table if exists t_keymovie;

drop table if exists t_movie;

drop table if exists t_searchkey;

/*==============================================================*/
/* Table: seq_name                                              */
/*==============================================================*/
create function `seq_fun`(seq_name_val varchar(50)) returns int(11)
begin
 update seq_name set val=last_insert_id(val+1) where seq_name=seq_name_val; 
 return last_insert_id();
end

create table seq_name
(
   seq_name             varchar(20) not null,
   val                  bigint(20) not null,
   primary key (seq_name)
);

alter table seq_name comment '序列表';

/*==============================================================*/
/* Table: t_bill                                                */
/*==============================================================*/
create table t_bill
(
   bill_id              int not null,
   amount               int not null comment '金额',
   paytime              datetime,
   orderid              varchar(60),
   customer_id          int,
   wx_id                varchar(100),
   primary key (bill_id)
);

/*==============================================================*/
/* Table: t_config                                              */
/*==============================================================*/
create table t_config
(
   id                   varchar(64) not null,
   name                 varchar(64),
   value                varchar(512),
   primary key (id)
);

/*==============================================================*/
/* Table: t_customer                                            */
/*==============================================================*/
create table t_customer
(
   customer_id          int not null auto_increment,
   displayname          varchar(60),
   account              varchar(60),
   pwd                  varchar(40),
   sex                  int default 1,
   wx_id                varchar(100),
   regdate              timestamp,
   expirydate           timestamp comment '失效日期',
   longitude            varchar(100),
   latitude             varchar(100),
   reg_type             int default 1 comment '1:wx 2:reg',
   state                int default 1,
   primary key (customer_id)
);

/*==============================================================*/
/* Table: t_dict                                                */
/*==============================================================*/
create table t_dict
(
   dtype                varchar(100) not null,
   dname                varchar(100),
   vvalue               varchar(100) not null,
   vname                varchar(512),
   primary key (dtype, vvalue)
);

alter table t_dict comment '字典';

/*==============================================================*/
/* Table: t_film                                                */
/*==============================================================*/
create table t_film
(
   film_id              int not null,
   name                 varchar(60) not null comment '名称',
   resume               varchar(512) comment '简述',
   type                 int not null comment '类型',
   url                  varchar(512) not null comment '地址',
   director             varchar(120) comment '导演',
   actor                varchar(120) comment '主演',
   country              int comment '国家',
   publish              datetime comment '发行日期',
   format               int comment '视频格式',
   picture              varchar(512) comment '图片地址',
   player               int not null comment '播放器类型',
   player_url           varchar(512) comment '播发器下载地址',
   primary key (film_id)
);

/*==============================================================*/
/* Table: t_keymovie                                            */
/*==============================================================*/
create table t_keymovie
(
   keymovie_id          int not null,
   film_id              int not null comment '影片ID',
   name                 varchar(60) comment '电影名称',
   picture              varchar(512) comment '图片地址',
   word                 varchar(120) comment '关键字',
   primary key (keymovie_id)
);

/*==============================================================*/
/* Table: t_movie                                               */
/*==============================================================*/
create table t_movie
(
   movie_id             int not null,
   film_id              int not null comment '影片ID',
   name                 varchar(60) comment '电影名称',
   picture              varchar(512) comment '图片地址',
   type                 int comment '视频类型 1院线同步电影 2网络事件视频 3激情伦理片',
   primary key (movie_id)
);

/*==============================================================*/
/* Table: t_searchkey                                           */
/*==============================================================*/
create table t_searchkey
(
   searchkey_id         int not null,
   word                 varchar(120) not null comment '关键字',
   times                int comment '搜索次数',
   create_time          datetime,
   update_time          datetime,
   primary key (searchkey_id)
);

