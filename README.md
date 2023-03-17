
# A secondary development of live broadcast server based on srs
> springcloud project, features are still being improved...springcloud project, features are still being improved...
The project does not have any permission verification and is not applicable to the production environment. It can be developed again on this basis
>Load balancing is done in srs by configuring the edge and source servers

api document: https://www.apifox.cn/apidoc/shared-761de0a3-7537-4e71-96e8-3934dc7cc1b8    163ntztR

srs project: https://github.com/ossrs/srs
srs hooks infomation:
```
http_hooks {
	enabled         on;
	on_publish      http://srs-callback:8085/api/v1/streams;
	on_unpublish    http://srs-callback:8085/api/v1/streams;
	on_play         http://srs-callback:8085/api/v1/sessions;
	on_stop         http://srs-callback:8085/api/v1/sessions;
}
```

