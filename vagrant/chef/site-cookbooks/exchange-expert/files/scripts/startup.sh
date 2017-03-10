echo `cd /exchange_expert/repository/scripts && sudo sh deploy.sh`
echo `cd /exchange_expert/client/home && sudo npm cache clean -f && sudo npm install -g n && sudo n stable`
echo `sudo apt-get install g++ -y`
echo `cd /exchange_expert/client && git checkout origin/development && git pull origin development`
echo `cd /exchange_expert/client/home && npm install && gulp build`
echo `rm -rf /exchange_expert/repository/vagrant/release/client/home/*`
echo `sudo chown -R ubuntu:ubuntu /exchange_expert/repository/vagrant/release/client/home`
echo `cp -R /exchange_expert/client/home/release/* /exchange_expert/repository/vagrant/release/client/home/`