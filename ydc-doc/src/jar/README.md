
 ## 外部类导入   （不能从中央仓库获取的）
 ####导入命令  -Dfile：表示本地jar路径
mvn install:install-file  -Dfile=E:/jar/client-sdk.api-1.0.2.jar  -DgroupId=client-sdk  -DartifactId=api -Dversion=1.0.2  -Dpackaging=jar

mvn install:install-file  -Dfile=E:/jar/client-sdk.common-1.0.0-SNAPSHOT.jar  -DgroupId=client-sdk  -DartifactId=common -Dversion=1.0.0  -Dpackaging=jar

mvn install:install-file  -Dfile=E:/jar/client-sdk.core-1.0.0-SNAPSHOT.jar  -DgroupId=client-sdk  -DartifactId=core -Dversion=1.0.0  -Dpackaging=jar

mvn install:install-file  -Dfile=E:/jar/client-sdk.spring-1.0.0-SNAPSHOT.jar  -DgroupId=client-sdk  -DartifactId=spring  -Dversion=1.0.0  -Dpackaging=jar

mvn install:install-file  -Dfile=E:/jar/httpclient-4.3.jar  -DgroupId=org.apache.http  -DartifactId=httpclient  -Dversion=4.3  -Dpackaging=jar

mvn install:install-file  -Dfile=E:/jar/httpcore-4.3.jar  -DgroupId=org.apache.http  -DartifactId=httpcore  -Dversion=4.3  -Dpackaging=jar

mvn install:install-file  -Dfile=E:/jar/httpmime-4.3.jar  -DgroupId=org.apache.http  -DartifactId=httpmime  -Dversion=4.3  -Dpackaging=jar

mvn install:install-file  -Dfile=E:/jar/opencv-330.jar  -DgroupId=org.opencv  -DartifactId=opencv  -Dversion=330  -Dpackaging=jar

mvn install:install-file  -Dfile=/root/alipay-sdk-java-3.4.27.ALL.jar  -DgroupId=com.alipay.sdk  -DartifactId=alipay-sdk-java  -Dversion=3.4.27.ALL  -Dpackaging=jar

mvn install:install-file  -Dfile=/root/wxpay-sdk-3.0.9.jar  -DgroupId=com.github.wxpay -DartifactId=wxpay-sdk  -Dversion=3.0.9  -Dpackaging=jar