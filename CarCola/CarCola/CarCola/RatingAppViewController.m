//
//  RatingAppViewController.m
//  CarCola
//
//  Created by Hao Zhou on 3/15/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import "RatingAppViewController.h"

@interface RatingAppViewController ()

@end

@implementation RatingAppViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.ringOne.hidden = YES;
    self.ringTwo.hidden = YES;
    self.ringThree.hidden = YES;
    self.ringFour.hidden = YES;
    self.ringFive.hidden = YES;

    
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}



- (IBAction)ringButtonOne:(id)sender {
    self.ringOne.hidden = NO;
}

- (IBAction)ringButtonTwo:(id)sender {
    self.ringTwo.hidden = NO;
}

- (IBAction)ringButtonThree:(id)sender {
    self.ringThree.hidden = NO;
}

- (IBAction)ringButtonFour:(id)sender {
    self.ringFour.hidden = NO;
}

- (IBAction)ringButtonFive:(id)sender {
    self.ringFive.hidden = NO;
}

- (IBAction)backButton:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];

}

- (IBAction)doneRatingButton:(id)sender {
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Thanks for rating!" message:@"Please press Okay and Cancel button afterwards that can bring you back." delegate:self cancelButtonTitle:@"Okay" otherButtonTitles:nil];
    [alert show];
}

- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex {
    if (buttonIndex == [alertView cancelButtonIndex]) {
        [self dismissViewControllerAnimated:YES completion:nil];

        
    }
}

@end
