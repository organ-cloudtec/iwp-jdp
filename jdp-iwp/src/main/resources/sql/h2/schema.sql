drop table if exists RBAC_USER;

create table RBAC_USER (
	RECID varchar(255) not null,
	USR_NAME varchar(255) not null,
	USR_ID varchar(255) not null,
	PWD varchar(255),
	ORG_RECID varchar(255) not null,
	REMARKS varchar(255),
	DEL_FLAG varchar(1) default 0,
    primary key (RECID)
);

--机构表
drop table if exists RBAC_ORG;

create table RBAC_ORG (
	RECID varchar(255) not null,
	CODE varchar(255) not null,
	GRADE int ,
	NAME varchar(255) not null,
	SORT int,
	PARENT_ID varchar(255),
	REMARKS varchar(255),
	DEL_FLAG varchar(1) default 0,
    primary key (RECID)
);
--菜单
drop table if exists RBAC_MENU;

create table RBAC_MENU (
	RECID varchar(255) not null,
	NAME varchar(255) not null,
	NAME_MNG varchar(255) not null ,
	PERMISSION_FLAG varchar(255) ,
	TARGET varchar(255),
	URL varchar(255),
	PARENT_ID varchar(255),
	PARENTS varchar(255),
	SORT int,
	ISSHOW varchar(1),
	ICON varchar(255),
	REMARKS varchar(255),
	DEL_FLAG varchar(1) default 0,
    primary key (RECID)
);
--角色
drop table if exists RBAC_ROLE;

create table RBAC_ROLE(
	RECID varchar(255) not null,
	NAME varchar(255) not null,
	NAME_MNG varchar(255) not null ,
	REMARKS varchar(255),
	DEL_FLAG varchar(1) default 0,
    primary key (RECID)
);
--用户角色
drop table if exists RBAC_USER_ROLE;

create table RBAC_USER_ROLE(
	USER_RECID varchar(255) not null,
	ROLE_RECID varchar(255) not null,
	REMARKS varchar(255),
	DEL_FLAG varchar(1) default 0,
 	primary key (USER_RECID,ROLE_RECID)
);
--角色，资源
drop table if exists RBAC_ROLE_MENU ;

create table RBAC_ROLE_MENU (
        ROLE_RECID varchar(255) not null,
		MENU_RECID varchar(255) not null,
		REMARKS varchar(255),
		DEL_FLAG varchar(1) default 0,
	 primary key (ROLE_RECID,MENU_RECID)
)

--基础数据
drop table if exists RBAC_DICT ;

create table RBAC_DICT (
        RECID varchar(255) not null,
        LABEL varchar(255) not null,
		VALUE varchar(255) not null,
		TYPE varchar(255)  not null,
		DESCRIPTION varchar(255) not null,
		SORT int,
		REMARKS varchar(255),
		DEL_FLAG varchar(1) default 0,
	 primary key (RECID)
)