//
//  RateViewController.m
//  CarCola
//
//  Created by Hao Zhou on 3/12/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import "RateViewController.h"

@interface RateViewController ()

@end

@implementation RateViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.userFeedback.hidden = NO;
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


//send feedback, sure this is a faked sending.
- (IBAction)sendFeedbackButton:(id)sender {
    
    if (self.userFeedback.text.length == 0){
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"I understand" message:@"Please leave your feedback, you can click to leave if you are not ready yet." delegate:self cancelButtonTitle:@"I am not ready" otherButtonTitles:nil];
        [alert show];
    }else{
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Thank you" message:@"Your opinion and suggestion are always most important for us." delegate:self cancelButtonTitle:@"Done" otherButtonTitles:nil];
        [alert show];
        self.userFeedback.hidden = YES;
    }
}

- (IBAction)cancelFeedbackButton:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];

}

- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex {
    if (buttonIndex == [alertView cancelButtonIndex]) {
        [self dismissViewControllerAnimated:YES completion:nil];

    }
}


@end
