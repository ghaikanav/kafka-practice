To run kafka using zookeeper, use docker-compose.yml
https://github.com/ghaikanav/my-demo-app/blob/main/docker-compose.yml

To alter the topic and add partitions: 

``` 
docker exec -it <Container_Id> /bin/bash
/usr/bin/kafka-topics --alter --bootstrap-server kafka:29092 --partitions 3 --topic demo_java
```
