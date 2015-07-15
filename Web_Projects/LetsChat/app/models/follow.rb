class Follow < ActiveRecord::Base

	belongs_to :fans, class_name: "User",foreign_key: "fan_id"
  belongs_to :stars, class_name: "User", foreign_key: "star_id"
	
end
