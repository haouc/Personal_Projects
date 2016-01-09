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
		# if user does not assign a url of image for themself, app assign a question maker icon to them.
		if !params[:image].present?
			params[:image] = "icon_question.png"
		end
		@user = User.new(username: params[:username].downcase, email: params[:email].downcase, password: params[:password], image: params[:image])
		if @user.password =~ /[a-z]/ && @user.password =~ /[A-Z]/ && @user.password =~ /[0-9]/ && @user.password.length > 8
			validation = true
		else
			validation = false
		end

		if validation && @user.save
			redirect_to root_url, notice: "Thanks for signing up. Welcome to our site, Please sign in here!"
		else
			redirect_to signup_path, notice: "Sign up failed! Please try again with strong password or unused username."
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
			redirect_to edit_user_path(params[:id]), notice: "Password doesn't match the existing password"
		end
	end
end