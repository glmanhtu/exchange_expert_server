name "default"
description "Install Oracle Java"
default_attributes({
	"java" => {
	    "install_flavor" => "oracle",
	    "jdk_version" => "8",
	    "oracle" => {
	      "accept_oracle_download_terms" => true
	    }
  	},
  	"mongodb" => {
  		"bind_ip"   =>  "0.0.0.0"
  	}
})

run_list(
  "recipe[java]",
  "recipe[mongodb]",
  "recipe[exchange-expert]"
)