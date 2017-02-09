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
  			"version"	=>	"5.1.2",
        "download_checksum" =>  "411091695ff9188b9394816f88f3328f478c4d21e2a80ce194660ee14b868475"
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