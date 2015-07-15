class MessagesController < ApplicationController

	before_action :find_user
	def find_user
		if (!session["user_id"].present?)
			redirect_to login_path
		end
	end


	before_action :find_message, only: [:edit, :destroy, :update]
	def find_message
		if !Message.find_by_id(params["id"]).present?
			redirect_to user_messages_path, notice:"The message is not existing!"
		end
	end


	before_action :authorize, only: [:edit, :destroy, :update]
	def authorize
		@message = Message.find_by(id: params["id"])
		@user = User.find_by_id(session["user_id"])
		if @user.blank? || session[:user_id] != @user.id
			redirect_to root_url, notice: "You need login!"
		end
	end

	def index
		@user = User.find_by_id(session["user_id"])
		if params["user_id"].present?
			@messages = Message.where(sender_id: params["user_id"])
			@messages_in = Message.where(receiver_id: session["user_id"])
		end
		@messages = @messages.order('date desc').paginate(:per_page => 5, :page => params[:page])
		@messages_in = @messages_in.order('date desc').paginate(:per_page => 5, :page => params[:page])
	end

	def new
		
	end

	def create
		message = Message.new
		if User.find_by_username(params[:receiver]).present?
			message.receiver_id = User.find_by_username(params[:receiver]).id
			message.content = params[:message]
			message.date = DateTime.now.to_i
			message.sender_id = session["user_id"]
			message.is_read = false
			message.save
			redirect_to user_messages_path
		else
			redirect_to user_messages_path, notice: "No such user existing!"
		end
		
	end

	def update
		# @message.content = params["update_tweet"]
		@message.date = DateTime.now.to_i
		@message.is_read = true
		@message.save
		redirect_to user_messages_path
	end

	def destroy
		@message.delete
		redirect_to user_messages_path
	end

end