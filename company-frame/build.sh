name=company-frame
version=latest
mvn clean compile package -Dmaven.test.skip=true && docker build -t pingxin/${name}:${version} .
#docker push pingxin/${name}:${version}

#docker run --name company-frame --network px_net --link mysql:data_mysql --link redis:data_redis -p8021:8080  -d pingxin/company-frame:latest