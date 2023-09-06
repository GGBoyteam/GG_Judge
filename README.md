# 开发环境部署

开发环境部署详见：[本地开发环境部署](https://github.com/mc23101/GG_Judge_Wiki/wiki/%E6%9C%AC%E5%9C%B0%E5%BC%80%E5%8F%91%E7%8E%AF%E5%A2%83%E9%83%A8%E7%BD%B2)

```sh
# 从github上下载本地环境部署包
curl -J -L -o /home/deploylocal.tar https://github.com/GGBoyteam/GG_Judge/releases/download/localenvironment/deploylocal.tar
# 从gitee上下载本地环境部署包
curl -J -L -o /home/deploylocal.tar https://gitee.com/zhangsisiyao/ggjudgerelease/releases/download/localenv/deploylocal.tar

# 解压环境包
tar -xf /home/deploylocal.tar

# 运行脚本
sh /home/deploylocal/start.sh
```





# 项目根目录介绍

| 目录            | 说明                                                   |
| --------------- | ------------------------------------------------------ |
| .run            | 项目idea运行环境，请不要随意更改                       |
| GG_auth         | 项目登录模块                                           |
| GG_common       | 项目公共模块，装载所有微服务的公共部分，实体请写在这里 |
| GG_gateway      | 项目网关模块                                           |
| GG_judgeServer  | 项目判题模块                                           |
| GG_system       | 项目系统模块                                           |
| deploy          | 项目线上环境搭建脚本                                   |
| deploylocal     | 项目本地开发环境搭建脚本                               |
| sql             | mysql初始化sql文件                                     |
| vue-onlinejudge | 项目前端页面                                           |
| .gitignore      | git忽略文件配置文件                                    |
| pom.xml         | 项目maven管理的pom文件                                 |

# 团队协作开发工具

postman团队:[点击加入](https://app.getpostman.com/join-team?invite_code=3f7acb69e9c71961909ec0fecc71fd69)







