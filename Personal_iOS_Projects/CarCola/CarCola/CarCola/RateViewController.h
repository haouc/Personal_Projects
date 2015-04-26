//
//  RateViewController.h
//  CarCola
//
//  Created by Hao Zhou on 3/12/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface RateViewController : UIViewController

- (IBAction)sendFeedbackButton:(id)sender;
- (IBAction)cancelFeedbackButton:(id)sender;

@property (weak, nonatomic) IBOutlet UITextView *userFeedback;


@end
