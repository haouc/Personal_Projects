Rails.application.routes.draw do

  root 'tweets#index'

  resources :tweets do 
  	resources :comments
  end
  
  resources :users do
  	resources :follows
    resources :messages
  end

  get "/signup" => 'users#new'
  get "/login" => 'sessions#new'
  post '/sessions' => 'sessions#create'
  get '/logout' => 'sessions#destroy'

  

end
