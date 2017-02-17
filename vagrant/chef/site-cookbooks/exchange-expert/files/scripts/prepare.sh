sudo chown -R vagrant /var/lib/mongodb
sudo mongorestore --db exchange_expert --dbpath /var/lib/mongodb --username admin --password admin123 --drop /exchange_expert/mongo_data/exchange_expert
sudo chown -R mongodb:nogroup /var/lib/mongodb