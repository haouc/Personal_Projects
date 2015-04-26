//
//  FirstViewController.h
//  CarCola
//
//  Created by Hao Zhou on 3/1/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "FirstViewController.h"
#import "InitialLaunch.h"

@interface FirstViewController : UIViewController
@property NSMutableArray *carArr;
//@property NSInteger *loginCounter;

@property (weak, nonatomic) IBOutlet UIImageView *carImageOne;

@property (weak, nonatomic) IBOutlet UIImageView *carImageTwo;


@property NSDate *trackingDate;



@end

