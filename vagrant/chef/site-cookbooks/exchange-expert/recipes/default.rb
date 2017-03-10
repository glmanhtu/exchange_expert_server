#
# Cookbook Name:: exchange-expert
# Recipe:: default
#
# Copyright 2017, YOUR_COMPANY_NAME
#
# All rights reserved - Do Not Redistribute
#

include_recipe 'elasticsearch'
include_recipe 'apt::default'
include_recipe 'nginx::default'
include_recipe "nodejs::npm"

git "/exchange_expert/repository" do
  repository "git@github.com:yehnkay/exchange_expert_server.git"
  reference "develop"
  action :sync
  user "ubuntu"
end

git "/exchange_expert/client" do
  repository "git@github.com:yehnkay/exchange_expert_client.git"
  revision "development"
  checkout_branch 'development'
  enable_checkout false
  action :sync
end

package 'nginx' do
  action :install
end

elasticsearch_install 'elasticsearch' do
  type :package
  version "2.4.0"
  download_checksum "5edf1ac795d6950781b20ecc8db992d6dc95e55abb1ff66e6b1234d85bd68514"
  action :install
end

elasticsearch_configure 'elasticsearch' do
    allocated_memory '512m'
    configuration ({
        'cluster.name' => 'escluster',
        'node.name' => 'exchange_expert',
        'http.port' => 9200,
        'network.bind_host' =>  '0',
        'network.host'  =>  '0.0.0.0',
        'http.cors.enabled' =>  true,
        "path.repo" =>  ["/exchange_expert/elasticsearch_data"]
        })
end

elasticsearch_user 'elasticsearch' do
  username 'ubuntu'
  groupname 'ubuntu'

  action :create
end

service "elasticsearch" do
	action :stop
end

service "mongodb" do
	action :stop
end

cookbook_file "/tmp/prepare.sh" do
  source "scripts/prepare.sh"
  mode 0755
end

cookbook_file "/tmp/startup.sh" do
  source "scripts/startup.sh"
  mode 0755
end

directory '/exchange_expert/scripts' do
  owner 'ubuntu'
  group 'ubuntu'
  mode '0755'
  action :create
end

cookbook_file "/exchange_expert/scripts/es_backup.sh" do
	source "scripts/es_backup.sh"
	mode 0755
end

cookbook_file "/exchange_expert/scripts/mongo_backup.sh" do
	source "scripts/mongo_backup.sh"
	mode 0755
end

cookbook_file "/exchange_expert/scripts/startup.sh" do
	source "scripts/startup.sh"
	mode 0755
end


execute "prepare script on boot" do
  command "sh /tmp/prepare.sh"
end

service "elasticsearch" do
	action :restart
end

service "mongodb" do
	action :restart
end

# restore backup of ES
begin
	http_request 'Setup snapshoot ES' do
	  	action :post
	  	url 'http://localhost:9200/_snapshot/exchange_expert'
	  	message (
	  		{
	  			:type => 'fs',
	  			:settings => {
	  				:location	=>	"/exchange_expert/elasticsearch_data",
	  				:compress	=>	true
	  			}
			}.to_json
		)
	end
	rescue  Net::HTTPServerException => e
   		puts "Ignore exception: #{e}"
end

http_request 'Stop Good' do
  	action :post
  	url 'http://localhost:9200/good/_close'
  	message (
	  		{}.to_json
		)
    ignore_failure true
end


http_request 'Restore snapshoot ES' do
  	action :post
  	url 'http://localhost:9200/_snapshot/exchange_expert/default/_restore'
  	message (
	  		{}.to_json
		)
    ignore_failure true
end

http_request 'Start Good' do
  	action :post
  	url 'http://localhost:9200/good/_open'
  	message (
	  		{}.to_json
		)
    ignore_failure true
end

cookbook_file "#{node['nginx']['dir']}/sites-available/exchange-expert.cf.conf" do
  source "exchange-expert.cf.conf"
  owner "root"
  group "root"
  mode  "0644"
end

# enable your sites configuration using a definition from the nginx cookbook
nginx_site "exchange-expert.cf.conf" do
  enable true
end

nodejs_npm "bower"

nodejs_npm "gulp"

execute "start script on boot" do
  command "sh /tmp/startup.sh"
end

service 'nginx' do
  action :restart
end