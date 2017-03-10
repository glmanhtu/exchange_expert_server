echo `chmod +x service.sh`
echo `cd /exchange_expert/repository/scripts && ./service.sh stop -e dev -jar /exchange_expert/repository/target/exchange-0.0.1-SNAPSHOT.jar`
echo `cd /exchange_expert/repository && mvn clean install -DskipTests`
echo `cd /exchange_expert/repository/scripts && ./service.sh start -e dev -jar /exchange_expert/repository/target/exchange-0.0.1-SNAPSHOT.jar`