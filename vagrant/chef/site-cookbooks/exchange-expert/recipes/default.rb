#
# Cookbook Name:: exchange-expert
# Recipe:: default
#
# Copyright 2017, YOUR_COMPANY_NAME
#
# All rights reserved - Do Not Redistribute
#

service "elasticsearch" do
	action :stop
end

service "mongodb" do
	action :stop
end

cookbook_file "/tmp/startup.sh" do
  source "scripts/startup.sh"
  mode 0755
end

directory '/exchange_expert/scripts' do
  owner 'vagrant'
  group 'vagrant'
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

execute "start script on boot" do
  command "sh /tmp/startup.sh"
end

directory '/exchange_expert/elasticsearch_data' do
	owner 'elasticsearch'
	group 'elasticsearch'
	mode '0755'
  	action :create
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
	  	url 'http://localhost:9201/_snapshot/exchange_expert'
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
http_request 'Restore snapshoot ES' do
  	action :post
  	url 'http://localhost:9201/_snapshot/exchange_expert/default/_restore'  
  	message (
	  		{}.to_json
		)  	
end
