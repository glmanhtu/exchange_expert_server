`mkdir -p /exchange_expert/mongo_data`
`sudo rm -rf /exchange_expert/mongo_data/*`
`tar -zcvf /exchange_expert/data/mongodb/mongodb.data.tar.gz -C /exchange_expert/mongo_data`

`sudo chown -R vagrant /var/lib/mongodb`
`sudo mongorestore --db exchange_expert --dbpath /var/lib/mongodb --username admin --password admin123 --drop /exchange_expert/mongo_data/exchange_expert`
`sudo chown -R mongodb:nogroup /var/lib/mongodb`

`mkdir -p /exchange_expert/elasticsearch_data`
`rm -rf /exchange_expert/elasticsearch_data/*`
`tar -zcvf /exchange_expert/data/elasticsearch/elasticsearch.data.tar.gz -C /exchange_expert/elasticsearch_data`