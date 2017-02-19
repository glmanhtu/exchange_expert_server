echo `mkdir -p /exchange_expert/mongo_data`
echo `sudo rm -rf /exchange_expert/mongo_data/*`
echo `sudo chown -R vagrant /exchange_expert/mongo_data`
echo `tar -zxvf /exchange_expert/data/mongodb/mongodb.data.tar.gz -C /exchange_expert`

echo `sudo chown -R vagrant /var/lib/mongodb`
echo `sudo mongorestore --db exchange_expert --dbpath /var/lib/mongodb --username admin --password admin123 --drop /exchange_expert/mongo_data/exchange_expert`
echo `sudo chown -R mongodb:nogroup /var/lib/mongodb`

echo `mkdir -p /exchange_expert/elasticsearch_data`
echo `sudo rm -rf /exchange_expert/elasticsearch_data/*`
echo `sudo chown -R vagrant /exchange_expert/elasticsearch_data`
echo `sudo tar -zxvf /exchange_expert/data/elasticsearch/elasticsearch.data.tar.gz -C /exchange_expert`
echo `sudo chown -R vagrant:vagrant /exchange_expert`