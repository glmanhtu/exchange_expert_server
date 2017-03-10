`mkdir -p /exchange_expert/elasticsearch_data`
`rm -rf /exchange_expert/elasticsearch_data/*`
`rm -rf /exchange_expert/repository/vagrant/data/elasticsearch/*`
`curl -XPUT 'http://localhost:9200/_snapshot/exchange_expert/default?wait_for_completion=true'`
`tar -zcvf elasticsearch.data.tar.gz /exchange_expert/elasticsearch_data`
`mv elasticsearch.data.tar.gz /exchange_expert/repository/vagrant/data/elasticsearch/`