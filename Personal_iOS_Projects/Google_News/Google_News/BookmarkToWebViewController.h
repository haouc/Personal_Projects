//
//  BookmarkToWebViewController.h
//  Google_News
//
//  Created by Hao Zhou on 2/15/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BookMarkTableViewCell.h"

// custom protocol
@protocol BookmarkToWebViewDelegate <NSObject>

- (void)bookmark:(id)sender sendsURL:(NSURL *)url;

@end



@interface BookmarkToWebViewController : UITableViewController

@property (weak, nonatomic) id<BookmarkToWebViewDelegate> delegate;
@property NSMutableArray *myFavorite;
@property NSArray *myKeys;
//@property NSString *linkUrl;

- (IBAction)editButton:(id)sender;
- (IBAction)tapButton:(id)sender;
//- (IBAction)tapLoadURL:(id)sender;



@end
