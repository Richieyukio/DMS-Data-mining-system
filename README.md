## 需求说明：
（1）注册需要录入信息（用户名、密码、性别、爱好、地址等）。
（2）登录需要输入信息（账号、密码）。
（3）进入主页后可以应用日志与物流项目，填写日志信息（日志ID，用户名，登陆地点，登录IP，登录状态），物流信息（物流ID，目的地，经手人，收货人，物流状态），输入之后保存，等待后期使用。
（4）采用表格显示数据库中录入的信息
（5）输入错误弹出对话框，提醒用户问题所在，进行更改实时更新信息。
（6）自动保存后可离开界面退出系统。
具体功能模块如图所示：

![image](https://user-images.githubusercontent.com/60099213/141681442-8793843e-181f-4040-be65-c05a7f982567.png)

## 系统总体设计
2.1编写目的
根据需求分析文档，初步提出问题的解决方案，以及软件系统的体系结构和数据结构的设计方案，并写出书面文档总体设计说明书，为下一步进行详细设计做准备。
2.2总体设计
2.2.1功能划分
该系统可以按功能进行模块划分，如图1所示。
![image](https://user-images.githubusercontent.com/60099213/141681410-ff01defe-5453-4187-861d-a5f7bc7a960d.png)
图1  系统模块图
## 数据库及表
test数据库：
gather_logrec表:存放用户日志数据匹配记录
gather_transport表：存放用户物流数据匹配记录
matched_transport表：已匹配的物流数据id记录
matched_logrec表：已匹配的日志数据id记录
user表：存放用户信息记录
## 主要业务流程
 		首先要注册用户（如有账号可以直接登录），然后登录进主界面，然后进		行第一步，数据采集，从键盘上录入数据。
  	第二步，进行数据的匹配，如只有物流或者日志数据可进行单独的匹配。
  	第三步，点击数据保存按钮，数据将会保存进入数据库已创建的表中，		方便后续的查看。
  	第四步，点击发送数据，数据将会发送至服务器。
  	第五步，点击数据显示，数据将会分日志和物流进行显示。

## 详细设计
### 表设计
（1）gather_logrec表:存放用户日志数据匹配记录
![image](https://user-images.githubusercontent.com/60099213/141681452-c1352f3c-53d1-4233-ab40-a2de1cdafff0.png)


（2）gather_transport表：存放用户物流数据匹配记录
![image](https://user-images.githubusercontent.com/60099213/141681455-652a5f8a-acfb-404f-bb62-450b62fbb0f6.png)

（3）记录matched_logrec表：已匹配的日志数据id记录

![image](https://user-images.githubusercontent.com/60099213/141681458-f9d23ccc-903f-4f91-bed0-b93db753dcac.png)

（4）matched_transport表：已匹配的物流数据id
![image](https://user-images.githubusercontent.com/60099213/141681459-9dd1420b-0c24-4692-a94f-9658fcea9d03.png)


（5）user表：存放用户信息记录
![image](https://user-images.githubusercontent.com/60099213/141681463-198fa7b6-3cc2-4d45-aaed-f2114d12902a.png)
