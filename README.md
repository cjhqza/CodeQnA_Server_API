# CodeQnA_Server_API
基于Vue3和SpringCloud的技术问答系统 ---- 后端接口

本项目包括前台用户系统以及后台管理系统的后端API接口的编写代码。

其中，后台管理系统的后端服务名为CodeQnA_manager，运行ManagerApplication启动类即可。而前台用户系统的后端服务名为CodeQnA_service，运行服务中的每个子服务的启动类即可。

注意：本项目添加多个Spring Cloud的依赖包括Nacos，配置文件是在Nacos服务平台上进行注册，如果是您本人的本机上运行，需要注意Naocs服务平台的连接地址，以及其他基础设施服务如MySQL的连接的ip地址、登录账号密码等符合您本机上的设置。

项目GitHub下载地址：https://github.com/cjhqza/CodeQnA_Server_API
