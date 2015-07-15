class Comment < ActiveRecord::Base

	belongs_to :tweet

	belongs_to :user

	validates_presence_of :content

end
