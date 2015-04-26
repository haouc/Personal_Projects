//
//  UserRateMeViewController.h
//  CarCola
//
//  Created by Hao Zhou on 3/12/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface UserRateMeViewController : UIViewController
- (IBAction)CancelButton:(id)sender;
- (IBAction)RateButton:(id)sender;

@property (weak, nonatomic) IBOutlet UIImageView *StarOne;
@property (weak, nonatomic) IBOutlet UIImageView *StarTwo;
@property (weak, nonatomic) IBOutlet UIImageView *StarThree;
@property (weak, nonatomic) IBOutlet UIImageView *StarFour;
@property (weak, nonatomic) IBOutlet UIImageView *StarFive;
@property (weak, nonatomic) IBOutlet UIImageView *StarSix;
@property (weak, nonatomic) IBOutlet UIImageView *StarSeven;







@end
