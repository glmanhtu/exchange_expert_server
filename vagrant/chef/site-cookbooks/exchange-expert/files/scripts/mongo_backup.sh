sudo rm -rf /exchange_expert/mongo_data/*
sudo service mongodb stop
sudo mongodump --db exchange_expert --dbpath /var/lib/mongodb --out /exchange_expert/mongo_data
sudo service mongodb start