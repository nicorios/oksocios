#kill previous process
kill -9 $(ps -aux | grep 'java -jar' | grep 'oksocios-api' | awk '{print $2}')

#Wait a little
sleep 2

#Start oksocios
(nohup java -jar "/home/ubuntu/src-oksocios/target/oksocios-api-1.0.0.jar")