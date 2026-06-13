# blazkowicz

[未实现]~~一个简易的风控引擎~~

## 控制台

启动后访问：**http://127.0.0.1:8080/**

功能模块：

- **概览** — 规则/策略/运行态统计
- **规则配置** — 创建与编辑 Groovy 规则
- **策略配置** — 绑定规则与处置策略
- **运行态测试** — 实时调用防控接口并查看结果

## 文档

- [本地 Wiki：项目现状与规划](./wiki/项目现状与规划.md)

## API 测试地址

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

见 `blazkowicz-start/src/main/resources/db/schema.sql`，应用启动时会自动建表。

核心表：

- `tb_blazkowicz_rule` — 规则脚本与条件
- `tb_blazkowicz_strategy` — 策略绑定规则与处置配置
- `blazkowicz_sequence` — ID 发号
