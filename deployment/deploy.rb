require 'sinatra'
require 'json'

set :bind, '0.0.0.0'

get '/message' do
  'Put this in your pipe & smoke it!'
end

post '/event_handler' do
  @payload = JSON.parse(params[:payload])

  case request.env['HTTP_X_GITHUB_EVENT']
  when "pull_request"
    if @payload["action"] == "closed" && @payload["pull_request"]["merged"]
      system("cd /exchange_expert/repository/scripts && sh deploy.sh")
    end
  end
end