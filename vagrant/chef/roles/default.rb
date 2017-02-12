name "java"
description "Install Oracle Java"
default_attributes({
	"java" => {
	    "install_flavor" => "oracle",
	    "jdk_version" => "8",
	    "oracle" => {
	      "accept_oracle_download_terms" => true
	    }
  	},
  	"elasticsearch"	=>	{
  		"install"	=>	{
  			"type"	=>	"package",
  			"version"	=>	"5.2.0",
        "download_checksum" =>  "6f446164010bbfccd734484e2805e6c20b4d66d9b6125c0b157a47be22d8fe09"
  		},
      "user"  =>  {
        "username"  =>  "vagrant",
        "groupname" =>  "vagrant"
      },
  		"configure"	=>	{
  			"allocated_memory"	=>	"256m",
  			"configuration"	=>	{
  				"cluster.name"	=>	'escluster',
  				"node.name"	=>	'exchange_expert',
  				"http.port"	=>	9201,
  				"network.bind_host"	=>	'0',
  				"network.host"	=>	"0.0.0.0",
  				"transport.tcp.port"    => 9300,
  				"transport.host"    =>  "0.0.0.0",
  				"discovery.zen.ping.unicast.hosts"  =>  "0.0.0.0",
  				"http.cors.enabled" =>  true,
  				"http.cors.allow-origin"    =>  "*",
          "path.repo" =>  ["/exchange_expert/elasticsearch_data"]
  			}
  		}
  	},
  	"mongodb" => {
  		"bind_ip"   =>  "0.0.0.0"
  	}
})

run_list(
  "recipe[java]",
  "recipe[elasticsearch]",
  "recipe[mongodb]",
  "recipe[exchange-expert]"
)