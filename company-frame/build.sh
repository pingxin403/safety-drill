name=company-frame
version=latest
mvn clean compile package -Dmaven.test.skip=true && docker build -t pingxin/${name}:${version} .
#docker push pingxin/${name}:${version}