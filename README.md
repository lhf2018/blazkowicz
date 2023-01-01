# blazkowicz

一个简易的风控引擎

## 本地测试地址：

http://127.0.0.1:8080/prevention/request?prevention_type=TEST&business_identity=TEST&user_id=114515

## todo

* ~~Prevention实体~~
* ~~ConfigurationRule的构造方法~~
* ~~传左参数~~
* 加白
* 并发处理
* ~~聚合根等注解~~
* 更新eventservice
* 更新strategyservice
* 更新规则
* ~~规则参数和策略分开，策略决定规则参数，规则只管规则脚本~~
* RuleConfigurationServiceImpl只更新rule脚本
* ~~Strategy引入规则，同时更新rule参数~~
* ~~rule中重写，只有脚本，没有参数，但会对外展示需要什么参数~~
* 接入dubbo
* 接入nosql
* 内存缓存
* 接入缓存
* 配置态更新
* 日志配置
* RuleRepoImpl

## 更新记录

#### 2022年1月1号

* 更新文件路径
* 接入mysql数据库+mapper实现
* 实现sequenceID

#### 2021年12月31号

* 配置单元测试

## 建表语句

``
create table blazkowicz_sequence
(
sequence_id int not null, sequence_type varchar(63) not null, id int auto_increment, constraint blazkowicz_sequence_pk primary key (id)
);``