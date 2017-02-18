`mkdir -p /exchange_expert/mongo_data`
`sudo rm -rf /exchange_expert/mongo_data/*`
`sudo rm -rf /exchange_expert/data/mongodb/*`
`sudo service mongodb stop`
`sudo mongodump --db exchange_expert --dbpath /var/lib/mongodb --out /exchange_expert/mongo_data`
`sudo service mongodb start`
`tar -zcvf mongodb.data.tar.gz /exchange_expert/mongo_data`
`mv mongodb.data.tar.gz /exchange_expert/data/mongodb/