
#一个基于srs直播服务器的二次开发
springboot,springcloud
功能还在完善中...

api文档：
链接: https://www.apifox.cn/apidoc/shared-761de0a3-7537-4e71-96e8-3934dc7cc1b8  访问密码 : 163ntztR

srs项目地址
https://github.com/ossrs/srs

srs回调地址:
```
http_hooks {
enabled         on;
on_publish      http://srs-callback:8085/api/v1/streams;
on_unpublish    http://srs-callback:8085/api/v1/streams;
on_play         http://srs-callback:8085/api/v1/sessions;
on_stop         http://srs-callback:8085/api/v1/sessions;
}
```

