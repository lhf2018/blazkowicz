# blazkowicz

[未实现]~~一个简易的风控引擎~~

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
* strategy中的rule修改和公共configuratioservice中的rule修改之间的关系--只有公共能改，策略中不能改
* strategyMapper
* ruleMapper
* ~~rule是否要用id概念~~
* 搜索rule
* 搜索策略
* 接入dubbo
* 接入nosql
* 内存缓存
* ~~接入缓存~~
* 配置态更新
* ~~日志配置~~
* RuleRepoImpl
* StrategyRepoImpl
* 修改Strategy内容
* ruleDO
* todo扩展除了脚本以外更多类型的规则（黑白名单库等）
* 规则的外部使用在规则引擎中区分
* rule规则的创建和更新等，看是否收口到Event中
* RunningStrategy中如何读取到strategy的内容
* PreventionConfigInfService

## 更新记录
#### 2023年4月15
* StrategyMapper

#### 2023年4月7

* Strategy聚合根优化
* 增加PreventionConfigInfService
* 优化LogicUtil代码

#### 2023年4月3

* 接入redis
* 完善StrategyRepo
* 完善实现RuleConfigurationServiceImpl
* 移除ruleManageInfService，上层直接使用ruleRepo
* rule增加id，变为实体

#### 2023年3月4

* 重命名类+完善strategy的实现
* 规则工厂

#### 2023年2月21

* 把event和prevention的划分为聚合根
* 对rule的定义做了完善

#### 2023年1月14

* 使用jxel：一条规则可以处理多条脚本识别逻辑结果

#### 2023年1月8号

* ruleDO相关

#### 2023年1月1号

* 更新文件路径
* 接入mysql数据库+mapper实现
* 实现sequenceID

#### 2022年12月31号

* 配置单元测试

## 建表语句

``
create table blazkowicz_sequence
(
sequence_id int not null, sequence_type varchar(63) not null, id int auto_increment, constraint blazkowicz_sequence_pk primary key (id)
);``
