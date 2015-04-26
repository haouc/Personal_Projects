//
//  DetailViewController.m
//  Google_News
//
//  Created by Hao Zhou on 2/15/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import "DetailViewController.h"


@interface DetailViewController ()
@property (strong, nonatomic) UIPopoverController *masterPopoverController;
//- (void) configureView;
@end

@implementation DetailViewController


- (void)viewDidLoad {
    [super viewDidLoad];

    NSURL *url = [NSURL URLWithString:@"https://news.google.com/nwshp?hl=en&tab=wn&ei=-EHhVPTSLcG0ggSCiYLQDg&ved=0CA0QqS4oBQ"];

    
    [self.webView loadRequest:[NSURLRequest requestWithURL:url]];

    // Do any additional setup after loading the view, typically from a nib.
    [self configureView];
    
}

#pragma mark - Managing the detail item

- (void)setLinkItem:(NSDictionary *)newLinkItem {
    if (_linkItem != newLinkItem) {
        _linkItem = newLinkItem;
            
        // Update the view.
        [self configureView];
        
        NSLog(@"setter override");
        
        if (self.masterPopoverController != nil) {
            [self.masterPopoverController dismissPopoverAnimated:YES];
        }
    }
}

- (void)configureView {
     //Update the user interface for the detail item.
    if (self.linkItem) {
        self.detailDescriptionLabel.text = self.linkItem[@"title"];
        NSURL *url = [NSURL URLWithString:self.linkItem[@"link"]];
        self.currentURL = url;
        
        [self.webView loadRequest:[NSURLRequest requestWithURL:url]];

    }

}

- (void)webView:(UIWebView *)webView didFailLoadWithError:(NSError *)error
{
    //Alert only on network error
    if (error.code == -1009) {
        UIAlertView *alert = [[UIAlertView alloc]
                              initWithTitle: @"News"
                              message: @"Network error: please check your internet connection. Make sure you are not on airplane mode."
                              delegate: nil
                              cancelButtonTitle:@"Ok"
                              otherButtonTitles:nil];
        [alert show];
    }
}



- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void) prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
if ([[segue identifier] isEqualToString:@"popoverSegue"]) {
    
    NSLog(@"Prepare for segue");
    UINavigationController *nc = (UINavigationController*)segue.destinationViewController;
    BookmarkToWebViewController *bvc = (BookmarkToWebViewController *)nc.topViewController;
    bvc.delegate = self;
    }
}


- (IBAction)favoriteButton:(id)sender {
    if (!self.linkItem){
        return;
    }
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    NSArray *dataArr = (NSArray *)[defaults objectForKey:@"favorites"];
    NSMutableArray *newArr = nil;
    
    if (!dataArr){
        newArr = [[NSMutableArray alloc] init];
        
    }else {
        newArr = [[NSMutableArray alloc] initWithArray:dataArr];
    }
    
    if (![newArr containsObject:self.linkItem]){
        [newArr addObject:self.linkItem];
    }
    [defaults setObject:newArr forKey:@"favorites"];
    [defaults synchronize];
    
    NSLog(@"adding in favorite");
}


- (IBAction)postToTweet:(id)sender {

    UIActionSheet *share = [[UIActionSheet alloc] initWithTitle:@"Pass on the news!" delegate:self cancelButtonTitle:@"OK" destructiveButtonTitle:nil otherButtonTitles:@"Post to Twitter", nil];
    
    [share showInView:self.view];
}



- (void)actionSheet:(UIActionSheet *)actionSheet clickedButtonAtIndex:(NSInteger)buttonIndex {

        if([SLComposeViewController isAvailableForServiceType: SLServiceTypeTwitter]) {
            SLComposeViewController *tweetSheet =[SLComposeViewController composeViewControllerForServiceType:SLServiceTypeTwitter];
            [tweetSheet addURL:self.webView.request.URL];
            [tweetSheet setInitialText:@"Check out this article: "];
            [self presentViewController:tweetSheet animated:YES completion:nil];
        } else {
            UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"Sorry" message:@"You can't send a tweet right now, make sure you have at least one Twitter account setup and your device is using iOS6 or above!." delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil];
            [alertView show];
        }

}


- (void)bookmark:(id)sender sendsURL:(NSURL *)url{
     [self.webView loadRequest:[NSURLRequest requestWithURL:url]];
    
}

@end
