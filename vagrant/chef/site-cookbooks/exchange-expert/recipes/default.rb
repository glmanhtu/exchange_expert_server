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

file "/tmp/git_wrapper.sh" do
  owner "ubuntu"
  mode "0755"
  content "#!/bin/sh\nexec /usr/bin/ssh -o \"StrictHostKeyChecking=no\" -i /home/ubuntu/.ssh/id_rsa \"$@\""
end

git "/exchange_expert/repository" do
  repository "git@github.com:glmanhtu/exchange_expert_server.git"
  branch "develop"
  action :sync
  user "ubuntu"
  ssh_wrapper "/tmp/git_wrapper.sh"
end

git "/exchange_expert/client" do
  repository "git@github.com:glmanhtu/exchange_expert_client.git"
  branch 'development'
  action :sync
  user "ubuntu"
  ssh_wrapper "/tmp/git_wrapper.sh"
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

cookbook_file "/tmp/startup.sh" do
  source "scripts/startup.sh"
  mode 0755
end

service "elasticsearch" do
	action :restart
end

service "mongodb" do
	action :restart
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