# 需求
1、10*10的停车场，可以容纳最多100辆车。

2、每个车位最多停1辆车

3、入库需要发放凭证

4、出库需要验证凭证

5、停满无法发放凭证

6、凭证不得重复，不具备字面上逻辑关联性，且一次性UUID

# Testing
## 停车获取凭证
given：车

when：停车场未满

then：发放凭证

else：提示已满
## 取车消费凭证
given：凭证

when：凭证有效

then：返回车

else：提示错误
## 生成唯一凭证
given：时间，车信息

when：当入参随机，返回结果不一致

then：验证通过

else：验证失败
## 验证凭证
given：凭证

when：不满足凭证格式（快速失败验证）

then：验证失败

when：通过公钥私钥验证凭证有效

then：验证通过

else：验证失败

## 多次使用凭证失效问题
given：凭证
when：正常凭证
then：成功
when：已消费过的凭证
then：已消费
## 凭证过期
given：凭证
when：正常凭证
then：成功
when：已过期的凭证
then：已过期
## 消费过期凭证
given：过期凭证
when：消费过期凭证
then：成功