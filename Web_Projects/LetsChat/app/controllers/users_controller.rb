class UsersController < ApplicationController

	before_action :authorize, only: [:show , :update]

	def authorize
		@user = User.find_by(id: params[:id])
		if @user.blank? || session[:user_id] != @user.id
			redirect_to root_url, notice: "Nice try!"
		end
	end

	def new
		@user = User.new
	end

	def create
		# if user does not assign a url of image for themself, app assign a question icon to them.
		if !params[:image].present?
			params[:image] = "icon_question.png"
		end
		@user = User.new(username: params[:username], email: params[:email], password: params[:password], image: params[:image])
		if @user.save
			redirect_to root_url, notice: "Thanks for signing up. Welcome to our site, Please sign in here!"
		else
			render "new"
		end
	end

	def show
		
	end

	def index
    if params["keyword"].present?
      @users = User.where("username LIKE ?", "%#{params[:keyword]}")
    else
      @users = User.all
    end

    @users = @users.order('username asc').limit(100)
  end

	def edit
		@user = User.find_by(id: params[:id])
	end

	def update
		@user = User.find_by(id: params[:id])
		@user.username = params[:username]
		@user.email = params[:email]
		@user.image = params[:image]

		if params[:password] == params[:repassword]
			@user.password = params[:password]
			if @user.save
				redirect_to user_path(params[:id])
			else
				render "edit"
			end
		else
			redirect_to edit_user_path(params[:id]), notice: "Password doesn't match the repassword"
		end
	end
end