echo `cd /exchange_expert/repository/scripts && sudo sh deploy.sh`
echo `cd /exchange_expert/client/home && sudo npm cache clean -f && sudo npm install -g n && sudo n stable`
echo `sudo apt-get install g++ -y`
echo `cd /exchange_expert/client/home && npm install && bower install --allow-root && gulp build`
echo `cd /exchange_expert/client/admin && npm install && bower install --allow-root && gulp build`
echo `sudo chown -R ubuntu:ubuntu /exchange_expert/client/home/release`