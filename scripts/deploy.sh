echo `chmod +x service.sh`
echo `cd /exchange_expert/repository && git checkout origin develop && git pull origin develop && mvn clean install -DskipTests`
echo `./service.sh stop -e dev -jar /exchange_expert/release/exchange-0.0.1-SNAPSHOT.jar`
echo `rm -rf /exchange_expert/release/exchange-0.0.1-SNAPSHOT.jar`
echo `cp /exchange_expert/repository/target/exchange-0.0.1-SNAPSHOT.jar /exchange_expert/release/`
echo `./service.sh start -e dev -jar /exchange_expert/release/exchange-0.0.1-SNAPSHOT.jar`