//
//  DetailViewController.h
//  Google_News
//
//  Created by Hao Zhou on 2/15/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <Social/Social.h>
#import "BookmarkToWebViewController.h"

@interface DetailViewController : UIViewController <BookmarkToWebViewDelegate, UIActionSheetDelegate>
@property (strong, nonatomic) id detailItem;
@property (weak, nonatomic) IBOutlet UILabel *detailDescriptionLabel;
@property (weak, nonatomic) IBOutlet UIWebView *webView;
@property (weak, nonatomic) NSDictionary *linkItem;
@property NSURL *currentURL;
//- (IBAction)bookmarkButton:(id)sender;
- (IBAction)favoriteButton:(id)sender;
- (IBAction)postToTweet:(id)sender;

@property (weak, nonatomic) IBOutlet UIWebView *loadingIndicator;

@end

