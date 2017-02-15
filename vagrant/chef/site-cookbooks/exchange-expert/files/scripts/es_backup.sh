rm -rf /exchange_expert/elasticsearch_data/*
curl -XPOST 'http://localhost:9200/good/_close'
curl -XPUT 'http://localhost:9200/_snapshot/exchange_expert/default?wait_for_completion=true'
curl -XPOST 'http://localhost:9200/good/_open'