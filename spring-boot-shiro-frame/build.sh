name=spring-boot-shiro-frame
version=latest
mvn clean compile package -Dmaven.test.skip=true && docker build -t pingxin/${name}:${version} .

#docker push pingxin/${name}:${version}
#运行
#docker run --name spring-boot-shiro-frame --network px_net --link mysql:data_mysql --link redis:data_redis -p8022:8080  -d pingxin/spring-boot-shiro-frame:latest