sudo rm -rf /exchange_expert/mongo_data/*
sudo mongodump --db exchange_expert --dbpath /var/lib/mongodb --username admin --password admin123 --out /exchange_expert/mongo_data