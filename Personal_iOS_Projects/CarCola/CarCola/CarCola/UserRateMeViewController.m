//
//  UserRateMeViewController.m
//  CarCola
//
//  Created by Hao Zhou on 3/12/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import "UserRateMeViewController.h"

@interface UserRateMeViewController ()

@end

@implementation UserRateMeViewController

- (void)viewDidLoad {
    [super viewDidLoad];

    // Do any additional setup after loading the view.
}



- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

- (IBAction)CancelButton:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];

}

- (IBAction)RateButton:(id)sender {
    NSUserDefaults *userRateDefault = [NSUserDefaults standardUserDefaults];

    
    NSInteger rateNumber = [userRateDefault integerForKey:@"userRate"];
    
    if (rateNumber == 0) {
        self.StarOne.image = [UIImage imageNamed:@"RateStar-2"];
        [self.view bringSubviewToFront:_StarOne];
        [self growandshrink:_StarOne];
        rateNumber ++;
        [userRateDefault setInteger:rateNumber forKey:@"userRate"];
        [userRateDefault synchronize];
    }
    else if (rateNumber == 1) {
        self.StarTwo.image = [UIImage imageNamed:@"RateStar-5"];
        [self.view bringSubviewToFront:_StarTwo];
        [self growandshrink:_StarTwo];
        rateNumber ++;
        [userRateDefault setInteger:rateNumber forKey:@"userRate"];
        [userRateDefault synchronize];
    }
    else if (rateNumber == 2) {
        self.StarThree.image = [UIImage imageNamed:@"RateStar-6"];
        [self.view bringSubviewToFront:_StarThree];
        [self growandshrink:_StarThree];
        rateNumber ++;
        [userRateDefault setInteger:rateNumber forKey:@"userRate"];
        [userRateDefault synchronize];
    }
    else if (rateNumber == 3) {
        self.StarFour.image = [UIImage imageNamed:@"RateStar-7"];
        [self.view bringSubviewToFront:_StarFour];
        [self growandshrink:_StarFour];
        rateNumber ++;
        [userRateDefault setInteger:rateNumber forKey:@"userRate"];
        [userRateDefault synchronize];
    }
    else if (rateNumber == 4) {
        self.StarFive.image = [UIImage imageNamed:@"RateStar-2"];
        [self.view bringSubviewToFront:_StarFive];
        [self growandshrink:_StarFive];
        rateNumber ++;
        [userRateDefault setInteger:rateNumber forKey:@"userRate"];
        [userRateDefault synchronize];
    }
    else if (rateNumber == 5) {
        self.StarSix.image = [UIImage imageNamed:@"RateStar-9"];
        [self.view bringSubviewToFront:_StarSix];
        [self growandshrink:_StarSix];
        rateNumber ++;
        [userRateDefault setInteger:rateNumber forKey:@"userRate"];
        [userRateDefault synchronize];
    }
    else if (rateNumber == 6) {
        self.StarSeven.image = [UIImage imageNamed:@"RateStar-2"];
        [self.view bringSubviewToFront:_StarSeven];
        [self growandshrink:_StarSeven];
        rateNumber ++;
        [userRateDefault setInteger:rateNumber forKey:@"userRate"];
        [userRateDefault synchronize];
    }
    else {
        [userRateDefault setInteger:0 forKey:@"userRate"];
        [userRateDefault synchronize];
        
        self.StarOne.image = nil;
        self.StarTwo.image = nil;
        self.StarThree.image = nil;
        self.StarFour.image = nil;
        self.StarFive.image = nil;
        self.StarSix.image = nil;
        self.StarSeven.image = nil;
    }
    
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Thanks for rating!" message:@"Please press Okay that can bring you back." delegate:self cancelButtonTitle:@"Okay" otherButtonTitles:nil];
    [alert show];
    
//    NSLog(@"Current rate count is %li and the location is star %li", [userRateDefault integerForKey:@"userRate"], rateNumber);
//    NSLog(@"The starone is nil? %@ and startwo is nil? %@", self.StarOne.image == nil? @"YES":@"NO", self.StarTwo.image == nil?@"YES":@"NO");

    
    
}


#pragma growingandshrinking
-(void)growandshrink: (UIImageView *) image{
    
    [UIImageView animateWithDuration:0.5f
                          animations:^{
                              CGRect frame = image.frame;
                              frame.size.height = 1.0f;
                              frame.size.width = 1.0f;
                              image.frame = frame;
                          }
                          completion:^(BOOL finished){
                          }];
}


@end
