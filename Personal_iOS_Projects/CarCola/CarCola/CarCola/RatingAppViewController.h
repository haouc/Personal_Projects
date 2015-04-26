//
//  RatingAppViewController.h
//  CarCola
//
//  Created by Hao Zhou on 3/15/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "FirstViewController.h"

@interface RatingAppViewController : UIViewController
@property (weak, nonatomic) IBOutlet UIImageView *ringOne;
@property (weak, nonatomic) IBOutlet UIImageView *ringTwo;
@property (weak, nonatomic) IBOutlet UIImageView *ringThree;
@property (weak, nonatomic) IBOutlet UIImageView *ringFour;
@property (weak, nonatomic) IBOutlet UIImageView *ringFive;

- (IBAction)ringButtonOne:(id)sender;
- (IBAction)ringButtonTwo:(id)sender;

- (IBAction)ringButtonThree:(id)sender;
- (IBAction)ringButtonFour:(id)sender;
- (IBAction)ringButtonFive:(id)sender;

- (IBAction)backButton:(id)sender;

- (IBAction)doneRatingButton:(id)sender;



@end
