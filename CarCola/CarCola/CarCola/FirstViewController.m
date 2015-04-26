//
//  FirstViewController.m
//  CarCola
//
//  Created by Hao Zhou on 3/1/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import "FirstViewController.h"

@interface FirstViewController ()

@end

@implementation FirstViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.ratingViewCaller.hidden = YES;
//for first use this app and record that.
    NSUserDefaults *startApp = [NSUserDefaults standardUserDefaults];
    [startApp setInteger:0 forKey:@"firstUse"];
    [startApp synchronize];
    
    //get time
    [self getDate];
    
    [self configView];
    [self recordingLog];
    [self checkingLog];
    
    
    NSUserDefaults *checkSavedFirstUse = [NSUserDefaults standardUserDefaults];
    NSDate *saved = [checkSavedFirstUse objectForKey:@"Initial Launch"];
    
    NSLog(@"the cheched in firstview for saved firstUse is %@", saved);
    
}

#pragma get the current time
-(void)getDate {
    NSDate* sourceDate = [NSDate date];
    
    NSTimeZone* sourceTimeZone = [NSTimeZone timeZoneWithAbbreviation:@"GMT"];
    NSTimeZone* destinationTimeZone = [NSTimeZone systemTimeZone];
    
    NSInteger sourceGMTOffset = [sourceTimeZone secondsFromGMTForDate:sourceDate];
    NSInteger destinationGMTOffset = [destinationTimeZone secondsFromGMTForDate:sourceDate];
    NSTimeInterval interval = destinationGMTOffset - sourceGMTOffset;
    
    NSDate* destinationDate = [[NSDate alloc] initWithTimeInterval:interval sinceDate:sourceDate];
    
    self.trackingDate = destinationDate;
    
    NSLog(@"the current date is %@", destinationDate);
    NSLog(@"the trackingDate is %@", self.trackingDate);
    
}

- (void)recordingLog {
    NSUserDefaults *loginDefaults = [NSUserDefaults standardUserDefaults];
    
    NSInteger counter = [loginDefaults integerForKey:@"firstUse"];
    
    NSInteger temp;
    
    if ([loginDefaults integerForKey:@"countingLoginTimes"] != 0) {
        temp = [loginDefaults integerForKey:@"countingLoginTimes"];
    }else{
        temp = counter;
    }

    

    temp ++;
    NSInteger userCounter = temp;
    
    [loginDefaults setInteger:userCounter forKey:@"countingLoginTimes"];
    [loginDefaults synchronize];


    NSLog(@"the user loged in %li times.", userCounter);
}

- (void)checkingLog {
    NSUserDefaults *checkingLoginTimes = [NSUserDefaults standardUserDefaults];
    NSInteger count = [checkingLoginTimes integerForKey:@"countingLoginTimes"];
    
    if (count == 1) {
        NSUserDefaults *firstTimeUse = [NSUserDefaults standardUserDefaults];
        [firstTimeUse setObject:self.trackingDate forKey:@"Initial Launch"];
        [firstTimeUse synchronize];
    }
    
    if (count == 5) {
        
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Thank you for your interest" message:@"Please give me a feedback how likely you would recommend this App to your friends. Press the emerging Star Ball." delegate:self cancelButtonTitle:@"Cancel" otherButtonTitles:nil];
        [alert addButtonWithTitle:@"Rate"];
        [alert show];
        
        self.ratingViewCaller.hidden = NO;
        [self.view bringSubviewToFront:self.ratingViewCaller];
    }
    
}

- (void) configView {
    
    NSArray *imageNamesOne = @[@"car-1", @"car-2", @"car-3", @"car-4", @"car-5", @"car-6", @"car-7", @"car-8", @"car-9", @"car-10", @"car-11", @"car-12", @"car-13", @"car-14", @"car-15", @"car-16", @"car-17", @"car-18", @"car-19", @"car-20"];
    NSArray *imageNamesTwo = @[@"car-21", @"car-22", @"car-23", @"car-24", @"car-25", @"car-26", @"car-27", @"car-28", @"car-29", @"car-30", @"car-31", @"car-32", @"car-33", @"car-34", @"car-35", @"car-36", @"car-37", @"car-38", @"car-39", @"car-40"];
    
    NSMutableArray *imagesOne = [[NSMutableArray alloc] init];
    for (int i = 0; i < imageNamesOne.count; i++) {
        [imagesOne addObject:[UIImage imageNamed:[imageNamesOne objectAtIndex:i]]];
    }
    self.carImageOne.animationImages = imagesOne;
    self.carImageOne.animationDuration = 50;
    [self.view addSubview:_carImageOne];
    [_carImageOne startAnimating];

    
    NSMutableArray *imagesTwo = [[NSMutableArray alloc] init];
    for (int i = 0; i < imageNamesTwo.count; i++) {
        [imagesTwo addObject:[UIImage imageNamed:[imageNamesTwo objectAtIndex:i]]];
    }
    self.carImageTwo.animationImages = imagesTwo;
    self.carImageTwo.animationDuration = 55;
    [self.view addSubview:_carImageTwo];
    [_carImageTwo startAnimating];
    
    NSLog(@"car pool has %@", imageNamesOne);
    
    
    self.carImageOne.contentMode = UIViewContentModeScaleAspectFill;
    
    
    self.carImageTwo.contentMode = UIViewContentModeScaleAspectFill;
    
    self.carImageOne.autoresizingMask =
    ( UIViewAutoresizingFlexibleBottomMargin
     | UIViewAutoresizingFlexibleHeight
     | UIViewAutoresizingFlexibleLeftMargin
     | UIViewAutoresizingFlexibleRightMargin
     | UIViewAutoresizingFlexibleTopMargin
     | UIViewAutoresizingFlexibleWidth);
    
    self.carImageTwo.autoresizingMask =
    ( UIViewAutoresizingFlexibleBottomMargin
     | UIViewAutoresizingFlexibleHeight
     | UIViewAutoresizingFlexibleLeftMargin
     | UIViewAutoresizingFlexibleRightMargin
     | UIViewAutoresizingFlexibleTopMargin
     | UIViewAutoresizingFlexibleWidth);
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)ratingViewButton:(id)sender {
}
@end
