class TweetsController < ApplicationController

	before_action :find_user
	def find_user
		if (!session["user_id"].present?)
			redirect_to login_path
		end
	end

	before_action :authorize, only: [:edit, :destroy, :update]
	def authorize
		@tweet = Tweet.find_by(id: params["id"])
		@user = User.find_by(id: @tweet.user_id)
		if @user.blank? || session[:user_id] != @user.id
			redirect_to root_url, notice: "You need login!"
		end
	end
	
	def index
		if params[:keyword].present?
			
			if User.where("username LIKE ?", "%#{params[:keyword]}").present?
				userid = User.find_by(username: params[:keyword]).id
				@tweets = Tweet.where("user_id LIKE ?", "%#{userid}")
			else
				redirect_to root_url, notice: "Search ID doesn't exist!"
				return
			end
			@tweets = @tweets.order('date desc').paginate(:per_page => 5, :page => params[:page])
			return
		end
		
		if params["user_id"].present?
			@tweets = Tweet.where(user_id: params["user_id"])
		else
			@tweets = Tweet.all
		end
		@tweets = @tweets.order('date desc').paginate(:per_page => 5, :page => params[:page])
	end

	def show
		@tweet = Tweet.find_by(id: params["id"])
	end

	def create
		tweet = Tweet.new
		tweet.content = params[:post_tweet]
		tweet.date = DateTime.now.to_i
		tweet.user_id = session["user_id"]
		tweet.save
		redirect_to root_path
	end

	def destroy
		@tweet.delete
		redirect_to root_path
	end

	def edit
		@tweet = Tweet.find_by(id: params["id"])
	end

	def update
		@tweet.content = params["update_tweet"]
		@tweet.image = params["image"]
		@tweet.date = DateTime.now.to_i
		@tweet.save
		redirect_to root_path
	end

end