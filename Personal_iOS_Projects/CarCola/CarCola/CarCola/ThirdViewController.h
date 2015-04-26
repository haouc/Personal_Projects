//
//  ThirdViewController.h
//  CarCola
//
//  Created by Hao Zhou on 3/3/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "ActivityIndicatorView.h"

@protocol LoadDataFinishDelegate <NSObject>

@end



@interface ThirdViewController : UIViewController <UIWebViewDelegate, LoadDataFinishDelegate>


@property (weak, nonatomic) IBOutlet UIWebView *carSearchWebView;
//@property (weak, nonatomic) IBOutlet UIActivityIndicatorView *activityView;
@property UIActivityIndicatorView *loadingView;

//@property (weak, nonatomic) IBOutlet ActivityIndicatorView *activityView;

@property (strong, nonatomic) UIRefreshControl *refreshControl;

//@property (strong)ActivityIndicatorView *activityIndicator;

@property (weak, nonatomic) id<LoadDataFinishDelegate> delegate;
@property (weak, nonatomic) IBOutlet ActivityIndicatorView *indicatorView;


@end
