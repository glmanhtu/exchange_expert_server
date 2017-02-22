echo `chmod +x service.sh`
echo `./service.sh stop -e dev -jar /exchange_expert/release/exchange-0.0.1-SNAPSHOT.jar`
echo `rm -rf /exchange_expert/release/exchange-0.0.1-SNAPSHOT.jar`
echo `cp /home/ubuntu/exchange-0.0.1-SNAPSHOT.jar /exchange_expert/release/`
echo `./service.sh start -e dev -jar /exchange_expert/release/exchange-0.0.1-SNAPSHOT.jar`