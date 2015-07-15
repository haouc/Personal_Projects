class Tweet < ActiveRecord::Base

	validates_presence_of :content

	has_many :comments, class_name: 'Comment', foreign_key: 'tweet_id'

	has_many :like

	has_many :liker, class_name: 'User', :through => :like

end
