rm -rf /exchange_expert/elasticsearch_data/*
curl -XPOST 'http://localhost:9201/exchange_expert/_close'
curl -XPUT 'http://localhost:9201/_snapshot/exchange_expert/default?wait_for_completion=true'
curl -XPOST 'http://localhost:9201/exchange_expert/_open'