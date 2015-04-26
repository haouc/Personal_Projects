//
//  ThirdViewController.m
//  CarCola
//
//  Created by Hao Zhou on 3/3/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import "ThirdViewController.h"

@interface ThirdViewController ()

@end

@implementation ThirdViewController

- (void)viewDidLoad {
    self.carSearchWebView.delegate = self;
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    NSString *urlStr = @"http://www.edmunds.com/";
    NSURL *url = [NSURL URLWithString:urlStr];
    [self.carSearchWebView loadRequest:[NSURLRequest requestWithURL:url]];
    _carSearchWebView.scalesPageToFit = YES;
    

    self.indicatorView.hidden = YES;
    
}


- (void)webViewDidStartLoad:(UIWebView *)WebView {
    self.indicatorView.hidden = NO;
}

- (void)webViewDidFinishLoad:(UIWebView *)WebView {
    self.indicatorView.hidden = YES;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
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


- (UIModalPresentationStyle)adaptivePresentationStyleForPresentationController:(UIPresentationController *)controller {
    return UIModalPresentationNone;
}

@end
