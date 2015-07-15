# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ name: 'Chicago' }, { name: 'Copenhagen' }])
#   Mayor.create(name: 'Emanuel', city: cities.first)


User.delete_all

User.create  id: 1,
             username: 'yanzhewu',
             password: 'yanzhewu',
             email: 'yanzhewu@uchicago.edu',
             image: 'http://tp3.sinaimg.cn/1442054902/50/40002770510/1'

User.create  id: 2,
             username: 'haozhou',
             password: 'haozhou',
             email: 'haozhou@uchicago.edu',
             image: 'http://tp4.sinaimg.cn/1438513755/180/40008758792/1'
             
User.create  id: 3,
             username: 'lingduokong',
             password: 'lingduokong',
             email: 'lingduokong@uchicago.edu',
             image: 'http://tp2.sinaimg.cn/1666978981/50/5723425429/0'


Tweet.delete_all

Tweet.create  id: 1,
              content: 'Lous challenge anyone???????',
              date: 1425069955,
              user_id: "1",
              image: "http://ww1.sinaimg.cn/bmiddle/6973530djw1esi5icwcmuj20ji0yojua.jpg"

Tweet.create  id: 2,
              content: 'An entire team of Apple developers was there 24/7 to help, so that was a wonderful resource to learn Swift and Objective C in a weekend.',
              date: 1425069957,
              user_id: "2",
              image: "http://ww4.sinaimg.cn/square/87246b09jw1esguiqhsjpj20zk0qodot.jpg" 


Tweet.create  id: 3,
              content: 'CSIL is once again selling discounted used computers and monitors for university students and staff! Models for sale are MacPro(2010), Shuttle, and 24" Apple LED Cinema Display. First come, first serve.',
              date: 1425069955,
              user_id: "3" ,
              image: "http://ww1.sinaimg.cn/thumbnail/6ac4d14dgw1esi2hn6vmdj20dw0dwdhr.jpg" 

Tweet.create  id: 4,
              content: 'Went to an alum potluck, being the only student showing up with four alums around, which is pretty cool and actually the best alum event so far!',
              date: 1425069955,
              user_id: "1",
              image: "http://ww3.sinaimg.cn/bmiddle/4a23d069jw1esi60jgix6j20v815owoq.jpg"  

Tweet.create  id: 5,
              content: 'Wish I could graduate again ‪#‎BadgerMemory‬',
              date: 1425963490,
              user_id: "2",
              image: "http://ww4.sinaimg.cn/square/005wlOCojw1esi2tymbxjj30zk0k042w.jpg"   

Tweet.create  id: 6,
              content: 'Lous challenge anyone???????',
              date: 1425069955,
              user_id: "1",
              image: "http://ww1.sinaimg.cn/bmiddle/6973530djw1esi5icwcmuj20ji0yojua.jpg"

Tweet.create  id: 7,
              content: 'An entire team of Apple developers was there 24/7 to help, so that was a wonderful resource to learn Swift and Objective C in a weekend.',
              date: 1425069957,
              user_id: "2",
              image: "http://ww4.sinaimg.cn/square/87246b09jw1esguiqhsjpj20zk0qodot.jpg" 


Tweet.create  id: 8,
              content: 'CSIL is once again selling discounted used computers and monitors for university students and staff! Models for sale are MacPro(2010), Shuttle, and 24" Apple LED Cinema Display. First come, first serve.',
              date: 1425069955,
              user_id: "3" ,
              image: "http://ww1.sinaimg.cn/thumbnail/6ac4d14dgw1esi2hn6vmdj20dw0dwdhr.jpg" 

Tweet.create  id: 9,
              content: 'Went to an alum potluck, being the only student showing up with four alums around, which is pretty cool and actually the best alum event so far!',
              date: 1425069955,
              user_id: "1",
              image: "http://ww3.sinaimg.cn/bmiddle/4a23d069jw1esi60jgix6j20v815owoq.jpg"  

Tweet.create  id: 10,
              content: 'Wish I could graduate again ‪#‎BadgerMemory‬',
              date: 1425963490,
              user_id: "2",
              image: "http://ww4.sinaimg.cn/square/005wlOCojw1esi2tymbxjj30zk0k042w.jpg"                          


Comment.delete_all

Comment.create  id: 1,
                content: 'You cant like your own post...',
                date: 1431983183,
                tweet_id: 1,
                user_id: 1

Comment.create  id: 2,
                content: 'I must have been a little drunk',
                date: 1431983197,
                tweet_id: 1,
                user_id: 2


Comment.create  id: 3,
                content: 'That was at hydrate',
                date: 1431983352,
                tweet_id: 1,
                user_id: 3


Comment.create  id: 4,
                content: 'I heard you bumped into Alex in Chicago lol, the world is too small!',
                date: 1431983363,
                tweet_id: 1,
                user_id: 3

Comment.create  id: 5,
                content: 'You cant like your own post...',
                date: 1431983183,
                tweet_id: 2,
                user_id: 1

Comment.create  id: 6,
                content: 'I must have been a little drunk',
                date: 1431983197,
                tweet_id: 2,
                user_id: 2


Comment.create  id: 7,
                content: 'That was at hydrate',
                date: 1431983352,
                tweet_id: 3,
                user_id: 3


Comment.create  id: 8,
                content: 'I heard you bumped into Alex in Chicago lol, the world is too small!',
                date: 1431983363,
                tweet_id: 3,
                user_id: 3


Follow.delete_all

Follow.create   fan_id: 1,
                star_id: 2,
                id: 1  

Follow.create   fan_id: 1,
                star_id: 3,
                id: 2   

Follow.create   fan_id: 2,
                star_id: 1,
                id: 3    

Follow.create   fan_id: 2,
                star_id: 3,
                id: 4   

Follow.create   fan_id: 3,
                star_id: 2,
                id: 5    

Follow.create   fan_id: 3,
                star_id: 1,
                id: 6    

                                                                                                                
Message.delete_all

Message.create  id:1,
                content: "Hahahaha. That's funny. Yeah. I might have to buy a new one tmr",
                date: 1432931316,
                sender_id: 3,
                receiver_id: 2,
                is_read: false

Message.create  id:2,
                content: "Oh hey. All of my friends are friends with you now.",
                date: 1432931383,
                sender_id: 2,
                receiver_id: 3,
                is_read: false

Message.create  id:3,
                content: "Haha. Yay!",
                date: 1432931454,
                sender_id: 3,
                receiver_id: 2,
                is_read: false     

Message.create  id:4,
                content: "My old one got wet and fried a few months ago",
                date: 1432931529,
                sender_id: 2,
                receiver_id: 3,
                is_read: false                             


# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ name: 'Chicago' }, { name: 'Copenhagen' }])
#   Mayor.create(name: 'Emanuel', city: cities.first)
