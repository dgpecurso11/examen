FROM kebblar/jdk18-utf8-debug-maven 
COPY ./target/sample-1.0-SNAPSHOT-fat.jar /javabin/sample-1.0-SNAPSHOT-fat.jar 
CMD java -jar /javabin/sample-1.0-SNAPSHOT-fat.jar
